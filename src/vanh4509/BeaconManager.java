package vanh4509;

import vanh4509.vanh4509;

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

	private float Beacon_Scale_Factor = 1.0;
	private float Beacon_Offset_Factor = 1.0;

	private HashMap<UUID, Double> beaconWeights = new HashMap<UUID, Double>();

	private double maxWeight;
	private UUID maxWeightUUID;

	private UUID closestDistanceUUID;
	private double closestDistance;


	public void updateWeights(Toroidal2DPhysics space, Ship ship){

		maxWeight = MIN_VALUE;
		closestDistance = MIN_VALUE;

		for(Beacon b : space.getBeacons()){
			UUID id = b.id;
			double energy = b.BEACON_ENERGY_BOOST;
			double distance = space.findShortestDistance(ship.getPosition(), b.getPosition());

			double currentWeight = ((energy / distance) * (1 - (ship.energy / ship.SHIP_MAX_ENERGY)) * Beacon_Scale_Factor) + Beacon_Offset_Factor;

			beaconWeights.put(id, new Double(currentBias));

			if (currentWeight > maxWeight){
				maxWeight = currentWeight;
				maxWeightUUID = id;
			}

			if (distance < closestDistance){
				closestDistance = distance;
				closestDistanceUUID = id;
			}
		}
	}

	public Beacon getBestBeacon(Toroidal2DPhysics space){
		return (Beacon) space.getObjectByID(maxWeightUUID);
	}

	public Beacon getClosestBeacon(Toroidal2DPhysics space){
		return (Beacon) space.getObjectByID(closestDistanceUUID);
	}

	public double getBiasOfBestBeacon(Beacon b){
		return beaconWeights.get(b.id).doubleValue();
	}



}