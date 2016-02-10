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
import spacesettlers.utilities.Vector2D;

public class AsteroidManager extends Object {
    private boolean useStdKRep;
    private String friendlyTeamName = null;

    private double ASTEROID_SCALE_FACTOR = 1.2;
    private double ASTEROID_OFFSET_FACTOR = 0.01;
    
    // max value calculated by assuming maximum valued asteroid to be all-metal and of maximum area
    // (15^2 * PI) * METALS_DENSITY (0.45)
    private double MAXIMUM_ASTEROID_VALUE = 707.0;
    
	HashMap<UUID, Double> asteroidWeights;
    HashMap<UUID, Double> teamToAsteroidWeightsMap;

    UUID maxWeightUUID = null;
    
	public void updateWeights(Toroidal2DPhysics space, Ship ship) {
        double maxWeight = -1.0;

        maxWeightUUID = null;
        friendlyTeamName = ship.getTeamName();
        
        // scrub the hashmaps and start over
        asteroidWeights.clear();
        teamToAsteroidWeightsMap.clear();
        
        if (useStdKRep) {
            for (Asteroid ast : space.getAsteroids()) {
                // weight is value / distance
                if (ast != null && ast.isMineable() && ast.isAlive() && ast.getPosition().getTranslationalVelocity().getMagnitude() == 0.0){
                    double value = ast.getResources().getTotal();
                    double distance = space.findShortestDistance(ship.getPosition(), ast.getPosition());
                    double weight = getAsteroidWeight(distance, value);

                    // update the maps
                    asteroidWeights.put(ast.getId(), new Double(weight));
                
                    // update max uuid
                    maxWeightUUID = weight > maxWeight ? ast.getId() : maxWeightUUID;
                }
            }
        } else {
            for (Asteroid ast : space.getAsteroids()) {
                String closestTeamName = null;
                double closestDistance = Double.MAX_VALUE;
                
                if (ast != null && ast.isMineable() && ast.isAlive() && ast.getPosition().getTranslationalVelocity().getMagnitude() == 0.0) {
                    // for each asteroid, find the ship closest to it
                    for (Ship s : space.getShips()) {
                        double distance = space.findShortestDistance(s.getPosition(), ast.getPosition());

                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestTeamName = s.getTeamName();
                        }
                    }
                    // update the map
                    double weight = getAsteroidWeight(closestDistance, ast.getResources().getTotal());

                    if (friendlyTeamName.equals(closestTeamName)) {
                        // update max uuid and weight
                        maxWeightUUID = weight > maxWeight ? ast.getId() : maxWeightUUID;
                        teamToAsteroidWeightsMap.put(ast.getId(), weight);
                    }
                }
            }
        }
	}

	public Asteroid getBestAsteroid(Toroidal2DPhysics space) {
        //System.out.println(maxWeightUUID);
        return (Asteroid) space.getObjectById(maxWeightUUID);
	}

    public double getBiasOfBestAsteroid(){
        if(maxWeightUUID == null){
            return -1.0;
        } else {
            return teamToAsteroidWeightsMap.get(maxWeightUUID).doubleValue();
        }
    }
    
    private double getAsteroidWeight(double distance, double value) {
        return (value / MAXIMUM_ASTEROID_VALUE) / distance * ASTEROID_SCALE_FACTOR + ASTEROID_OFFSET_FACTOR;
    }
    
    public AsteroidManager() {
        this(true);
    }
    
    public AsteroidManager(boolean useStd) {
        this.useStdKRep = useStd;
        asteroidWeights = new HashMap<UUID, Double>();
        teamToAsteroidWeightsMap = new HashMap<UUID, Double>();
    }
}