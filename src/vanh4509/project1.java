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
 * @author Shelby Vanhooser, Nick Sparks
 */
public class project1 extends TeamClient {

  // --------------------- Class Variable Declarations ---------------------

  private HashMap <UUID, Ship> asteroidToShipMap;
  private HashMap <Ship, UUID> shipToAsteroidMap;
  private HashMap <UUID, Boolean> aimingForBase;

	private BeaconManager beaconManager;					// An object to handle keeping track of Beacons in space and how favorable conditions are for the Ship to refuel at one 
	private AsteroidManager asteroidManager;			// an object to handle keeping track of Asteroids in space and how favorable conditions are for the Ship to mine one 
	private BaseManager baseManager;							// An object to handle keeping track of Bases in space and how favorable conditions are for the Ship to visit one to deposit resources/refuel

	private boolean shouldShoot = false;					// From the base agent, a boolean to keep track of if this agent fires its weapon 

	private UUID asteroidCollectorID;							// From the base agent, the UUID of the ship designated to collect Asteroids - UNUSED
	private double weaponsProbability = 0.0;				// From the base agent, the probabily the ship will fire its weapons.  As this project is taking place in a passive game, this is 0



	// --------------------------------------------------- Movement Initialization Method ------------------------------------------------------

	/**
	 * @description Begins the process of assigning tasks to ships and interactions with objects in space
	 * 
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game 
	 * @param actionableObjects (Set<AbstractActionableObject>) - the set of objects from space that can be acted upon by the agent (some objects in space cannot be influenced by the agent)
	 * 
	 * @return (Map<UUID, AbstractAction>)
	 */
	public Map<UUID, AbstractAction> getMovementStart(Toroidal2DPhysics space,
			Set<AbstractActionableObject> actionableObjects) {

		HashMap<UUID, AbstractAction> actions = new HashMap<UUID, AbstractAction>();			// The set of actions that we will put upon the space

		for (AbstractObject actionable :  actionableObjects) {					// For each actionable object in the space
			if (actionable instanceof Ship) {															// If the actionable object is one of our ships...
				Ship ship = (Ship) actionable;															//... cast it as such 

				AbstractAction action;																			// The Action we will return 
				
				action = getAsteroidCollectorAction(space, ship);  					// In this implementation, there will only be one ship, so we get its action (which is that of an Asteroid collector role)

				actions.put(ship.getId(), action);													// Put this action in the set that will be put upon the space
				
			} else {
				
				actions.put(actionable.getId(), new DoNothingAction());			// This actionable object is a base.  Do not act upon it. 
			}
		} 
		return actions;																									// Put the actions on the space 
	}

	// ------------------------------------------------- Asteroid Collection Action Retreival Method ----------------------------------------------------
	
