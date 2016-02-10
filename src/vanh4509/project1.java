package vanh4509;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import spacesettlers.actions.AbstractAction;
import spacesettlers.actions.DoNothingAction;
import spacesettlers.actions.MoveToObjectAction;
import spacesettlers.actions.MoveAction;
import spacesettlers.actions.PurchaseCosts;
import spacesettlers.actions.PurchaseTypes;
import spacesettlers.clients.*;
import spacesettlers.graphics.SpacewarGraphics;
import spacesettlers.objects.AbstractActionableObject;
import spacesettlers.objects.AbstractObject;
import spacesettlers.objects.Asteroid;
import spacesettlers.objects.Base;
import spacesettlers.objects.Beacon;
import spacesettlers.objects.Ship;
import spacesettlers.objects.powerups.SpaceSettlersPowerupEnum;
import spacesettlers.objects.resources.ResourcePile;
import spacesettlers.simulator.Toroidal2DPhysics;
import spacesettlers.utilities.Position;
import spacesettlers.utilities.Vector2D;


/**
 * Custom Classes
 */
import vanh4509.AsteroidManager;
import vanh4509.BeaconManager;
import vanh4509.BaseManager;

/**
 * Collects nearby asteroids and brings them to the base, picks up beacons as needed for energy.
 * 
 * If there is more than one ship, only one ship is dedicated to picking up asteroids and
 * the other is dedicated to using weapons
 * 
 * @author amy
 */
public class project1 extends TeamClient {
  HashMap <UUID, Ship> asteroidToShipMap;
  HashMap <Ship, UUID> shipToAsteroidMap;
  HashMap <UUID, Boolean> aimingForBase;

	BeaconManager beaconManager;
	AsteroidManager asteroidManager;
	BaseManager baseManager;

	boolean shouldShoot = false;

	UUID asteroidCollectorID;
	double weaponsProbability = 1;


	/**
	 * Assigns ships to asteroids and beacons, as described above
	 */
	public Map<UUID, AbstractAction> getMovementStart(Toroidal2DPhysics space,
			Set<AbstractActionableObject> actionableObjects) {
		HashMap<UUID, AbstractAction> actions = new HashMap<UUID, AbstractAction>();

		// loop through each ship
		for (AbstractObject actionable :  actionableObjects) {
			if (actionable instanceof Ship) {
				Ship ship = (Ship) actionable;

				AbstractAction action;
				
				//We are at the current ship object NOTE: This implementation assumes only one ship in space

				// the first time we initialize, decide which ship is the asteroid collector
				action = getAsteroidCollectorAction(space, ship);

				actions.put(ship.getId(), action);
				
			} else {
				// it is a base.  Heuristically decide when to use the shield (TODO)
				actions.put(actionable.getId(), new DoNothingAction());
			}
		} 
		return actions;
	}
	
	/**
	 * Gets the action for the asteroid collecting ship
	 * @param space
	 * @param ship
	 * @return
	 */
	private AbstractAction getAsteroidCollectorAction(Toroidal2DPhysics space,
			Ship ship) {

		AbstractAction current = ship.getCurrentAction();

    if (current.isMovementFinished(space)) {
    // reset the current action if we aren't moving
      current = null;
    }
    

    if (ship.getResources().getTotal() == 0 && ship.getEnergy() > 2000 && aimingForBase.containsKey(ship.getId()) && aimingForBase.get(ship.getId())) {
			current = null;
			aimingForBase.put(ship.getId(), false);
		}
        
		Position currentPosition = ship.getPosition();

		beaconManager.updateWeights(space, ship); 			// Update weights in our beacon manager
		asteroidManager.updateWeights(space, ship); 		// Update weights in our asteroid manager
		baseManager.updateWeights(space, ship);    			// Update weights in our base manager

		double beaconBias = beaconManager.getBiasOfBestBeacon();
		double asteroidBias = asteroidManager.getBiasOfBestAsteroid();
		double baseBias = baseManager.getBiasOfBestBase();
        
    // compute the maximum bias
    double maxBias = Math.max(beaconBias, Math.max(asteroidBias, baseBias));

    if (maxBias == baseBias && (current == null || current instanceof DoNothingAction)){
			// Perform move to base action 

			Base base = baseManager.getBestBase(space);
			AbstractAction newAction = new MoveToObjectAction(space, currentPosition, base);
			aimingForBase.put(ship.getId(), true);
			//AbstractAction newAction = new MoveToObjectAction(space, currentPosition, base);
			
			return newAction;   
    } else if (maxBias == beaconBias && (current == null || current instanceof DoNothingAction)) {
			// Perform move to beacon action

			aimingForBase.put(ship.getId(), false);

			AbstractAction newAction = null;
			Beacon beacon = beaconManager.getBestBeacon(space);
			// if there is no beacon, then just skip a turn

			if (beacon == null) {
				newAction = new DoNothingAction();
			} else {
				newAction = new MoveToObjectAction(space, currentPosition, beacon);
			}

			return newAction;

    } else if (maxBias == asteroidBias && (current == null || current instanceof DoNothingAction)) {
			// Perform move to asteroid action


			aimingForBase.put(ship.getId(), false);
			Asteroid asteroid = asteroidManager.getBestAsteroid(space);

			AbstractAction newAction = null;

			if (asteroid == null) {
				// there is no asteroid available so collect a beacon
				Beacon beacon = beaconManager.getBestBeacon(space);
				// if there is no beacon, then just skip a turn
				if (beacon == null) {
					newAction = new DoNothingAction();
				} else {
					newAction = new MoveToObjectAction(space, currentPosition, beacon);
					//newAction = new MoveToObjectAction(space, currentPosition, beacon);
				}
			} else {
				newAction = new MoveToObjectAction(space, currentPosition, asteroid);
				//newAction = new MoveToObjectAction(space, currentPosition, asteroid);
			}

			return newAction;
    }
        
		return current;
	}

// -------------------------------------- Override Methods -------------------------------------------------

