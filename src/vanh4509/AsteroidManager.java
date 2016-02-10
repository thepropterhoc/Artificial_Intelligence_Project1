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

/**
 * Manages the bias value for every asteroid in the game field.
 *
 * Handles two representations of knowledge, one propositional
 * model that looks at all asteroids as potential candidates for
 * mining, and another relational representation that only
 * considers asteroids we're closest to.
 * 
 * @param useStdKRep changes the knowledge representation used during play
 * 
 * @param friendlyTeamName our team name. Used in alternate KR
 * 
 * @param asteroidWeights map for standard KR. tracks all asteroids and
 * their bias values, denoted as weights
 * 
 * @param teamToAsteroidWeightsMap map for the alternate KR. tracks asteroids
 * we're closest to
 * 
 * @param maxWeightUUID stores the UUID of the most valuable asteroid tracked
 * 
 * ASTEROID_SCALE_FACTOR and  ASTEROID_OFFSET_FACTOR are used to tweak how often
 * the agent goes to an asteroid instead of a base or a beacon
 */
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
    
    /**
     * Updates the weights of each asteroid based on KR used.
     * 
     * The weight of each asteroid is determined by its distance
     * from the current ship and its value.
     * 
     * @param space the current simulation
     * @param ship the current ship
     */
	public void updateWeights(Toroidal2DPhysics space, Ship ship) {
        double maxWeight = -1.0;

        maxWeightUUID = null;
        friendlyTeamName = ship.getTeamName();
        
        // scrub the hashmaps and start over
        asteroidWeights.clear();
        teamToAsteroidWeightsMap.clear();
        
        if (useStdKRep) {
            for (Asteroid ast : space.getAsteroids()) {
                // weight is value / distance. only consider stationary asteroids
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
                    
                    // we only care if we're the closest
                    if (friendlyTeamName.equals(closestTeamName)) {
                        // update max uuid and weight
                        maxWeightUUID = weight > maxWeight ? ast.getId() : maxWeightUUID;
                        teamToAsteroidWeightsMap.put(ast.getId(), weight);
                    }
                }
            }
        }
	}

    /**
     * Returns the asteroid with the highest weight
     * as determined by updateWeights.
     * 
     * @param space the current simulation
     */
	public Asteroid getBestAsteroid(Toroidal2DPhysics space) {
        //System.out.println(maxWeightUUID);
        return (Asteroid) space.getObjectById(maxWeightUUID);
	}

    /**
     * Returns the bias value of the best asteroid
     * as determined by updateWeights.
     */
    public double getBiasOfBestAsteroid(){
        if(maxWeightUUID == null){
            return -1.0;
        } else {
            return teamToAsteroidWeightsMap.get(maxWeightUUID).doubleValue();
        }
    }
    
    /**
     * Calculates the weight of an asteroid given its distance and value
     * 
     * @param distance the distance from the ship
     * @param value the total number of resources on the asteroid
     */
    private double getAsteroidWeight(double distance, double value) {
        return (value / MAXIMUM_ASTEROID_VALUE) / distance * ASTEROID_SCALE_FACTOR + ASTEROID_OFFSET_FACTOR;
    }
    
    /**
     * Default constructor. Uses standard KR.
     */
    public AsteroidManager() {
        this(true);
    }
    
    /**
     * Constructor. Allows you to switch between KRs.
     * 
     * @param useStd whether you want to use the standard knowledge representation
     */
    public AsteroidManager(boolean useStd) {
        this.useStdKRep = useStd;
        asteroidWeights = new HashMap<UUID, Double>();
        teamToAsteroidWeightsMap = new HashMap<UUID, Double>();
    }
}