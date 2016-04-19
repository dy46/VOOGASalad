package game_engine.store_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
public class Store {
	private int myMoney;
	private Map<Tower, Integer> buyableUnits;
	private Map<Unit, List<Pair<Upgrade, Integer>>> upgrades;
	private Map<Upgrade, Integer> items;
	
	public Store(int startMoney){
		myMoney = startMoney;
		buyableUnits = new HashMap<Tower, Integer>();
		upgrades = new HashMap<Unit, List<Pair<Upgrade, Integer>>>();
	}
	public void addBuyableTower(Tower t, Integer cost){
		buyableUnits.put(t, cost);
	}
	public boolean canAfford(int cost){
		return myMoney >= cost;
	}
	public Unit purchaseUnit(String name){
		Unit u = null;
		for(Unit unit : buyableUnits.keySet()){
			if(unit.toString().equals(name) && myMoney >= buyableUnits.get(unit)){
				myMoney -= buyableUnits.get(unit);
				u = unit;
				break;
			}
		}
		// this should have a copy method
		return u;
	}
	public int getUnitListSize(){
		return buyableUnits.size();
	}
	public void applyItem(String name, List<Unit> applied){
		for(Upgrade u : items.keySet()){
			if(u.getName().equals(name) && myMoney >= items.get(u)){
				applied.forEach(t -> t.addAffectors(u.getAffectors()));
				myMoney -= items.get(u);
			}
		}
	}
	public void addItemToInventory(String name, Inventory playerInventory){
		for(Upgrade u : items.keySet()){
			if(u.getName().equals(name) && myMoney >= items.get(u)){
				playerInventory.addToInventory(u);
				myMoney -= items.get(u);
			}
		}
	}
	public List<Tower> getTowerList(){
		ArrayList<Tower> ret = new ArrayList<Tower>();
		for(Tower t : buyableUnits.keySet()){
			ret.add(t);
		}
		
		return ret;
	}
	public void addMoney(int amount){
		myMoney += amount;
	}
	
	
}
