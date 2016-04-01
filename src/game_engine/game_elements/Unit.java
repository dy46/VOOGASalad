package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

import game_engine.properties.UnitProperties;

public class Unit implements GameElement{

	private String myID;
	private UnitProperties myProperties;
	private List<Affector> myAffectors;
	
	public Unit(String ID, UnitProperties properties){
		this.myProperties = properties;
		this.myID = ID;
		myAffectors = new ArrayList<>();
	}
	
	public UnitProperties getProperties(){
		return myProperties;
	}
	
	public String getID(){
		return myID;
	}
	
	public void update(){
		myAffectors.forEach(a -> a.apply(myProperties));
	}

	public void setID(String ID) {
		this.myID = ID;
	}

	public void setProperties(UnitProperties properties) {
		this.myProperties = properties;
	}
	
}