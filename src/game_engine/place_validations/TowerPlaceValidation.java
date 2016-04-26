package game_engine.place_validations;

import game_engine.GameEngineInterface;
import game_engine.AI.AISimulator;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;


/**
 * This class checks for valid tower placement using an AI Simulator to run a simulated enemy path
 * following obstacle test after adding an obstacle (the tower being placed).
 * 
 * @author adamtache
 *
 */

public class TowerPlaceValidation extends PlaceValidation {

    private AISimulator mySimulator;

    public boolean validate (Unit unit, double posX, double posY) {
        Unit copy = unit.copyShallowUnit();
        copy.getProperties().setPosition(new Position(posX, posY));
        boolean simulation = mySimulator.simulateEnemyPathFollowing(copy);
        return simulation;
    }

    public void setEngine (GameEngineInterface myEngine) {
        super.setEngine(myEngine);
        this.mySimulator = new AISimulator(myEngine);
    }

}
