package game_engine.game_elements;

public abstract class LiveableUnit extends Unit{

	public LiveableUnit(String ID) {
		super(ID);
	}
	
	public boolean isAlive(){
		return getProperties().getHealth().getValue() > 0;
	}

}