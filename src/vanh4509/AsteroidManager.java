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

public class AsteroidManager extends Object {
    private float ASTEROID_SCALE_FACTOR = 1.0;
    private float ASTEROID_OFFSET_FACTOR = 0.0;
    
    // max value calculated by assuming maximum valued asteroid to be all-metal and of maximum area
    // (15^2 * PI) * METALS_DENSITY (0.45)
    private float MAXIMUM_ASTEROID_VALUE = 707.0;
    
	HashMap<UUID, Float> asteroidWeights = new HashMap<UUID, Float>();
    UUID maxWeightUUID = null;
    
	public void updateWeights(Toroidal2DPhysics space, Ship ship) {
        double maxWeight = MIN_VALUE;
        
        // scrub the hashmaps and start over
        asteroidWeights.clear();
        
        for (Asteroid ast : space.getAsteroids()) {
            // weight is value / distance
            double value = ast.getResources().getTotal();
            double distance = space.findShortestDistance(ship.getPosition(), ast.getPosition());
            double weight = (value / MAXIMUM_ASTEROID_VALUE) / distance * ASTEROID_SCALE_FACTOR + ASTEROID_OFFSET_FACTOR;
            
            // update the maps
            asteroidWeights.put(ast.id, new Double(weight));
            
            // update max uuid
            maxWeightUUID = weight > maxWeight ? ast.id : maxWeightUUID;
        }
	}

	public Asteroid getBestAsteroid(Toroidal2DPhysics space) {
        return (Asteroid) space.getObjectByID(maxWeightUUID);
	}
}