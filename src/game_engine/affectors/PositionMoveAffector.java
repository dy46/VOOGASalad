package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public class PositionMoveAffector extends Affector {

//	public PositionMoveAffector (List<Double> baseNumbers, int timeToLive) {
//		super(baseNumbers, timeToLive);
//	}

	public PositionMoveAffector(List<Function> functions, int timeToLive){
		super(functions, timeToLive);
	}

	@Override
	public void apply (UnitProperties properties) {
//		if(getFunctions() == null){
//			properties.getPosition().addToXY(getBaseNumbers().get(0), 
//											getBaseNumbers().get(1));
//		}
		double speed = getFunctions().get(0).evaluate(getElapsedTime());
		double xDirection = Math.cos(getFunctions().get(0).evaluate(getElapsedTime()));
		double yDirection = Math.sin(getFunctions().get(0).evaluate(getElapsedTime()));
		properties.getPosition().addToXY(speed * xDirection, speed * yDirection);
	}

}