package game_engine.factories;

import java.util.List;
import java.util.Map;

import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.store_elements.Store;
import game_engine.store_elements.Pair;

public class StoreFactory {
	private Store myStore;
	private Map<String, Unit> myUnits;
	private Map<String, Affector> myUpgrades;
	
	public StoreFactory(Map<String, Unit> units, Map<String, Affector> upgrades){
		myStore = null;
		myUnits = units;
		myUpgrades = upgrades;
	}
	
	public void createNewStore(int money){
		myStore = new Store(money);
	}	
	
	public void addBuyableUnit(String name, int cost){
		myStore.addBuyableUnit(myUnits.get(name), cost);
	}
	
	public void addBuyableUnits(List<Pair<String, Integer>> unitsWithPrices){
		for(Pair p : unitsWithPrices){
			myStore.addBuyableUnit(myUnits.get(p.getLeft()),(Integer) p.getRight());
		}
	}
	
	public void addUpgrade(String unitName,String upgradeName, int cost){
		myStore.addUpgrade(myUnits.get(unitName), myUpgrades.get(upgradeName), cost);
	}
	
	
}
