package game_engine.affectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.Property;


public class AIPathFollowAffector extends PathFollowAffector {

	public AIPathFollowAffector (AffectorData data) {
		super(data);
	}

	public List<Double> transformValues (Property p, List<Double> values) {
		return values;
	}

	public Position respondToPosition(Unit u, Position next) {
		if (next == null) {
			Position currentPosition = getCurrentPosition(u);
			if(getWS().getCurrentLevel().isGoal(currentPosition)){
				return null;
			}
			Branch currentBranch = pickBestBranch(u);
			if (currentBranch == null) {
				return currentPosition;
			}
			u.getProperties().getMovement().setBranches(Arrays.asList(currentBranch));
			next = currentBranch.getFirstPosition();
		}
		return next;
	}

	private Branch pickBestBranch (Unit u) {
		List<Branch> branchChoices = getBranchChoicesOnPath(u);
		if(branchChoices.size() == 0){
			return null;
		}
		Branch bestBranch = null;
		double bestHeuristic = Integer.MIN_VALUE;
		for (Branch b : branchChoices) {
			double branchHeuristic = branchingHeuristic(b);
			if (branchHeuristic <= bestHeuristic) {
				bestHeuristic = branchHeuristic;
				bestBranch = b;
			}
		}
		return bestBranch;
	}

	private double branchingHeuristic (Branch b) {
		return pathLengthHeuristic(b) + enemiesOnBranchHeuristic(b) + goalDistanceHeuristic(b) + nearbyTowersHeuristic(b);
	}
	
	private double enemiesOnBranchHeuristic(Branch b){
		List<Unit> currentEnemies = getWS().getCurrentLevel().getCurrentWave().getEnemies();
		int numEnemiesOnBranch = 0;
		for (Unit e : currentEnemies) {
			if (e.isAlive()) {
				Position ePosition = e.getProperties().getPosition();
				if (b.getPositions().contains(ePosition)) {
					numEnemiesOnBranch++;
				}
			}
		}
		return 0.1 * numEnemiesOnBranch;
	}
	
	private double nearbyTowersHeuristic(Branch b){
		List<Unit> currentTowers = getWS().getTowers();
		List<Unit> nearbyTowers = new ArrayList<>();
		for (Position p : b.getPositions()) {
			for (Unit t : currentTowers) {
				if (t.isAlive()) {
					Position tPosition = t.getProperties().getPosition();
					if (p.distanceTo(tPosition) < 50 && !nearbyTowers.contains(t)) {
						nearbyTowers.add(t);
					}
				}
			}
		}
		double nearbyTowersValue = 0;
		if (nearbyTowers.size() == 0)
			nearbyTowersValue = 100000;
		else
			nearbyTowersValue = 100000 / nearbyTowers.size();
		return nearbyTowersValue;
	}
	
	private double goalDistanceHeuristic(Branch b){
		double minDistanceToGoal = Integer.MAX_VALUE;
		Position lastPos = b.getLastPosition();
		if(getWS().getGoals() != null){
			for (Position goal : getWS().getGoals()) {
				if (b.isAccessible(goal)) {
					double distanceToGoal = lastPos.distanceTo(goal);
					if (distanceToGoal < minDistanceToGoal) {
						minDistanceToGoal = distanceToGoal;
					}
				}
			}
		}
		double goalValue = 0;
		if(!(minDistanceToGoal == Integer.MAX_VALUE))
			goalValue = 10 / minDistanceToGoal;
		return goalValue;
	}
	
	private double pathLengthHeuristic(Branch b){
		if(b.getLength() == 0)
			return 0;
		return 5000 / b.getLength();
	}

}