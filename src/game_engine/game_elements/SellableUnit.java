package game_engine.game_elements;
import java.util.List;
import game_engine.affectors.Affector;

public abstract class SellableUnit extends Unit{
	
	public SellableUnit(String name, List<Affector> affectors, int numFrames) {
		super(name, affectors, numFrames);
	}

	public double getSellPrice(){
		return getProperties().getPrice().getValue();
	}
	
	public void sell(Unit unit){
		getWorkspace().addBalance(getSellPrice());
		getWorkspace().remove(unit);
	}
	
}