package game_engine.place_validations;

import game_engine.GameEngineInterface;
import game_engine.AI.AISimulator;
import game_engine.game_elements.Unit;

public abstract class PlaceValidation {
    
	private AISimulator mySimulator;
	private GameEngineInterface myEngine;
	
    public PlaceValidation(GameEngineInterface gameEngine) {
    	this.myEngine = gameEngine;
    	this.mySimulator = new AISimulator(gameEngine);
    };
    
    public abstract boolean validate(Unit unit, double posX, double posY);
    
    public GameEngineInterface getEngine(){
    	return myEngine;
    }
    
    public AISimulator getAISimulator(){
    	return mySimulator;
    }
    
}