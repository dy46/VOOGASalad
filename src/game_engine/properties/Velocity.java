package game_engine.properties;

public class Velocity {

	public double mySpeed;
	public Direction myDirection;
	
	public Velocity(double speed, Direction direction){
		this.mySpeed = speed;
		this.myDirection = direction;
	}
	
	public double getSpeed(){
		return mySpeed;
	}
	
	public Direction getDirection(){
		return myDirection;
	}
	
	public void setSpeed(double speed){
		this.mySpeed = speed;
	}
	
	public void setDirection(Direction direction){
		this.myDirection = direction;
	}
	
}