package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;
import game_engine.properties.UnitProperties;

/**
 * This class is the superclass for game units and implements GameElement. 
 * It represents any physical game unit and holds its ID, UnitProperties, and list of current Affectors to be applied.
 * @author adamtache
 *
 */

public abstract class Unit extends GameElement{

	private UnitProperties myProperties;
	private List<Affector> myAffectors;
	
	public Unit(String name){
		super(name);
		myProperties = new UnitProperties();
		initialize();
	}
	
	public Unit(String ID, UnitProperties properties){
		super(ID);
		this.myProperties = properties;
		initialize();
	}
	
	private void initialize(){
		myAffectors = new ArrayList<>();
	}
	
	public UnitProperties getProperties(){
		return myProperties;
	}
	
	public void update(){
		myAffectors.forEach(a -> a.apply(myProperties));
		myAffectors.clear();
	}

	public void setProperties(UnitProperties properties) {
		this.myProperties = properties;
	}

	public void addAffector(Affector affector) {
		myAffectors.add(affector);
	}

	public List<Affector> getAffectors() {
		return myAffectors;
	}

	public void setAffectors(List<Affector> affectors) {
		this.myAffectors = affectors;
	}
	
}