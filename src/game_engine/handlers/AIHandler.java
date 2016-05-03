package game_engine.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import exceptions.WompException;
import game_engine.AI.BFSSearcher;
import game_engine.affectors.AIPathFollowAffector;
import game_engine.affectors.Affector;
import game_engine.controllers.LevelController;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.interfaces.AIWorkspace;
import game_engine.properties.Position;


/**
 * This class handles configuration of artificially intelligent Units for a game engine.
 * This includes properly configuring movement (branches, position, direction) based on game
 * factors.
 * 
 * @author adamtache
 *
 */

public class AIHandler{

	private BFSSearcher mySearcher;
	private LevelController myLevelController;

	public AIHandler(AIWorkspace engine){
		this.mySearcher = new BFSSearcher(engine);
		this.myLevelController = engine.getLevelController();
	}

	public void updateAIBranches() {
		List<Unit> activeAI = null;
		try {
			activeAI = myLevelController.getActiveUnitsWithAffector(Class.forName("game_engine.affectors.PathFollowPositionMoveAffector"));
		} catch (ClassNotFoundException e) {
			new WompException("AIPathFollowAffector class missing.").displayMessage();
		}
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
			currPos = myLevelController.getCurrentLevel().getSpawns().get(0);
		}
		newBranches = mySearcher.getPath(currPos);
		if(newBranches != null){
			u.getProperties().getMovement().setBranches(newBranches);
		}
	}

}