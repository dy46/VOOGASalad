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
 * This includes properly configuring movement (branches, position, direction) based on game
 * factors.
 * 
 * @author adamtache
 *
 */

public class AIHandler {

	private GameEngineInterface myEngine;
	private AISearcher mySearcher;
	private HashMap<Position, List<Branch>> cachedPosPaths;
	private HashMap<Branch, List<Branch>> cachedBranchPaths;
	private HashMap<Unit, List<Branch>> cachedUnitPaths;

	public AIHandler(GameEngineInterface engine){
		this.myEngine = engine;
		this.mySearcher = engine.getAISearcher();
		cachedPosPaths = new HashMap<>();
		cachedBranchPaths = new HashMap<>();
		cachedUnitPaths = new HashMap<>();
	}

	public void updateAIBranches() {
		List<Unit> activeAI = getActiveAIEnemies();
		for(Unit u : activeAI){
			List<Branch> currentPath = u.getProperties().getMovement().getBranches();
			if(currentPath.size() == 0){
				updateBranches(u);
			}
			else{
				cachedUnitPaths.put(u, currentPath);
				cachedPosPaths.put(u.getProperties().getPosition(), currentPath);
				cachedBranchPaths.put(u.getProperties().getMovement().getCurrentBranch(), currentPath);
			}
		}
	}
	
	public void updatePathMaps(HashMap<Unit, List<Branch>> unitPaths, HashMap<Branch, List<Branch>> branchPaths,
			HashMap<Position, List<Branch>> posPaths) {
		this.updateBranchPaths(branchPaths);
		this.updatePosPaths(posPaths);
		this.updateUnitPaths(unitPaths);
	}

	public void updateUnitPaths(HashMap<Unit, List<Branch>> unitPaths){
		this.cachedUnitPaths = unitPaths;
		Iterator<Unit> it = unitPaths.keySet().iterator();
		while(it.hasNext()){
			Unit next = it.next();
			next.getProperties().getMovement().setBranches(unitPaths.get(next));
		}
	}
	
	public void updateBranchPaths(HashMap<Branch, List<Branch>> branchPaths){
		this.cachedBranchPaths = branchPaths;
		Iterator<Branch> it = branchPaths.keySet().iterator();
		while(it.hasNext()){
			updateUnitsWithBranch(it.next());
		}
	}
	
	private void updateUnitsWithBranch(Branch branch){
		for(Unit u : this.getActiveAIEnemies()){
			if(u.getProperties().getMovement().getBranches().contains(branch)){
				u.getProperties().getMovement().setBranches(cachedBranchPaths.get(branch));
			}
		}
	}
	
	private void updateUnitsAtPos(Position pos){
		for(Unit u : this.getActiveAIEnemies()){
			if(u.getProperties().getPosition().equals(pos)){
				u.getProperties().getMovement().setBranches(cachedPosPaths.get(pos));
			}
		}
	}
	
	public void updatePosPaths(HashMap<Position, List<Branch>> posPaths){
		this.cachedPosPaths = posPaths;
		Iterator<Position> it = posPaths.keySet().iterator();
		while(it.hasNext()){
			updateUnitsAtPos(it.next());
		}
	}

	private void updateBranches(Unit u){
		List<Branch> newBranches = new ArrayList<>();
		Position currPos = u.getProperties().getPosition();
		if(currPos == null){
			currPos = myEngine.getLevelController().getCurrentLevel().getSpawns().get(0);
			if(currPos == null)
				return;
		}
		Branch currBranch = u.getProperties().getMovement().getCurrentBranch();
		if(cachedPosPaths.containsKey(currPos)){
			newBranches = cachedPosPaths.get(currPos);
		}
		else if(cachedBranchPaths.containsKey(currPos)){
			newBranches = cachedBranchPaths.get(currBranch);
		}
		else{
			newBranches = mySearcher.getPathToAnyGoal(currPos);
			cachedPosPaths.put(currPos, newBranches);
			cachedBranchPaths.put(currBranch, newBranches);
		}
		if(newBranches != null){
			cachedUnitPaths.put(u, newBranches);
			configureMovement(u, newBranches);
		}
	}

	private void configureMovement (Unit u, List<Branch> newBranches) {
		Movement myMovement = u.getProperties().getMovement();
		List<Branch> currentBranches = myMovement.getBranches();
		Position currentPosition = u.getProperties().getPosition();
		if (currentPosition == null) {
			return;
		}
		else if (currentBranches == null || currentBranches.size() == 0) {
			if (newBranches != null) {
				myMovement.setBranches(newBranches);
				myMovement.initializeCurrentBranch(newBranches.get(0));
				myMovement.initializeMovingTowards();
			}
			return;
		}
		if (newBranches.get(0).equals(currentBranches.get(0))) {
			if (!newBranches.get(1).equals(currentBranches.get(1))) {
				Position newBranchFirstPos = newBranches.get(1).getFirstPosition();
				Position newBranchLastPos = newBranches.get(1).getLastPosition();
				double dirToFirstPos =
						DirectionHandler.getDirection(currentPosition, newBranchFirstPos);
				double dirToLastPos =
						DirectionHandler.getDirection(currentPosition, newBranchLastPos);
				double currDir = u.getProperties().getVelocity().getDirection();
				if (currDir == dirToFirstPos || currDir == dirToLastPos)
					u.turnAround();
			}
		}
	}

	public List<Unit> getActiveAIEnemies(){
		HashSet<Unit> AI = new HashSet<>();
		List<Unit> activeEnemies = myEngine.getLevelController().getCurrentLevel().getCurrentWave().getSpawningUnitsLeft();
		List<Unit> allEnemies = myEngine.getLevelController().getCurrentLevel().getCurrentWave().getSpawningUnits();
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

	public List<Branch> getBranchesAtPos (Position pos) {
		List<Branch> branches = new ArrayList<>();
		for (Branch b : myEngine.getBranches()) {
			if(b.getPositions().contains(pos)){
				branches.add(b);
			}
		}
		return branches;
	}

	public HashMap<Unit, List<Branch>> getUnitPaths() {
		return cachedUnitPaths;
	}

	public HashMap<Branch, List<Branch>> getBranchPaths() {
		return cachedBranchPaths;
	}

	public HashMap<Position, List<Branch>> getPositionPaths() {
		return cachedPosPaths;
	}

}