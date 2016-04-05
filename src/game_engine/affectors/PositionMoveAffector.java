package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public abstract class PositionMoveAffector extends Affector {

	public PositionMoveAffector(List<Function> functions, int TTL){
		super(functions, TTL);
	}
	
	@Override
	public void apply (UnitProperties properties) {
//		if(getFunctions() == null){
//			properties.getPosition().addToXY(getBaseNumbers().get(0), 
//											getBaseNumbers().get(1));
//		}
		double speed = properties.getVelocity().getSpeed() + getFunctions().get(0).evaluate(getElapsedTime());
		double xDirection = properties.getVelocity().getDirection() + Math.cos(getFunctions().get(0).evaluate(getElapsedTime()));
		double yDirection = properties.getVelocity().getDirection() + Math.sin(getFunctions().get(0).evaluate(getElapsedTime()));
		properties.getPosition().addToXY(speed * xDirection, speed * yDirection);
		updateElapsedTime();
	}
	
}