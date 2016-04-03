package game_engine.properties;

import game_engine.functions.Function;

public class Velocity extends Property{

	private double mySpeed;
	private double myDirection;
	private Function speedFunction;
	private Function directionFunction;

	public Velocity (double speed, double direction) {
		this.mySpeed = speed;
		this.myDirection = direction;
	}

	public Velocity(Function speedFunction, Function directionFunction){
		this.speedFunction = speedFunction;
		this.directionFunction = directionFunction;
	}

	public double getSpeed () {
		return mySpeed;
	}

	public void setSpeed (double speed) {
		this.mySpeed = speed;
	}

	public void setDirection(double direction) {
		this.myDirection = direction;
	}

	public double getDirection () {
		return myDirection;
	}

	public Function getSpeedFunction(){
		return speedFunction;
	}

	public Function getDirectionFunction(){
		return directionFunction;
	}

}