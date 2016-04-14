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
	
	public void addToX(double x) {
	    this.myX += x;
	}
	
	public void addToY(double y) {
	    this.myY += y;
	}
	
	public void addToXY(double x, double y) {
	    addToX(x);
	    addToY(y);
	}
	
	public Position copyPosition() {
	    return new Position(this.getX(), this.getY());
	}
	public double distanceTo(Position other){
		double dx = this.myX - other.myX;
		double dy = this.myY - other.myY;
		return Math.sqrt(dx*dx + dy*dy);
	}
	@Override
	public boolean equals(Object o){
		return (o instanceof Position && 
				(((Position)o).myX - this.myX) < 0.0000001 && 
				(((Position)o).myY - this.myY) < 0.0000001) ||
				(this == o);
	}
	
	@Override
	public String toString(){
		return Double.toString(this.myX)+ ", " +Double.toString(this.myY);
	}
}