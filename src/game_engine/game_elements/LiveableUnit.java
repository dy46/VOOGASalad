package game_engine.game_elements;

public abstract class LiveableUnit extends Unit{

	public LiveableUnit(String name) {
		super(name);
	}
	
	public boolean isAlive(){
		return getProperties().getHealth().getValue() > 0;
	}
	
	public void update(Unit unit){
		super.update();
		if(!isAlive()){
			getWorkspace().remove(unit);
		}
	}
	
	public void 

}