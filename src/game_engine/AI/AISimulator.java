package game_engine.AI;

import java.util.List;
import java.util.stream.Collectors;

import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.physics.CollisionChecker;
import game_engine.properties.Position;

/**
 * This class is a utility Artificial Intelligence simulator that allows for simulated search problems using visibility graphs and the AI searcher.
 * A simulated search problem runs a simulation on copies of game engine objects and tests for collisions and valid path finding.
 * @author adamtache
 *
 */

public class AISimulator {

	private GameEngineInterface myEngine;
	private AISearcher myAISearcher;
	private VisibilityHandler myVisibility;
	private AIHandler myAIHandler;

	public AISimulator(GameEngineInterface engine){
		this.myEngine = engine;
		this.myAISearcher = new AISearcher(engine);
		this.myAIHandler = new AIHandler(engine);
		this.myVisibility = new VisibilityHandler(engine);
	}

	public boolean simulateEnemyPathFollowing(Unit obstacle) {
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches(obstacle);
		List<Unit> enemies = myAIHandler.getActiveAIEnemies();
		for(Unit e : enemies){
			Position current = e.getProperties().getPosition();
			for(Position goal : myEngine.getCurrentLevel().getGoals()){
				List<Branch> shortestPath = myAISearcher.getShortestPathToGoal(current, goal, visibilityBranches);
				if(shortestPath != null){
					if(simulateEnemyBranchCollisions(e, shortestPath, obstacle)){
						return false;
					}
				}
				else{
					return false;
				}
			}
		}
		return true;
	}

	private boolean simulateEnemyBranchCollisions(Unit enemy, List<Branch> pathBranches, Unit obstacle){
		List<Unit> obstacles = myEngine.getTowers();
		List<Unit> obstaclesCopy = obstacles.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
		obstaclesCopy.add(obstacle.copyShallowUnit());
		for(Branch b : pathBranches){
			for(Position pos : b.getPositions()){
				List<Position> enemyBounds = CollisionChecker.getUseableBounds(enemy.getProperties().getBounds(), pos);
				if(CollisionChecker.simulatedObstacleCollisionCheck(enemyBounds, obstaclesCopy)){
					return true;
				}
			}
		}
		return false;
	}

}