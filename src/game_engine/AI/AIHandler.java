package game_engine.AI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.GameEngineInterface;
import game_engine.affectors.AIPathFollowAffector;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
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

	public AIHandler(GameEngineInterface engine){
		this.myEngine = engine;
		this.mySearcher = engine.getAIController().getAISearcher();
	}

	public void updateAIBranches() {
		List<Unit> activeAI = getActiveAIEnemies();
		for(Unit u : activeAI){
			List<Branch> currentPath = u.getProperties().getMovement().getBranches();
			if(currentPath.size() == 0){
				updateBranches(u);
			}
		}
	}

	private void updateBranches(Unit u){
		List<Branch> newBranches = new ArrayList<>();
		Position currPos = u.getProperties().getPosition();
		if(currPos == null){
			currPos = myEngine.getLevelController().getCurrentLevel().getSpawns().get(0);
		}
		newBranches = mySearcher.getPath(currPos);
		if(newBranches != null){
			u.getProperties().getMovement().setBranches(newBranches);
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
		for (Branch b : myEngine.getLevelController().getCurrentBranches()) {
			if(b.getPositions().contains(pos)){
				branches.add(b);
			}
		}
		return branches;
	}

	public Branch getGoalBranch(Position goal) {
		List<Branch> branches = getBranchesAtPos(goal);
		if(branches.size() == 0)
			return null;
		return branches.stream().filter(b -> b.getPositions().size() == 1).collect(Collectors.toList()).get(0);
	}

}