package game_engine.AI;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.physics.CollisionDetector;
import game_engine.properties.Position;


/**
 * This class is a utility Artificial Intelligence simulator that allows for simulated search
 * problems using visibility graphs and the AI searcher.
 * A simulated search problem runs a simulation on copies of game engine objects and tests for
 * collisions and valid path finding.
 * 
 * @author adamtache
 *
 */

public class AISimulator {

	private GameEngineInterface myEngine;
	private AISearcher myAISearcher;
	private AIHandler myAIHandler;
	private VisibilityHandler myVisibility;
	private CollisionDetector collisionDetector;

	public AISimulator(GameEngineInterface engine){
		this.myEngine = engine;
		this.myAISearcher = engine.getAISearcher();
		this.myAIHandler = engine.getAIHandler();
		this.myVisibility = new VisibilityHandler(engine);
		collisionDetector = new CollisionDetector(engine);
	}

	public boolean simulateEnemyPathFollowing(Unit obstacle) {
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches(obstacle);
		HashMap<Branch, List<Branch>> newBranchPaths = new HashMap<>();
		HashMap<Position, List<Branch>> newPosPaths = new HashMap<>();
		HashMap<Unit, List<Branch>> newUnitPaths = new HashMap<>();
		HashMap<Branch, List<Branch>> cachednewBranchPaths = myAIHandler.getBranchPaths();
		HashMap<Position, List<Branch>> cachednewPosPaths = myAIHandler.getPositionPaths();
		HashMap<Unit, List<Branch>> cachednewUnitPaths = myAIHandler.getUnitPaths();
		if(!myAISearcher.isValidSearchProblem(visibilityBranches)){
			return false;
		}
		for(Unit e : myAIHandler.getActiveAIEnemies()){
			Position currPos = e.getProperties().getPosition();
			Branch currBranch = e.getProperties().getMovement().getCurrentBranch();
			List<Branch> oldShortestPath = cachednewUnitPaths.get(e);
			for(Position goal : myEngine.getLevelController().getCurrentLevel().getGoals()){
				if(continueSearch(oldShortestPath, visibilityBranches)){
					List<Branch> cachedBranchPath = cachednewBranchPaths.get(currBranch);
					if(continueSearch(cachedBranchPath, visibilityBranches)){
						List<Branch> cachedPosPath = cachednewPosPaths.get(currPos);
						if(continueSearch(cachedPosPath, visibilityBranches)){
							List<Branch> newShortestPath = myAISearcher.getShortestPathToGoal(currPos, goal, visibilityBranches);
							if(newShortestPath == null || simulateEnemyBranchCollisions(e, newShortestPath, obstacle)){
								return false;
							}
							else{
								newPosPaths.put(currPos, newShortestPath);
								newBranchPaths.put(currBranch, newShortestPath);
								newUnitPaths.put(e, newShortestPath);
							}
						}
						else{
							newPosPaths.put(currPos, cachedBranchPath);
						}
					}
					else{
						newBranchPaths.put(currBranch, cachedBranchPath);
					}
				}
				else{
					newUnitPaths.put(e, oldShortestPath);
				}
			}
		}
		myAIHandler.updatePathMaps(newUnitPaths, newBranchPaths, newPosPaths);
		return true;
	}

	private boolean continueSearch(List<Branch> path, List<Branch> visibility){
		return path == null || !myAISearcher.isValidSearchProblem(path, visibility);
	}

	private boolean simulateEnemyBranchCollisions (Unit enemy,
			List<Branch> pathBranches,
			Unit obstacle) {
		List<Unit> obstacles = myEngine.getUnitController().getUnitType("Tower");
		List<Unit> obstaclesCopy =
				obstacles.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
		obstaclesCopy.add(obstacle.copyShallowUnit());
		for (Branch b : pathBranches) {
			for (Position pos : b.getPositions()) {
				List<Position> enemyBounds =
						enemy.getProperties().getBounds().getUseableBounds(pos);
				if (collisionDetector.simulatedObstacleCollisionCheck(enemyBounds, obstaclesCopy)) {
					return true;
				}
			}
		}
		return false;
	}

}