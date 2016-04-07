package game_engine.properties;

public class Damage {
	
	private double myDamage;
	
	public Damage(double damage){
		this.myDamage = damage; 
	}
	
	public double getDamage(){
		return myDamage; 
	}
	
	public void setDamage(double damage){
		myDamage = damage; 
	}
	
	public Damage copyDamage() {
	    return new Damage(this.getDamage());
	}
	
}