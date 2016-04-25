package game_engine.AI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import game_engine.GameEngineInterface;
import game_engine.affectors.AIPathFollowAffector;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
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
				Position curr = u.getProperties().getPosition();
				u.getProperties().getMovement().initializeCurrentBranch(findBranchForPos(curr), curr, u.getProperties().getVelocity().getDirection());
			}
		}
		VisibilityHandler myVisibility = new VisibilityHandler(myEngine);
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches();
		for(Unit u : activeAI){
			List<Branch> shortestPath = mySearcher.getShortestPath(u.getProperties().getPosition(), visibilityBranches);
			if(shortestPath != null){
				u.getProperties().getMovement().setBranches(shortestPath, u.getProperties().getPosition(), u.getProperties().getVelocity().getDirection());
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