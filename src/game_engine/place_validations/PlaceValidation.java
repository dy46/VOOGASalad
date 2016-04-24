package game_engine.place_validations;

import game_engine.GameEngineInterface;
import game_engine.game_elements.Unit;

public abstract class PlaceValidation {
    
    public PlaceValidation() {};
    
    public abstract boolean validate(GameEngineInterface gameEngine, Unit unit, double posX, double posY);
    
}
