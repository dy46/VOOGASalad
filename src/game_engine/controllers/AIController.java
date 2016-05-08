/**
 * This entire file is part of my masterpiece.
 * Adam Tache
 * 
 * I add this class to my masterpiece because it is the main controller that allows for AI search problems to happen within the game engine.
 * This class was created in an effort to refactor and reduce the amount of information held in the Workspace related to AI. 
 * This allows AI to be encapsulated within this class as opposed to leaking throughout the codebase where it's not needed.
 * Instead of having separate objects of various AI utilities in the Workspace, this Controller was created.
 * 
 */

package game_engine.controllers;

import game_engine.AI.AISimulator;
import game_engine.handlers.AIHandler;
import game_engine.interfaces.AIWorkspace;

/**
 * This class is a controller for Artificial Intelligence that uses various AI utilities.
 * 
 * @author adamtache
 *
 */

public class AIController{

	private AIHandler myAIHandler;
	private AISimulator myAISimulator;

	public AIController(AIWorkspace engineWorkspace) {
		this.myAIHandler = new AIHandler(engineWorkspace);
		this.myAISimulator = new AISimulator(engineWorkspace);
		this.myAIHandler.updateAIBranches();
	}

	public void updateAI() {
		this.myAIHandler.updateAIBranches();
	}

	public AISimulator getAISimulator(){
		return myAISimulator;
	}

}