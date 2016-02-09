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

public class BaseManager extends Object {

	private double Base_Scale_Factor = 1.0;
	private double Base_Offset_Factor = 0.0;
	private double Ship_Mass_Factor = 800.0;

	private HashMap<UUID, Double> baseWeights = new HashMap<UUID, Double>();

	private double maxWeight = Double.MAX_VALUE;
	private UUID maxWeightUUID = null;

	public void updateWeights(Toroidal2DPhysics space, Ship ship){

		maxWeight = Double.MIN_VALUE;
		maxWeightUUID = null;
		baseWeights.clear();

		for(Base b : space.getBases()){
			UUID id = b.getId();

			double distance = space.findShortestDistance(ship.getPosition(), b.getPosition());

			double currentWeight = ((1 / distance) * (ship.getMass() / Ship_Mass_Factor) * Base_Scale_Factor) + Base_Offset_Factor;

			baseWeights.put(id, new Double(currentWeight));

			if (currentWeight > maxWeight){
				maxWeight = currentWeight;
				maxWeightUUID = id;
			}
		}
	}

	public Base getBestBase(Toroidal2DPhysics space){
		if (maxWeightUUID == null) {
			return null;
		} else {
			return (Base) space.getObjectById(maxWeightUUID);
		}
	}

	public double getBiasOfBestBase(){
		if (maxWeightUUID == null){
			return Double.MAX_VALUE;
		} else {
			return baseWeights.get(maxWeightUUID).doubleValue();
		}
	}
}