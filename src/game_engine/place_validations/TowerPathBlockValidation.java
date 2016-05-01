package game_engine.place_validations;

import java.util.Arrays;
import java.util.List;

import exceptions.WompException;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class TowerPathBlockValidation extends PlaceValidation {

	@Override
	public boolean validate(Unit unit, double posX, double posY) {
		Unit copy = unit.copyShallowUnit();
		copy.getProperties().setPosition(new Position(posX, posY));
		return blocksPath(unit);
	}

	private boolean blocksPath(Unit tower){
		List<Unit> pathFollowUnits = null;
		try {
			pathFollowUnits = getEngine().getLevelController().getActiveUnitsWithAffector(Class.forName("PathFollowPositionMoveAffector"));
		} catch (ClassNotFoundException e) {
			new WompException("PathFollowPositionMoveAffector not found").displayMessage();
		}
		for(Unit pathFollower : pathFollowUnits){
			for (Branch b : pathFollower.getProperties().getMovement().getBranches()) {
				for (Position pos : b.getPositions()) {
					List<Position> enemyBounds =
							pathFollower.getProperties().getBounds().getUseableBounds(pos);
					if (getEngine().getCollisionDetector().simulatedObstacleCollisionCheck(enemyBounds, Arrays.asList(pathFollower))) {
						return true;
					}
				}
			}
		}
		return false;
	}

}