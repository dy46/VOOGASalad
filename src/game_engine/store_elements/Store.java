package game_engine.store_elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.game_elements.Unit;
public class Store {
	private int myMoney;
	private Map<Unit, Integer> buyableUnits;
	private Map<Unit, List<Pair<Unit, Integer>>> upgrades;
	private Map<Unit, Integer> items;
	private Map<Unit, Integer> levelUnlock;

	public Store(int startMoney){
		myMoney = startMoney;
		buyableUnits = new HashMap<Unit, Integer>();
		upgrades = new HashMap<Unit, List<Pair<Unit, Integer>>>();
		items = new HashMap<Unit, Integer>();
		levelUnlock = new HashMap<Unit, Integer>();
	}
	public void addBuyableTower(Unit t, Integer cost, Integer level){
		buyableUnits.put(t, cost);
		levelUnlock.put(t, level);
	}
	public void addBuyableTower(Collection<Pair<Unit, Integer>> listOfNewUnits){
		for(Pair<Unit, Integer> p : listOfNewUnits){
			Unit newUnit = p.getLeft();
			int cost = p.getRight();
			boolean contained = false;
			for(Unit u : buyableUnits.keySet()){
				if(u.toString().equals(newUnit.toString())){
					this.buyableUnits.put(u, cost);
					contained = true;
					break;
				}
			}
			if(contained){
				continue;
			}
			buyableUnits.put(newUnit, cost);
		}
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
				// apply shit here
				for(Unit app : applied){
					//System.out.println(applied.size());
					List<AffectorTimeline> affectorsToApply = u.getTimelinesToApply()
							.stream().map(p -> p.copyTimeline()).collect(Collectors.toList());  
					app.addTimelines(affectorsToApply);
				}
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
				upgradedUnit.addAffectors(p.getLeft().getTimelinesToApply().get(0).getAffectors());
			}
		}
	}
	public List<Unit> getUnlockedUnits(int currLevel){
		List<Unit> unlocked = new ArrayList<Unit>();
		for(Unit u : buyableUnits.keySet()){
			if(currLevel <= this.levelUnlock.get(u)){
				unlocked.add(u);
			}
		}
		return unlocked;
	}
	public int getMoney(){
		return myMoney;
	}
	public void addItem(Unit u, int cost){
		boolean contains = false;
		for(Unit s : items.keySet()){
			if(s.toString().equals(u.toString())){
				items.put(s, cost);
				contains = false; 
				break;
			}
		}
		if(!contains){
			items.put(u, cost);
		}
	}


}
