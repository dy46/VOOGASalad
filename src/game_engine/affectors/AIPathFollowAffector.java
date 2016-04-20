package game_engine.affectors;

import java.util.ArrayList;
import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.Property;

public class AIPathFollowAffector extends PathFollowAffector{

	public AIPathFollowAffector(AffectorData data) {
		super(data);
	}
	
	public List<Double> transformValues(Property p, List<Double> values){
		return values;
	}

	public Position getNextPosition(Unit u){
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		Branch currentBranch = move.getCurrentBranch();
		if(currentBranch == null){
			getWS().decrementLives();
			return null;
		}
		Position next = currentBranch.getNextPosition(currentPosition);
		if(next == null){
			currentBranch = pickBestBranch(u);
			u.getProperties().getMovement().setCurrentBranch(currentBranch);
			if(currentBranch == null){
				getWS().decrementLives();
				return null;
			}
			next = currentBranch.getFirstPosition();
		}
		return next;
	}

	private Branch pickBestBranch(Unit u){
		List<Branch> branchChoices = getBranchChoices(u);
		Branch bestBranch = null;
		double bestHeuristic = Integer.MIN_VALUE;
		for(Branch b : branchChoices){
			double branchHeuristic = branchingHeuristic(b);
			if(branchHeuristic >= bestHeuristic){
				bestHeuristic = branchHeuristic;
				bestBranch = b;
			}
		}
		return bestBranch;
	}

	private double branchingHeuristic(Branch b){
		int pathLength = b.getLength();
		List<Position> branchPositions = b.getAllPositions();
		List<Unit> currentEnemies = getWS().getCurrentLevel().getCurrentWave().getEnemies();
		int numEnemiesOnBranch = 0;
		for(Unit e : currentEnemies){
			if(e.isAlive()){
				Position ePosition = e.getProperties().getPosition();
				if(branchPositions.contains(ePosition)){
					numEnemiesOnBranch++;
				}
			}
		}
		List<Unit> currentTowers = getWS().getTowers();
		List<Unit> nearbyTowers = new ArrayList<>();
		for(Position p : branchPositions){
			for(Unit t : currentTowers){
				if(t.isAlive()){
					Position tPosition = t.getProperties().getPosition();
					if(p.distanceTo(tPosition) < 50 && !nearbyTowers.contains(t)){
						nearbyTowers.add(t);
					}
				}
			}
		}
		double minDistanceToGoal = Integer.MAX_VALUE;
		Position lastPos = b.getLastPosition();
		for(Position goal : getWS().getGoals()){
			if(b.isAccessible(goal)){
				double distanceToGoal = lastPos.distanceTo(goal);
				if(distanceToGoal < minDistanceToGoal){
					minDistanceToGoal = distanceToGoal;
				}
			}
		}
		double goalValue, pathValue, enemiesOnBranchValue, nearbyTowersValue;
		pathValue = 5000/pathLength;
		enemiesOnBranchValue = 0.1 * numEnemiesOnBranch;
		if(nearbyTowers.size() == 0)
			nearbyTowersValue = 100000;
		else
			nearbyTowersValue = 100000/nearbyTowers.size();
		if(minDistanceToGoal == Integer.MAX_VALUE)
			goalValue = 0;
		else
			goalValue = 10/minDistanceToGoal;
		double heuristic = pathValue + enemiesOnBranchValue + goalValue + nearbyTowersValue;
		return heuristic;
	}

}