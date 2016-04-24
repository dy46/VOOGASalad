package game_engine.affectors;

import java.util.ArrayList;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.Property;


public class AIPathFollowAffector extends PathFollowAffector {

	public AIPathFollowAffector (AffectorData data) {
		super(data);
	}

	public List<Double> transformValues (Property p, List<Double> values) {
		return values;
	}

	public Position getNextPosition (Unit u) {
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		Branch currentBranch = move.getCurrentBranch();
		if (currentBranch == null) {
			return null;
		}
		Position next = move.getNextPosition(currentPosition);
		if (next == null) {
			if(getWS().getCurrentLevel().getGoals().contains(currentPosition)){
				return null;
			}
			currentBranch = pickBestBranch(u);
			if (currentBranch == null) {
				this.setElapsedTimeToDeath();
				u.addAffector(new RandomPathFollowAffector(new AffectorData()));
				return null;
			}
			u.getProperties().getMovement().setCurrentBranch(currentBranch, currentPosition);
			next = move.getNextPosition(currentPosition);
		}
		return next;
	}

	private Branch pickBestBranch (Unit u) {
		List<Branch> branchChoices = getValidBranchChoices(u);
		Branch bestBranch = null;
		double bestHeuristic = Integer.MAX_VALUE;
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
		return enemiesOnBranchHeuristic(b) + goalDistanceHeuristic(b) + nearbyTowersHeuristic(b);
	}

	private double enemiesOnBranchHeuristic(Branch b){
		List<Unit> currentEnemies = getWS().getCurrentLevel().getCurrentWave().getSpawningUnits();
		int numEnemiesOnBranch = 0;
		for (Unit e : currentEnemies) {
			if (e.isAlive()) {
				Position ePosition = e.getProperties().getPosition();
				if (b.getPositions().contains(ePosition)) {
					numEnemiesOnBranch++;
				}
			}
		}
		if(numEnemiesOnBranch == 0)
			return 0;
		return 1/numEnemiesOnBranch;
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
		return nearbyTowers.size();
	}

	private double goalDistanceHeuristic(Branch b){
		double minDistanceToGoal = Integer.MAX_VALUE;
		Position lastPos = b.getLastPosition();
		if(getWS().getCurrentLevel().getGoals() != null){
			for (Position goal : getWS().getCurrentLevel().getGoals()) {
				if (isAccessible(b, goal)) {
					double distanceToGoal = lastPos.distanceTo(goal);
					if (distanceToGoal < minDistanceToGoal) {
						minDistanceToGoal = distanceToGoal;
					}
				}
			}
		}
		return minDistanceToGoal;
	}

}