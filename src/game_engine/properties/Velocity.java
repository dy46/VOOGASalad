package game_engine.properties;


public class Velocity {

	private double mySpeed;
	private double myDirection;

	public Velocity (double speed, double direction) {
		this.mySpeed = speed;
		this.myDirection = direction;
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
	
	public Velocity copyVelocity() {
	    return new Velocity(this.getSpeed(), this.getDirection());
	}


}