	@Override
	public void getMovementEnd(Toroidal2DPhysics space, Set<AbstractActionableObject> actionableObjects) {
		ArrayList<Asteroid> finishedAsteroids = new ArrayList<Asteroid>();
		for (UUID asteroidId : asteroidToShipMap.keySet()) {
			Asteroid asteroid = (Asteroid) space.getObjectById(asteroidId);
			if (asteroid != null && !asteroid.isAlive()) {
				finishedAsteroids.add(asteroid);
				//System.out.println("Removing asteroid from map");
			}
		}

		for (Asteroid asteroid : finishedAsteroids) {
			asteroidToShipMap.remove(asteroid);
		}


	}

	@Override
	public void initialize(Toroidal2DPhysics space) {
		asteroidToShipMap = new HashMap<UUID, Ship>();
		shipToAsteroidMap = new HashMap<Ship, UUID>();
		aimingForBase = new HashMap<UUID, Boolean>();
		asteroidCollectorID = null;

		beaconManager = new BeaconManager();
		asteroidManager = new AsteroidManager();
		baseManager = new BaseManager();
	}

	@Override
	public void shutDown(Toroidal2DPhysics space) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<SpacewarGraphics> getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * If there is enough resourcesAvailable, buy a base.  Place it by finding a ship that is sufficiently
	 * far away from the existing bases
	 */
	public Map<UUID, PurchaseTypes> getTeamPurchases(Toroidal2DPhysics space,
			Set<AbstractActionableObject> actionableObjects, 
			ResourcePile resourcesAvailable, 
			PurchaseCosts purchaseCosts) {

		HashMap<UUID, PurchaseTypes> purchases = new HashMap<UUID, PurchaseTypes>();
		double BASE_BUYING_DISTANCE = 200;
		boolean bought_base = false;

		if (purchaseCosts.canAfford(PurchaseTypes.BASE, resourcesAvailable)) {
			for (AbstractActionableObject actionableObject : actionableObjects) {
				if (actionableObject instanceof Ship) {
					Ship ship = (Ship) actionableObject;
					Set<Base> bases = space.getBases();

					// how far away is this ship to a base of my team?
					double maxDistance = Double.MIN_VALUE;
					for (Base base : bases) {
						if (base.getTeamName().equalsIgnoreCase(getTeamName())) {
							double distance = space.findShortestDistance(ship.getPosition(), base.getPosition());
							if (distance > maxDistance) {
								maxDistance = distance;
							}
						}
					}

					if (maxDistance > BASE_BUYING_DISTANCE) {
						purchases.put(ship.getId(), PurchaseTypes.BASE);
						bought_base = true;
						//System.out.println("Buying a base!!");
						break;
					}
				}
			}		
		} 
		
		// see if you can buy EMPs
		if (purchaseCosts.canAfford(PurchaseTypes.POWERUP_EMP_LAUNCHER, resourcesAvailable)) {
			for (AbstractActionableObject actionableObject : actionableObjects) {
				if (actionableObject instanceof Ship) {
					Ship ship = (Ship) actionableObject;
					
					if (!ship.getId().equals(asteroidCollectorID) && ship.isValidPowerup(PurchaseTypes.POWERUP_EMP_LAUNCHER.getPowerupMap())) {
						purchases.put(ship.getId(), PurchaseTypes.POWERUP_EMP_LAUNCHER);
					}
				}
			}		
		} 


		return purchases;
	}

	/**
	 * The asteroid collector doesn't use power ups but the weapons one does (at random)
	 * @param space
	 * @param actionableObjects
	 * @return
	 */
	@Override
	public Map<UUID, SpaceSettlersPowerupEnum> getPowerups(Toroidal2DPhysics space,
			Set<AbstractActionableObject> actionableObjects) {
		HashMap<UUID, SpaceSettlersPowerupEnum> powerUps = new HashMap<UUID, SpaceSettlersPowerupEnum>();

		Random random = new Random();
		for (AbstractActionableObject actionableObject : actionableObjects){
			SpaceSettlersPowerupEnum powerup = SpaceSettlersPowerupEnum.values()[random.nextInt(SpaceSettlersPowerupEnum.values().length)];
			if (!actionableObject.getId().equals(asteroidCollectorID) && actionableObject.isValidPowerup(powerup) && random.nextDouble() < weaponsProbability){
				// powerUps.put(actionableObject.getId(), powerup);
			}
		}
		
		
		return powerUps;
	}

}
