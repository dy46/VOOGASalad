package game_engine.properties;

public class Direction {

	private double myDirection;
	
	public Direction(double direction){
		this.myDirection = direction;
	}
	
	public double getDirection(){
		return myDirection;
	}
	
	public void setDirection(double direction){
		this.myDirection = direction;
	}
	
}