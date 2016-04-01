package game_engine.properties;

public class Health {

	private double myHealth;
	
	public Health(double health){
		this.myHealth = health;
	}
	
	public double getHealth(){
		return myHealth;
	}
	
	public void setHealth(double health){
		myHealth = health;
	}
	
}