package game_engine.properties;

public class Health {

	private double myHealth;
	
	public Health(double health){
		this.myHealth = health;
	}
	
	public double getValue(){
		return myHealth;
	}
	
	public void setValue(double health){
		myHealth = health;
	}
	
	public void decrementValue(double health) {
	    this.myHealth -= health;
	}
	
	public Health copyHealth() {
	    return new Health(this.getValue());
	}
	
}