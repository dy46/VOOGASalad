package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public abstract class PositionMoveAffector extends Affector {
	private int currFunc;
	public PositionMoveAffector(List<Function> functions){
		super(functions);
		currFunc = 0;
	}

	@Override
	public void apply (UnitProperties properties) {
		super.apply(properties);	
//		double direction = Math.toRadians(properties.getVelocity().getDirection());
//		double speed = properties.getVelocity().getSpeed() + getFunctions().get(0).evaluate(getElapsedTime());
//		double xDirection = Math.sin(direction) + getFunctions().get(0).evaluate(getElapsedTime());
//		double yDirection = Math.cos(direction) + getFunctions().get(0).evaluate(getElapsedTime());
//		properties.getPosition().addToXY(speed * xDirection, speed * yDirection);
		double speed = properties.getVelocity().getSpeed();
		double x = properties.getPosition().getX();
		int iter = (int)(speed*1000);
		while(iter > 0 && currFunc < 1){
			double dx = getFunctions().get(currFunc).getDX();
			if(getFunctions().get(currFunc).atDomainEnd(x)){
				currFunc++;
				continue;
			}
			if((x-getFunctions().get(currFunc).getDomainEnd())/(x+dx-getFunctions().get(currFunc).getDomainEnd())>0){
				x += dx;
			}
			else{
				x = getFunctions().get(currFunc).getDomainEnd();
			}
			iter--;
		}
		double oldX = properties.getPosition().getX();
		double oldY = properties.getPosition().getY();
		double y = getFunctions().get(currFunc).evaluate((int)x); // accepts int?
		properties.getPosition().addToXY(x-oldX, y-oldY);
		
	}

}