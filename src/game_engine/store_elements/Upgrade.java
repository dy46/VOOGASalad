package game_engine.store_elements;

import java.util.ArrayList;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;

public class Upgrade {
	private String name;
	private List<Affector> affectors;	
	
	public Upgrade(String name){
		this.name = name;
		affectors = new ArrayList<Affector>();
	}
	
	public void addAffector(Affector newEffect){
		affectors.add(newEffect);
	}
	public void apply(Unit u){
		u.addAffectors(affectors);
	}
	public String getName(){
		return name;
	}
	public List<Affector> getAffectors(){
		return affectors;
	}
	

}