	/**
	 * Gets the action for the asteroid collecting ship (the only ship in this case)
	 * 
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game
	 * @param ship (Ship) - The player's current ship
	 * 
	 * @return (AbstractAction) - The action the Asteroid collector will undertake in the space 
	 */
	private AbstractAction getAsteroidCollectorAction(Toroidal2DPhysics space, 
		Ship ship) {
		AbstractAction current = ship.getCurrentAction(); 			// What is the Ship currently doing?

    if (current.isMovementFinished(space)) {								// If the Ship just finished its action...
      current = null;																				// ... note that it just did
    }

    if (ship.getResources().getTotal() == 0 && ship.getEnergy() > 2000 && aimingForBase.containsKey(ship.getId()) && aimingForBase.get(ship.getId())) {					// If the ship just hit a Base and has charged back up....
			current = null;																							// ... The ship is ready for another task
			aimingForBase.put(ship.getId(), false);											// Indicate that this hip is not targeting anything 
		}
    
		Position currentPosition = ship.getPosition();								// What is the Ship's current position? 


		beaconManager.updateWeights(space, ship); 										// Update weights in our Beacon manager
		asteroidManager.updateWeights(space, ship); 									// Update weights in our Asteroid manager
		baseManager.updateWeights(space, ship);    										// Update weights in our Base manager

		double beaconBias = beaconManager.getBiasOfBestBeacon();										// Determine the weight of seeking a Beacon interaction
		double asteroidBias = asteroidManager.getBiasOfBestAsteroid();							// Determine the weight of seeking an Asteroid interaction
		double baseBias = baseManager.getBiasOfBestBase();													// Determine the weight of seeking a Base interaction
		
    double maxBias = Math.max(beaconBias, Math.max(asteroidBias, baseBias));		// Which of the possible actions is greatest in weight? 
    
    if (ship.getEnergy() < 2000) {					// Perform an emergency check in case of low energy ...
        current = null;											// ... so we perform a Beacon action
        maxBias = beaconBias;								// Set the Beacon's bias to be the maximum
    }
    
    if (maxBias == baseBias && (current == null || current instanceof DoNothingAction)){				// If the maximum bias (or weight) is a Base action and the agent's Ship isn't already busy...
			Base base = baseManager.getBestBase(space);																								// ... begin preparations for interacting with a Base by retreiving the Base from the manager
			AbstractAction newAction = new MoveToObjectAction(space, currentPosition, base); 					// Create a movement action to the Base
			aimingForBase.put(ship.getId(), true);																										// Note in our structure that we are moving to a base (used in determining if we hit a Base)
			
			return newAction;   																																			// Return this Base action

      } else if (maxBias == beaconBias && (current == null || current instanceof DoNothingAction)) {			// If the maximum bias (or weight) is a Beacon action and the agent's Ship isn't already busy...
    		Beacon beacon = beaconManager.getBestBeacon(space);																								// ... begin preparations for interacting with a Beacon by retreiving the Beacon from the manager
    		AbstractAction newAction = null;																							// Note that our action will be returned after we determine what to do below 									 
    		
    		aimingForBase.put(ship.getId(), false);																				// Make an indication that the ship is no longer aiming for a Base, if we haven't already

    		if (beacon == null) {																													// If the BeaconManager didn't actually returned a valid Beacon...
    			newAction = new DoNothingAction();																					// ... wait a turn and see if things improve
    		} else {																																			// Otherwise...
    			newAction = new MoveToObjectAction(space, currentPosition, beacon);					// ... move to the returned Beacon
    		}

    		return newAction; 																														// Return the action we determined hsould be performed 

      } else if (maxBias == asteroidBias && (current == null || current instanceof DoNothingAction)) {		// If the maximum bias (or weight) is an Asteroid action and the agent's Ship isn't already busy...
    		Asteroid asteroid = asteroidManager.getBestAsteroid(space);																				// ... begin preparations for interacting with an Asteroid by retreiving the Asteroid from the manager
    		AbstractAction newAction = null;																							// Note that our action will be returned after we determine what to do below 

    		aimingForBase.put(ship.getId(), false);																				// Make an indication that the ship is no longer aiming for a Base, if we haven't already

    		if (asteroid == null) {																												// If the AsteroidManager didn't actually returned a valid Asteroid...
    			Beacon beacon = beaconManager.getBestBeacon(space);													// ... attempt to find a Beacon as a replacement
    			if (beacon == null) {																												// If the BeaconManager couldn't find a Beacon to interact with...
    				newAction = new DoNothingAction();																				// ... skip a turn and see if things improve
    			} else {																																		// Otherwise, BeaconManager returned a valid Beacon...
    				newAction = new MoveToObjectAction(space, currentPosition, beacon);				// ... So move to it
    			}
    		} else {																																			// Ttherwise the AsteroidManager returned a valid Asteroid...
    			newAction = new MoveToObjectAction(space, currentPosition, asteroid);				// ... so we can move to the Asteroid
    		}

    			return newAction;																														// Returne the proper action we just determined 
        }   
		return current;																														// No concensus could be reached on what to do in terms of our weights/biases.  Therefore, continue the current action. 
	}

// ------------------------------------------------- Override Methods -------------------------------------------------


