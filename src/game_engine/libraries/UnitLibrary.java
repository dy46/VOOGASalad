package game_engine.libraries;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Unit;

public class UnitLibrary {

	private List<Unit> myUnits;
	
	public UnitLibrary(){
		this.myUnits = new ArrayList<>();
	}
	
	public List<Unit> getUnits(){
		return myUnits;
	}
	
	public void addUnit(Unit unit){
		this.myUnits.add(unit);
	}
	
	public Unit getUnitByName(String name){
		for(Unit unit : myUnits){
			if(unit.getName().equals(name)){
				return unit;
			}
		}
		return null;
	}
	
}