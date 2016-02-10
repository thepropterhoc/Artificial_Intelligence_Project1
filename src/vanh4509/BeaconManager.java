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

	// --------------------- Class Variable Declarations ---------------------

	private static final double Beacon_Scale_Factor = 0.8;			// A scaling factor used to determine the sensitivity of the BeaconManager to conditions in the environment
	private static final double Beacon_Offset_Factor = 0.0;			// An offset factor used to determine how overall more favorable we want to be to navigating to Beacons

	private double maxWeight = -1.0;  				// Stores the weight of the best available Beacon
	private UUID maxWeightUUID = null;				// Stores the UUID of the best available Beacon

	// ------------------------ Weight Updating Methods ----------------------------

	/**
	*	@description Takes in the current state of the space and updates the favorability setting for each Beacon, but it only saves the best Beacon for efficiency
	*	
	* @param space (Toroidal2DPhysics) - The current space conditions of the game
	* @param ship (Ship) - The player's current ship
	* 
	* @return (void)
	*/
	public void updateWeights(Toroidal2DPhysics space, 
		Ship ship){
		
		maxWeight = -1.0;
		maxWeightUUID = null;

		for(Beacon b : space.getBeacons()){
			UUID id = b.getId();

			double distance = space.findShortestDistance(ship.getPosition(), b.getPosition());

			double distanceFactor = (1.0 / distance);
			double energyFactor = (1.0 - (( (double) ship.getEnergy()) / (double) ship.SHIP_MAX_ENERGY));

			double currentWeight = distanceFactor * energyFactor * Beacon_Scale_Factor + Beacon_Offset_Factor;

			if (currentWeight > maxWeight){
				maxWeight = currentWeight;
				maxWeightUUID = id;
			}
		}
	}

	// --------------------- Value Exporting Methods -----------------------

	/**
	* @description Returns the best Beacon from the current space, if it exists
	* 
	* @param space (Toroidal2DPhysics) - The current space conditions of the game
	* 
	* @return (Beacon) - The best Beacon available as of the last update of space conditions
	*/	public Beacon getBestBeacon(Toroidal2DPhysics space){
		if (maxWeightUUID == null) {
			return null;
		} else {
			return (Beacon) space.getObjectById(maxWeightUUID);
		}
	}

	/**
	* @description Returns the bias of the best Beacon as observed from the last update
	*
	* @param None
	* 
	* @return (double) - The bias of the best Beacon available.  This is a scaled value representing the overall favorability of the best Beacon 
	*/
	public double getBiasOfBestBeacon(){
		if (maxWeightUUID == null){
			return -1;
		} else {
			return maxWeight;
		}
	}


}