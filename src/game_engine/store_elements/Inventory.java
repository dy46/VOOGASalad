package game_engine.store_elements;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Upgrade> items;
	
	public Inventory(int team){
		this.items = new ArrayList<Upgrade>();
	}
	public void addToInventory(Upgrade item){
		this.items.add(item);
	}
	public Upgrade getFromInventory(String name){
		for(Upgrade u : items){
			if(u.getName().equals(name)){
				return u;
			}
		}
		// not sure if this is a good idea
		return null;
	}

	
}
