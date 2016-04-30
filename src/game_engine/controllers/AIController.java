package game_engine.controllers;

import game_engine.GameEngineInterface;
import game_engine.AI.AIHandler;
import game_engine.AI.AISearcher;
import game_engine.AI.AISimulator;

public class AIController {

	private GameEngineInterface myEngine;
	private AIHandler myAIHandler;
	private AISimulator myAISimulator;
	private AISearcher myAISearcher;

	public AIController(GameEngineInterface engineWorkspace) {
		this.myEngine = engineWorkspace;
		this.myAISearcher = new AISearcher(engineWorkspace);
		this.myAIHandler = new AIHandler(engineWorkspace);
		this.myAISimulator = new AISimulator(engineWorkspace);
		this.myAIHandler.updateAIBranches();
	}
	
	public void updateAI(){
		this.myAIHandler.updateAIBranches();
	}
	
	public  AIHandler getAIHandler(){
		return myAIHandler;
	}
	
	public AISimulator getAISimulator(){
		return myAISimulator;
	}
	
	public AISearcher getAISearcher(){
		return myAISearcher;
	}
	
	public GameEngineInterface getEngine(){
		return myEngine;
	}

}