package game_engine.place_validations;
import java.util.List;

import auth_environment.paths.VisibilityGraph;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;

public class TowerPlaceValidation extends PlaceValidation{

	public boolean validate(GameEngineInterface gameEngine, Unit unit, double posX, double posY) {
		Unit copy = unit.copyShallowUnit();
		VisibilityGraph myVisibility = new VisibilityGraph(gameEngine);
		List<Branch> visibilityBranches = myVisibility.getSimulatedPlacementBranches(copy);
		return myVisibility.isValidMap(visibilityBranches);
	}

}