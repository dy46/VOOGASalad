package game_engine.factories;

import java.util.List;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.UnitLibrary;
import game_engine.store_elements.Store;
import game_engine.store_elements.Pair;

public class StoreFactory {
	private Store myStore;
	// private Map<String, Unit> myUnits;
	// private Map<String, Affector> myUpgrades;
	private UnitLibrary ul;
	private AffectorLibrary al;

	public StoreFactory(UnitLibrary ul, AffectorLibrary al) {
		this.ul = ul;
		this.al = al;

		/*
		 * myUnits = new HashMap<String, Unit>(); myUpgrades = new
		 * HashMap<String, Affector>(); for(String name : ul.getUnitNames()){
		 * myUnits.put(name, ul.getUnitByName(name)); } for(String name :
		 * al.getAffectorNames()){ myUpgrades.put(name, al.getAffector(name)); }
		 */
		myStore = new Store(0);
	}
	/*
	 * public StoreFactory(Map<String, Unit> units, Map<String, Affector>
	 * upgrades){ myStore = null; myUnits = units; myUpgrades = upgrades; }
	 */

	public void createNewStore(int money) {
		myStore = new Store(money);
	}

	public void addBuyableUnit(String name, int cost) {
		Unit toAdd = ul.getUnitByName(name);
		myStore.addBuyableUnit(toAdd, cost);
	}

	public void addBuyableUnits(List<Pair<String, Integer>> unitsWithPrices) {
		for (Pair p : unitsWithPrices) {
			Unit toAdd = ul.getUnitByName((String) p.getLeft());
			myStore.addBuyableUnit(toAdd, (Integer) p.getRight());
		}
	}

	public void addBuyableUnits(List<String> names, List<Integer> prices) {
		for (int i = 0; i < names.size(); i++) {
			this.addBuyableUnit(names.get(i), prices.get(i));
		}
	}

	public void addUpgrade(String unitName, String upgradeName, int cost) {
		Unit toChange = ul.getUnitByName(unitName);
		Affector upgradeToAdd = al.getAffector(upgradeName);
		myStore.addUpgrade(toChange, upgradeToAdd, cost);
	}

	public void addUpgrades(List<String> unitNames, List<String> upgradeNames, List<Integer> costs) {
		for (int i = 0; i < unitNames.size(); i++) {
			this.addUpgrade(unitNames.get(i), upgradeNames.get(i), costs.get(i));
		}
	}

	public Store getStore() {
		return this.myStore;
	}

}