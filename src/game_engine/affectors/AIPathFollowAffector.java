package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;

public class AIPathFollowAffector extends PathFollowAffector{

	public AIPathFollowAffector(List<Function> functions) {
		super(functions);
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

	public Double getNextDirection(Unit u){
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		if(currentPosition.equals(move.getLastBranch().getLastPosition())) {
			// END OF PATH
			return u.getProperties().getVelocity().getDirection();
		}
		return move.getCurrentBranch().getNextDirection(currentPosition);
	}

	private Branch pickBestBranch(Unit u){
		List<Branch> branchChoices = u.getProperties().getMovement().getCurrentBranch().getForwardNeighbors();
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
		List<Enemy> currentEnemies = getWS().getCurrentLevel().getCurrentWave().getEnemies();
		int numEnemiesOnBranch = 0;
		for(Enemy e : currentEnemies){
			if(e.isAlive()){
				Position ePosition = e.getProperties().getPosition();
				if(branchPositions.contains(ePosition)){
					numEnemiesOnBranch++;
				}
			}
		}
		int numNearbyTowers = 0;
		List<Unit> currentTowers = getWS().getTowers();
		for(Position p : branchPositions){
			for(Unit t : currentTowers){
				if(t.isAlive()){
					Position tPosition = t.getProperties().getPosition();
					if(p.distanceTo(tPosition) < 300){
						numNearbyTowers++;
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
		double pathValue = 0.01*pathLength;
		double enemiesOnBranchValue = 5*numEnemiesOnBranch;
		double nearbyTowersValue = 20*numNearbyTowers;
		double goalValue = 10*minDistanceToGoal;
		return pathValue + enemiesOnBranchValue + goalValue;
	}

}