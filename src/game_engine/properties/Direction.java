package game_engine.properties;

public class Direction {

	private double xDirection;
	private double yDirection;
	
	public Direction(double xDirection, double yDirection){
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}
	
	public double getXDirection(){
		return xDirection;
	}
	
	public double getYDirection() {
	        return yDirection;
	}
	
	public void setDirection(double xDirection, double yDirection){
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}
	
}