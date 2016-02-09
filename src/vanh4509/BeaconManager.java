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

	private double maxWeight = -1.0;
	private UUID maxWeightUUID = null;

	public void updateWeights(Toroidal2DPhysics space, Ship ship){
		
		maxWeight = -1.0;
		maxWeightUUID = null;
		beaconWeights.clear();

		for(Beacon b : space.getBeacons()){
			UUID id = b.getId();
			double distance = space.findShortestDistance(ship.getPosition(), b.getPosition());
			double distanceFactor = (1.0 / distance);
			double energyFactor = (1.0 - (( (double) ship.getEnergy()) / (double) ship.SHIP_MAX_ENERGY));
			double currentWeight = distanceFactor * energyFactor * Beacon_Scale_Factor + Beacon_Offset_Factor;
			//System.out.printf("Distance factor : %f\n energy factor : %f\ncurrentWeight : %f\n", distanceFactor, energyFactor, currentWeight);
			
			beaconWeights.put(id, new Double(currentWeight));
			//System.out.printf("Current weight: %f", currentWeight);
			if (currentWeight > maxWeight){
				maxWeight = currentWeight;
				maxWeightUUID = id;
				//System.out.println("Did set new max weight");
			}
		}
		//System.out.printf("After weight update, ID is : %s\n", maxWeightUUID);
	}

	public Beacon getBestBeacon(Toroidal2DPhysics space){
		if (maxWeightUUID == null) {
			System.out.println("UUID is null");
			return null;
		} else {
			Beacon b = (Beacon) space.getObjectById(maxWeightUUID);
			if (b == null){
				System.out.println("Space returned a null");
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