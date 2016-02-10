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
    private boolean useStdKRep = true;
    private String friendlyTeamName = null;

    private double ASTEROID_SCALE_FACTOR = 1.2;
    private double ASTEROID_OFFSET_FACTOR = 0.01;
    
    // max value calculated by assuming maximum valued asteroid to be all-metal and of maximum area
    // ~(15^2 * PI) * METALS_DENSITY (0.45)
    private double MAXIMUM_ASTEROID_VALUE = 707.0;
    
	HashMap<UUID, Double> asteroidWeights;
    HashMap<String, HashMap<UUID, Double>> teamToAsteroidWeightsMap;
    HashMap<UUID, Vector2D> asteroidVelocities;
    HashMap<UUID, Position> asteroidPreviousPositions;
    double previousTimeStep = 0.0;

    UUID maxWeightUUID = null;
    
	public void updateWeights(Toroidal2DPhysics space, Ship ship) {
        double maxWeight = -1.0;
        friendlyTeamName = ship.getTeamName();
        
        // scrub the hashmap and start over
        asteroidWeights.clear();
        
        if (useStdKRep) {
            for (Asteroid ast : space.getAsteroids()) {
                // weight is value / distance
                if (ast != null && ast.isMineable() && ast.isAlive()){
                    double value = ast.getResources().getTotal();
                    double distance = space.findShortestDistance(ship.getPosition(), ast.getPosition());
                    double weight = (value / MAXIMUM_ASTEROID_VALUE) / distance * ASTEROID_SCALE_FACTOR + ASTEROID_OFFSET_FACTOR;

                    Position astPosition = ast.getPosition();
                    double currentStep = space.getTimestep();

                    double timeDifference = currentStep - previousTimeStep;
                    double deltaX;
                    double deltaY;
                    if (asteroidPreviousPositions.containsKey(ast.getId())){
                        Position previousAstPosition = asteroidPreviousPositions.get(ast.getId());
                        deltaX = (double) astPosition.getX() - previousAstPosition.getX();
                        deltaY = (double) astPosition.getY() - previousAstPosition.getY();
                    } else {
                        deltaY = 0.0;
                        deltaX = 0.0;
                    }

                    Vector2D asteroidVelocity = new Vector2D(deltaX, deltaY);
                    
                    // update the maps
                    asteroidVelocities.put(ast.getId(), asteroidVelocity);
                    asteroidPreviousPositions.put(ast.getId(), astPosition);
                    asteroidWeights.put(ast.getId(), new Double(weight));
                    
                    maxWeightUUID = weight > maxWeight ? ast.getId() : maxWeightUUID;
                }
            }

        } else {
            // alt knowledge representation
            // Group asteroids by which team they're closest to. We're more likely to get asteroids that are far away for every other team.
            for (Asteroid ast : space.getAsteroids()) {
                String closestTeamName = null;
                double closestDistance = Double.MAX_VALUE;
                
                if (ast != null && ast.isMineable() && ast.isAlive()) {
                    for (Ship s : space.getShips()) {
                        double distance = space.findShortestDistance(s.getPosition(), ast.getPosition());
                        
                        closestDistance = distance < closestDistance ? distance : closestDistance;
                        closestTeamName = distance < closestDistance ? s.getTeamName() : closestTeamName;
                    }
                    
                    // update the map
                    double weight = getAsteroidWeight(closestDistance, ast.getResources().getTotal());
                    
                    HashMap<UUID, Double> teamWeights = teamToAsteroidWeightsMap.get(closestTeamName);
                    if (teamWeights == null) {
                        teamWeights = new HashMap<UUID, Double>();
                    }
                    
                    teamWeights.put(ast.getId(), new Double(weight));
                    teamToAsteroidWeightsMap.put(closestTeamName, teamWeights);
                    
                    if (closestTeamName.equals(friendlyTeamName)) {
                        // update max uuid and weight
                        maxWeightUUID = weight > maxWeight ? ast.getId() : maxWeightUUID;
                        // maxWeight = weight > maxWeight ? weight : maxWeight;
                    }
                }
            }
        }
        previousTimeStep = space.getTimestep();
	}
    
    private double getAsteroidWeight(double distance, double value) {
        return Math.pow((value / MAXIMUM_ASTEROID_VALUE),0.75) / distance * ASTEROID_SCALE_FACTOR + ASTEROID_OFFSET_FACTOR;
    }

	public Asteroid getBestAsteroid(Toroidal2DPhysics space) {
        //System.out.println(maxWeightUUID);
        return (Asteroid) space.getObjectById(maxWeightUUID);
	}

    public double getBiasOfBestAsteroid() {
        if (maxWeightUUID == null) {
            return -1.0;
        } else if (useStdKRep) {
            return asteroidWeights.get(maxWeightUUID).doubleValue();
        } else {
            return teamToAsteroidWeightsMap.get(friendlyTeamName).get(maxWeightUUID).doubleValue();
        }
    }

    public Vector2D velocityVectorAsteroidIntercept(Toroidal2DPhysics space, Asteroid ast){
        return asteroidVelocities.get(ast.getId()).multiply(2.0f);
    }
    
    public AsteroidManager() {
        this(true);
    }
    
    public AsteroidManager(boolean useStd) {
        this.useStdKRep = useStd;
        asteroidWeights = new HashMap<UUID, Double>();
        teamToAsteroidWeightsMap = new HashMap<String, HashMap<UUID, Double>>();
        asteroidVelocities = new HashMap<UUID, Vector2D>();
        asteroidPreviousPositions = new HashMap<UUID, Position>();
    }
}