	/**
	 * Callback action for when a movement is completed where we will do any cleanup of objects in space that may have changed state (i.e. remove dead asteroids from being targeted)
	 * 
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game
	 * @param actionableObjects (Set<AbstractActionableObject>) - the set of objects from space that can be acted upon by the agent (some objects in space cannot be influenced by the agent)
	 * 
	 * @return (void)
	 */
	@Override
	public void getMovementEnd(Toroidal2DPhysics space, 
		Set<AbstractActionableObject> actionableObjects) {
		ArrayList<Asteroid> finishedAsteroids = new ArrayList<Asteroid>();																			// Variable to store which Asteroids have been mined 
		for (UUID asteroidId : asteroidToShipMap.keySet()) {																										// For every Asteroid ID in our Asteroid to ship map...
			Asteroid asteroid = (Asteroid) space.getObjectById(asteroidId);																				//... retreive this Asteroid by its ID from the space...
			if (asteroid != null && !asteroid.isAlive()) {																															//... and check to see if it is nonnull but alive 
				finishedAsteroids.add(asteroid);																																					// Finally, note that this Asteroid is finished 
			}
		}

		for (Asteroid asteroid : finishedAsteroids) {																														// For each of the Asteroids that are finished...
			asteroidToShipMap.remove(asteroid);																																		// ... remove each from the map of assigned Asteroids to Ships
		}
	}

	/**
	 * Initialization method for starting the agent
	 *
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game
	 * 
	 * @return (void)
	 */
	@Override
	public void initialize(Toroidal2DPhysics space) {					
		asteroidToShipMap = new HashMap<UUID, Ship>();
		shipToAsteroidMap = new HashMap<Ship, UUID>();
		aimingForBase = new HashMap<UUID, Boolean>();
		asteroidCollectorID = null;

		beaconManager = new BeaconManager();
		asteroidManager = new AsteroidManager(true);																														// Initialize the AsteroidManager with one of two available knowledge representations
		baseManager = new BaseManager();
	}

	/**
	 * Callback method for when the game is shutting down
	 *
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game
	 * 
	 * @return (void)
	 */
	@Override
	public void shutDown(Toroidal2DPhysics space) {
		// Unimplemented
	}

	/**
	 * Method for retreiving the graphics of this agent
	 *
	 * @param (void)
	 * 
	 * @return (Set<SpacewarGraphics>)
	 */
	@Override
	public Set<SpacewarGraphics> getGraphics() {
		// Unimplemented
		return null;
	}

// --------------------------------------------- Purchasing Methods -----------------------------------------------


	@Override
	/**
	 * Method for returning the purchases the agent wishes to conduct for UUIDs in space
	 *
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game
	 * @param actionableObjects (Set<AbstractActionableObject>) - The set of objects from space that can be acted upon by the agent (some objects in space cannot be influenced by the agent)
	 * @param resourcesAvailable (ResourcePile) - The set of items currently availalbe to the agent for use in purchasing (currency)
	 * @param purchaseCosts (PurchaseCosts) - The set of purchase cost annotations to be used in calculation on what can be afforded by the agent 
	 * 
	 * @return (Map<UUID, PurchaseTypes>) - A set of purchases mapped to by unique ids of objects in the map
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

					if (maxDistance > BASE_BUYING_DISTANCE) {														// Purchase a base
						purchases.put(ship.getId(), PurchaseTypes.BASE);
						bought_base = true;
						//System.out.println("Buying a base!!");
						break;
					}
				}
			}		
		} 
		return purchases;
	}


	// ------------------------------------- Using Powerups Methods ------------------------------------------

	/**
	 * The asteroid collector doesn't use power ups but the weapons one does (at random)
	 * @param space (Toroidal2DPhysics) - The current space conditions of the game
	 * @param actionableObjects (Set<AbstractActionableObject>) - The set of objects from space that can be acted upon by the agent (some objects in space cannot be influenced by the agent)
	 * 
	 * @return (Map<UUID, SpaceSettlersPowerupEnum>) - A set of powerups to be taken mapped to by unique ids of objects in the map
	 */
	@Override
	public Map<UUID, SpaceSettlersPowerupEnum> getPowerups(Toroidal2DPhysics space,
			Set<AbstractActionableObject> actionableObjects) {
		HashMap<UUID, SpaceSettlersPowerupEnum> powerUps = new HashMap<UUID, SpaceSettlersPowerupEnum>();
		
		return powerUps;
	}

}
