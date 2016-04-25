package game_engine.place_validations;
import auth_environment.paths.VisibilityGraph;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class TowerPlaceValidation extends PlaceValidation{

	public boolean validate(GameEngineInterface gameEngine, Unit unit, double posX, double posY) {
		Unit copy = unit.copyShallowUnit();
		copy.getProperties().getPosition().setPosition(new Position(posX, posY));
		VisibilityGraph myVisibility = new VisibilityGraph(gameEngine);
		boolean valid = myVisibility.simulateEnemyPathFollowing(copy);
		System.out.println("VALID: " + valid);
		return valid;
	}

}