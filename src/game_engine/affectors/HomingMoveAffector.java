package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.game_elements.Path;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;

public class HomingMoveAffector extends PositionMoveAffector{

	private Unit trackedUnit;
	
	public HomingMoveAffector(List<Function> functions, Unit tracked) {
		super(functions);
		this.trackedUnit = tracked;
	}
	
	public void apply (UnitProperties properties) {
		double speed = properties.getVelocity().getSpeed();
		for(int i=0; i<speed; i++){
			Position trackedPos = trackedUnit.getProperties().getPosition();
			Position currPos = properties.getPosition();
			double currX = currPos.getX();
			double currY = currPos.getY();
			double dx = Math.sin(currX - trackedPos.getX());
			double dy = Math.sin(currY - trackedPos.getY());
			double newDir = Math.toDegrees(Math.atan((dy)/(dx)));
			properties.getVelocity().setDirection(newDir);
			properties.getPosition().setX(currX + Math.cos(newDir));
			properties.getPosition().setY(currY + Math.sin(newDir));
		}
	}

}