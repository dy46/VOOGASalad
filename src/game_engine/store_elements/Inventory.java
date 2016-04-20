package game_engine.store_elements;

import java.util.ArrayList;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;

public class Inventory {
	private List<Unit> items;
	private List<Affector> aff;
	
	public Inventory(int team){
		this.items = new ArrayList<Unit>();
	}
	public void addToInventory(Unit item){
		this.items.add(item);
	}
	public Unit getFromInventory(String name){
		for(Unit u : items){
			if(u.toString().equals(name)){
				return u;
			}
		}
		// not sure if this is a good idea
		return null;
	}
	public void addToInventory(Affector a){
		aff.add(a);
	}

	
}
