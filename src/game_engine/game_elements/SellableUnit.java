package game_engine.game_elements;

public abstract class SellableUnit extends Unit{
	
	public SellableUnit(String name) {
		super(name);
	}

	public double getSellPrice(){
		return getProperties().getPrice().getValue();
	}
	
	public void sell(Unit unit){
		getWorkspace().addBalance(getSellPrice());
		getWorkspace().remove(unit);
	}
	
}