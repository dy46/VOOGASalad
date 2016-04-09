package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public abstract class PositionMoveAffector extends Affector {

	public PositionMoveAffector(List<Function> functions){
		super(functions);
	}
	
	@Override
	public void apply (UnitProperties properties) {
	        super.apply(properties);	
	        double direction = Math.toRadians(properties.getVelocity().getDirection());
		double speed = properties.getVelocity().getSpeed() + getFunctions().get(0).evaluate(getElapsedTime());
		double xDirection = Math.sin(direction) + getFunctions().get(0).evaluate(getElapsedTime());
		double yDirection = Math.cos(direction) + getFunctions().get(0).evaluate(getElapsedTime());
		properties.getPosition().addToXY(speed * xDirection, speed * yDirection);
	}
	
}