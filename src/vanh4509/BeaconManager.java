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

public class BeaconManager extends Object {

	private double Beacon_Scale_Factor = 1.0;
	private double Beacon_Offset_Factor = 0.0;

	private HashMap<UUID, Double> beaconWeights = new HashMap<UUID, Double>();

	private double maxWeight = Double.MAX_VALUE;
	private UUID maxWeightUUID = null;

	public void updateWeights(Toroidal2DPhysics space, Ship ship){
		
		maxWeight = Double.MIN_VALUE;
		maxWeightUUID = null;
		beaconWeights.clear();

		for(Beacon b : space.getBeacons()){
			UUID id = b.getId();
			double distance = space.findShortestDistance(ship.getPosition(), b.getPosition());

			double currentWeight = ((1 / distance) * (1 - (ship.getEnergy() / ship.SHIP_MAX_ENERGY)) * Beacon_Scale_Factor) + Beacon_Offset_Factor;

			beaconWeights.put(id, new Double(currentWeight));

			if (currentWeight > maxWeight){
				maxWeight = currentWeight;
				maxWeightUUID = id;
			}
		}
	}

	public Beacon getBestBeacon(Toroidal2DPhysics space){
		if (maxWeightUUID == null) {
			return null;
		} else {
			Beacon b = (Beacon) space.getObjectById(maxWeightUUID);
			if (b == null){
			}
			return (Beacon) space.getObjectById(maxWeightUUID);
		}
	}

	public double getBiasOfBestBeacon(){
		if (maxWeightUUID == null){
			return Double.MAX_VALUE;
		} else {
			return beaconWeights.get(maxWeightUUID).doubleValue();
		}
	}


}