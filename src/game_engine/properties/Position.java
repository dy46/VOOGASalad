package game_engine.properties;

public class Position {

	private double myX;
	private double myY;
	
	public Position(double x, double y){
		this.myX = x;
		this.myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public void setX(double x){
		this.myX = x;
	}
	
	public void setY(double y){
		this.myY = y;
	}
	
}