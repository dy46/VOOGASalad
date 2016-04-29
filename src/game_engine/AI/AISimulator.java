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

	public boolean simulateTowerPlacement(Unit obstacle) {
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches(obstacle);
		if(!myAISearcher.isValidSearchProblem(visibilityBranches)){
			return false;
		}
		HashMap<Unit, List<Branch>> newShortestPaths = new HashMap<>();
		for(Unit e : myAIHandler.getActiveAIEnemies()){
			Position currPos = e.getProperties().getPosition();
			for(Position goal : myEngine.getLevelController().getCurrentLevel().getGoals()){
				List<Branch> newShortestPath = myAISearcher.getBFSPath(currPos, goal, visibilityBranches);
				if(newShortestPath == null || simulatedCollision(e, newShortestPath, obstacle)){
					return false;
				}
				else{
					newShortestPaths.put(e, newShortestPath);
				}
			}
		}
		myAIHandler.getActiveAIEnemies().stream().forEach(e -> e.getProperties().getMovement().setBranches(newShortestPaths.get(e)));
		return true;
	}

	private boolean simulatedCollision (Unit enemy, List<Branch> newBranches, Unit obstacle) {
		List<Unit> obstacles = myEngine.getUnitController().getUnitType("Tower");
		List<Unit> obstaclesCopy =
				obstacles.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
		obstaclesCopy.add(obstacle.copyShallowUnit());
		for (Branch b : newBranches) {
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