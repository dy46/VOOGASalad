package game_engine.store_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
public class Store {
	private int myMoney;
	private Map<Unit, Integer> buyableUnits;
	private Map<Unit, List<Pair<Unit, Integer>>> upgrades;
	private Map<Unit, Integer> items;
	
	public Store(int startMoney){
		myMoney = startMoney;
		buyableUnits = new HashMap<Unit, Integer>();
		upgrades = new HashMap<Unit, List<Pair<Unit, Integer>>>();
		items = new HashMap<Unit, Integer>();
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
		for(Unit u : items.keySet()){
			if(u.toString().equals(name) && myMoney >= items.get(u)){
				applied.forEach(t -> t.addAffectors(u.getAffectors()));
				myMoney -= items.get(u);
			}
		}
	}
	public void addItemToInventory(String name, Inventory playerInventory){
		for(Unit u : items.keySet()){
			if(u.toString().equals(name) && myMoney >= items.get(u)){
				playerInventory.addToInventory(u);
				myMoney -= items.get(u);
			}
		}
	}
	public List<Unit> getTowerList(){
		ArrayList<Unit> ret = new ArrayList<Unit>();
		for(Unit t : buyableUnits.keySet()){
			ret.add(t);
		}
		
		return ret;
	}
	public void addMoney(int amount){
		myMoney += amount;
	}
	public void addUpgrade(Unit upgradedUnit, Unit upgradeType, int cost){
		List<Pair<Unit, Integer>> list = new ArrayList<Pair<Unit, Integer>>();
		for(Unit u : upgrades.keySet()){
			if(u.toString().equals(upgradedUnit.toString())){
				list = upgrades.get(u);
			}
		}
		boolean found = false;
		for(Pair<Unit, Integer> p : list){
			if(p.getLeft().toString().equals(upgradeType.toString())){
				p.setRight(cost);
				found = true;
			}
		}
		if(!found){
			list.add(new Pair<Unit, Integer>(upgradeType, cost));
		}
		upgrades.put(upgradedUnit, list);
	}
	public void buyUpgrade(Unit upgradedUnit, Unit upgradeType){
		Unit found = null;
		for(Unit u : upgrades.keySet()){
			if(u.toString().equals(upgradedUnit.toString())){
				found = u;
			}
		}
		if(found == null){
			throw new RuntimeException("This unit could not be found");
		}
		for(Pair<Unit, Integer> p : upgrades.get(found)){
			if(p.getLeft().toString().equals(upgradeType.toString()) && myMoney >= p.getRight()){
				myMoney -= p.getRight();
				upgradedUnit.getAffectorsToApply().addAll(p.getLeft().getAffectorsToApply());
			}
		}
	}
	
}
