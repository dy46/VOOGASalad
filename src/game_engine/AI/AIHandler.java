package game_engine.AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
	private HashMap<Position, List<Branch>> myShortestPaths;
	private HashMap<Unit, List<Branch>> myUnitPaths;

	public AIHandler(GameEngineInterface engine){
		this.myEngine = engine;
		this.mySearcher = engine.getAISearcher();
		myShortestPaths = new HashMap<>();
		myUnitPaths = new HashMap<>();
	}

	public void updateAIBranches() {
		List<Unit> activeAI = getActiveAIEnemies();
		for(Unit u : activeAI){
			List<Branch> currentPath = u.getProperties().getMovement().getBranches();
			if(currentPath.size() == 0){
				System.out.println("ADDING BRANCHES");
				updateBranches(u);
			}
			else{
				myUnitPaths.put(u, currentPath);
			}
		}
	}
	
	public void updateAIBranches(HashMap<Unit, List<Branch>> unitPaths){
		this.myUnitPaths = unitPaths;
		Iterator<Unit> it = unitPaths.keySet().iterator();
		while(it.hasNext()){
			System.out.println("NEW BRANCH FROM UNIT PATH MAP");
			Unit next = it.next();
			next.getProperties().getMovement().setBranches(unitPaths.get(next));
		}
	}

	private void updateBranches(Unit u){
		List<Branch> newBranches = new ArrayList<>();
		Position currentPosition = u.getProperties().getPosition();
		if(currentPosition == null){
			currentPosition = myEngine.getCurrentLevel().getSpawns().get(0);
			if(currentPosition == null)
				return;
		}
		if(myShortestPaths.containsKey(currentPosition)){
			newBranches = myShortestPaths.get(currentPosition);
		}
		else{
			newBranches = mySearcher.getShortestPath(currentPosition);
			myShortestPaths.put(currentPosition, newBranches);
		}
		System.out.println("NEW BRANCHES: " + newBranches);
		if(newBranches != null){
			myUnitPaths.put(u, newBranches);
			configureMovement(u, newBranches);
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
			if(e.isAlive() && e.isAlive() && e.isVisible() && !activeEnemies.contains(e)){
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

	public HashMap<Unit, List<Branch>> getUnitPaths() {
		return myUnitPaths;
	}

}