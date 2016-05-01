package game_engine.place_validations;

import game_engine.game_elements.Unit;
import game_engine.properties.Position;

/**
 * This class checks for valid tower placement using an AI Simulator to run a simulated enemy path
 * following obstacle test after adding an obstacle at the position selected (the tower being placed).
 * 
 * This incldues using maximum bounding distance from the largest current active Unit to avoid its collision.
 * 
 * @author adamtache
 *
 */

public class TowerPlaceValidation extends PlaceValidation {

    public boolean validate (Unit unit, double posX, double posY) {
        Unit copy = unit.copyShallowUnit();
        copy.getProperties().setPosition(new Position(posX, posY));
        return getEngine().getAIController().getAISimulator().simulateTowerPlacement(copy);
    }

}