package game_engine.place_validations;

import exceptions.WompException;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Unit;


public abstract class PlaceValidation {

    private GameEngineInterface myEngine;

    public abstract boolean validate (Unit unit, double posX, double posY) throws WompException;

    public GameEngineInterface getEngine () {
        return myEngine;
    }
    
    public void setEngine(GameEngineInterface myEngine) {
        this.myEngine = myEngine;
    }

}