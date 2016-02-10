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
 * Analyzes the state of the space to determine how favorable conditions are for navigating to a Base
 * 
 * Takes into account distances to Bases
 * 
 * @author Shelby Vanhooser, Nick Sparks
 */
public class BaseManager extends Object {

	private double Base_Scale_Factor = 1.1;   		// A scaling factor used to determine the sensitivity of the BaseManager to conditions in the environment 
	private double Base_Offset_Factor = 0.0;  		// An offset factor used to determine how overall more favorable we want to be to navigating to Bases
	private double Ship_Mass_Factor = 750.0;			// A scaling factor used to determine how much mass, i.e. resources we have on board 

	private double maxWeight = -1.0;						// The maximum weight currently assigned to the best base in the space
	private UUID maxWeightUUID = null;					// The UUID of the base that is currently most favored 

	/**
	*	Takes in the current state of the space and updates the favorability setting for each base, but it only saves the best base for efficiency
	*	
	*/
	public void updateWeights(Toroidal2DPhysics space, Ship ship){

		maxWeight = Double.MIN_VALUE;
		maxWeightUUID = null;

		for(Base b : space.getBases()){
			if (b.getTeamName().equalsIgnoreCase(ship.getTeamName())){
				UUID id = b.getId();

				double distance = space.findShortestDistance(ship.getPosition(), b.getPosition());

				double currentWeight = ((1.0 / Math.pow(distance, 0.75)) * ((((double)ship.getMass()) - ((double)ship.SHIP_MASS)) / Ship_Mass_Factor) * Base_Scale_Factor) + Base_Offset_Factor;

				if (currentWeight > maxWeight){
					maxWeight = currentWeight;
					maxWeightUUID = id;
				}
			}
		}
	}

	/**
	* Returns the best base from the current space, if it exists
	* 
	*/
	public Base getBestBase(Toroidal2DPhysics space){
		if (maxWeightUUID == null) {
			return null;
		} else {
			return (Base) space.getObjectById(maxWeightUUID);
		}
	}

	/**
	* Returns the bias of the best base as observed from the last update
	*
	*/
	public double getBiasOfBestBase(){
		if (maxWeightUUID == null){
			return -1.0;
		} else {
			return maxWeight;
		}
	}
}