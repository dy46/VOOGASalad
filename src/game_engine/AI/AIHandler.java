package game_engine.AI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import game_engine.GameEngineInterface;
import game_engine.affectors.AIPathFollowAffector;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.physics.DirectionHandler;
import game_engine.properties.Movement;
import game_engine.properties.Position;

/**
 * This class handles configuration of artificially intelligent Units for a game engine.
 * This includes properly configuring movement (branches, position, direction) based on game factors.
 * @author adamtache
 *
 */

public class AIHandler {

	private GameEngineInterface myEngine;
	private AISearcher mySearcher;

	public AIHandler(GameEngineInterface engine){
		this.myEngine = engine;
		this.mySearcher = new AISearcher(engine);
	}

	public void updateAIBranches() {
		List<Unit> activeAI = getActiveAIEnemies();
		for(Unit u : activeAI){
			if(u.getProperties().getMovement().getCurrentBranch() == null){
				Position currentPosition = u.getProperties().getPosition();
				List<Branch> newBranches = mySearcher.getShortestPath(currentPosition);
				configureMovement(u, newBranches);
			}
		}
		for(Unit u : activeAI){
			List<Branch> shortestPath = mySearcher.getShortestPath(u.getProperties().getPosition());
			if(shortestPath != null){
				configureMovement(u, shortestPath);
			}
		}
	}

	private void configureMovement(Unit u, List<Branch> newBranches) {
		Movement myMovement = u.getProperties().getMovement();
		List<Branch> currentBranches = myMovement.getBranches();
		Position currentPosition = u.getProperties().getPosition();
		myMovement.setBranches(newBranches, currentPosition);
		if(currentBranches.size() == 0){
			return;
		}
		if(newBranches.get(0).equals(currentBranches.get(0))){
			if(!newBranches.get(1).equals(currentBranches.get(1))){
				Position newBranchFirstPos = newBranches.get(1).getFirstPosition();
				Position newBranchLastPos = newBranches.get(1).getLastPosition();
				double dirToFirstPos = DirectionHandler.getDirectionBetween(currentPosition, newBranchFirstPos);
				double dirToLastPos = DirectionHandler.getDirectionBetween(currentPosition, newBranchLastPos);
				double currDir = u.getProperties().getVelocity().getDirection();
				if(currDir == dirToFirstPos || currDir == dirToLastPos)
					u.turnAround();
			}
		}
	}

	public List<Unit> getActiveAIEnemies(){
		HashSet<Unit> AI = new HashSet<>();
		List<Unit> activeEnemies = myEngine.getCurrentLevel().getCurrentWave().getSpawningUnitsLeft();
		List<Unit> allEnemies = myEngine.getCurrentLevel().getCurrentWave().getSpawningUnits();
		for(Unit e : allEnemies){
			if(e.isAlive() && !activeEnemies.contains(e)){
				activeEnemies.add(e);
			}
		}
		for(Unit e : activeEnemies){
			for(Affector a : e.getAffectors()){
				if(a instanceof AIPathFollowAffector){
					AI.add(e);
				}
			}
		}
		return new ArrayList<>(AI);
	}

	public Branch findBranchForPos(Position pos) {
		for(Branch b : myEngine.getBranches()){
			for(Position p : b.getPositions()){
				if(p.equals(pos)){
					return b;
				}
			}
		}
		for(Branch b : myEngine.getBranches()){
			for(Position p : b.getPositions()){
				if(p.roughlyEquals(pos)){
					return b;
				}
			}
		}
		return null;
	}

}