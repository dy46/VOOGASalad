package game_engine.AI;

import java.util.ArrayList;
import java.util.HashMap;
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
		HashMap<Position, List<Branch>> pathMap = new HashMap<>();
		for(Unit u : activeAI){
			Position currentPosition = u.getProperties().getPosition();
			List<Branch> newBranches = new ArrayList<>();
			if(currentPosition == null){
				currentPosition = myEngine.getCurrentLevel().getSpawns().get(0);
				if(currentPosition == null)
					return;
			}
			if(pathMap.containsKey(currentPosition)){
				newBranches = pathMap.get(currentPosition);
			}
			else{
				newBranches = mySearcher.getShortestPath(currentPosition);
				pathMap.put(currentPosition, newBranches);
			}
			if(newBranches != null){
				configureMovement(u, newBranches);
			}
		}
	}

	private void configureMovement(Unit u, List<Branch> newBranches) {
		Movement myMovement = u.getProperties().getMovement();
		List<Branch> currentBranches = myMovement.getBranches();
		Position currentPosition = u.getProperties().getPosition();
		if(currentPosition == null){
			return;
		}
		else if(currentBranches == null || currentBranches.size() == 0){
			if(newBranches != null){
				myMovement.setBranches(newBranches);
				myMovement.initializeCurrentBranch(newBranches.get(0));
				myMovement.initializeMovingTowards();
			}
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

	public List<Branch> getBranchesAtPos(Position pos) {
		List<Branch> branches = new ArrayList<>();
		for(Branch b : myEngine.getBranches()){
			for(Position p : b.getPositions()){
				if(p.equals(pos)){
					branches.add(b);
				}
			}
		}
		return branches;
	}

}