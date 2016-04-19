package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;

public class AffectorData {

	private List<List<Function>> myFunctions;
	private List<List<String>> myUnitProperties;
	private List<Integer> myAffectTypes;
	private List<Double> baseNumbers;
	
	public AffectorData() {};
	
	public AffectorData(List<List<Function>> functions, List<List<String>> unitProperies, List<Integer> types){
		myFunctions = functions;
		myUnitProperties = unitProperies;
		myAffectTypes = types;
	}
	
	public List<List<Function>> getFunctions(){
		return myFunctions;
	}
	
	public List<List<String>> getUnitProperties(){
		return myUnitProperties;
	}
	
	public void setFunctions(List<List<Function>> functions){
		this.myFunctions = functions;
	}
	
	public void setUnitProperties(List<List<String>> unitProperties){
		this.myUnitProperties = unitProperties;
	}
	
	public List<Integer> getTypes(){
		return myAffectTypes;
	}
	
	public void setBaseNumbers(List<Double> baseNumbers) {
	    this.baseNumbers = baseNumbers;
	}
	
	public List<Double> getBaseNumbers() {
	    return baseNumbers;
	}
	
}