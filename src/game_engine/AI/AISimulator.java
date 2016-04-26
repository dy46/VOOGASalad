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
		this.myAISearcher = new AISearcher(engine);
		this.myAIHandler = engine.getAIHandler();
		this.myVisibility = new VisibilityHandler(engine);
		collisionDetector = new CollisionDetector(engine);
	}

	public boolean simulateEnemyPathFollowing(Unit obstacle) {
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches(obstacle);
		List<Unit> enemies = myAIHandler.getActiveAIEnemies();
		HashMap<Unit, List<Branch>> unitPaths = new HashMap<>();
		HashMap<Unit, List<Branch>> oldUnitPaths = myAIHandler.getUnitPaths();
		for(Unit e : enemies){
			Position current = e.getProperties().getPosition();
			List<Branch> oldShortestPath = oldUnitPaths.get(e);
			for(Position goal : myEngine.getLevelController().getCurrentLevel().getGoals()){
				if(!myAISearcher.isValidSearchProblem(oldShortestPath, visibilityBranches)){
					System.out.println("OLD PATH NOT VALID ANYMORE");
					List<Branch> newShortestPath = myAISearcher.getShortestPathToGoal(current, goal, visibilityBranches);
					if(newShortestPath != null){
						if(simulateEnemyBranchCollisions(e, newShortestPath, obstacle)){
							return false;
						}
						else{
							unitPaths.put(e, newShortestPath);
						}
					}
					else{
						return false;
					}
				}
				else{
					System.out.println("OLD PATH STILL VALID");
					unitPaths.put(e, oldShortestPath);
				}
			}
		}
		myAIHandler.updateAIBranches(unitPaths);
		return true;
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
