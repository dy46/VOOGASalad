package game_engine.place_validations;
import java.util.List;

import auth_environment.paths.VisibilityGraph;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class TowerPlaceValidation extends PlaceValidation{

	public boolean validate(GameEngineInterface gameEngine, Unit unit, double posX, double posY) {
		Unit copy = unit.copyShallowUnit();
		copy.getProperties().getPosition().setPosition(new Position(posX, posY));
		VisibilityGraph myVisibility = new VisibilityGraph(gameEngine);
		List<Branch> visibilityBranches = myVisibility.getSimulatedPlacementBranches(copy);
		boolean validMap = myVisibility.isValidMap(visibilityBranches);
		if(!validMap){
			System.out.println("NOT VALID MAP");
			return false;
		}
		System.out.println("VALID MAP");
		boolean simulationValid = myVisibility.simulateEnemyPathFollowing(visibilityBranches, unit);
		System.out.println("SIMULATION VALIDITY: " + simulationValid);
		if(simulationValid){
			gameEngine.updateAIBranches();
		}
		return simulationValid;
	}

}