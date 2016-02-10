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


/**
 * Analyzes the state of the space to determine how favorable conditions are for navigating to a Beacon
 * 
 * Takes into account distances to Beacons
 * 
 * @author Shelby Vanhooser, Nick Sparks
 */
public class BeaconManager extends Object {

	private double Beacon_Scale_Factor = 0.85;			// A scaling factor used to determine the sensitivity of the BeaconManager to conditions in the environment
	private double Beacon_Offset_Factor = 0.0;			// An offset factor used to determine how overall more favorable we want to be to navigating to Beacons

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
			return (Beacon) space.getObjectById(maxWeightUUID);
		}
	}

	public double getBiasOfBestBeacon(){
		if (maxWeightUUID == null){
			return -1;
		} else {
			return maxWeight;
		}
	}


}