package game_engine.game_elements;

public abstract class LiveableUnit extends Unit{

	double myHealth;

	public LiveableUnit(String ID, double health) {
		super(ID);
		this.myHealth = health;
	}
	
	public boolean isAlive(){
		return myHealth > 0;
	}
	
	public double getHealth(){
		return myHealth;
	}

}