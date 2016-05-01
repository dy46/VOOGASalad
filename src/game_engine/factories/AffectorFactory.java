package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.affectors.Affector;
import game_engine.affectors.AffectorData;
import game_engine.affectors.BasicDecrementAffector;
import game_engine.affectors.BasicIncrementAffector;
import game_engine.affectors.BasicSetAffector;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;

public class AffectorFactory {

	private static final String PACKAGE = "game_engine.affectors.";
	private static final String BASE = "Affector";
	private AffectorLibrary myAffectorLibrary;
	private FunctionFactory myFunctionFactory;

	public AffectorFactory(FunctionFactory myFunctionFactory){
		myAffectorLibrary = new AffectorLibrary();
		this.myFunctionFactory = myFunctionFactory;
	}

	private void constructAffector(String property, String effect, AffectorData data){
		Affector affector = null;
		try {
			affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
					.getConstructor(AffectorData.class)
					.newInstance(data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		affector.setTTL(Integer.MAX_VALUE);
		myAffectorLibrary.addAffector(property, effect, affector);
	}
	
	public List<Function> convertToFunctions(List<String> functionNames, List<Double> functionValues) {
	    List<Function> functionList = new ArrayList<>();
	    for(int i = 0; i < functionNames.size(); i++) {
	        if(functionNames.get(i).equals("Constant")) {
	            functionList.add(myFunctionFactory.createConstantFunction(functionValues.get(i)));	           	            
	        }
	    }
	    return functionList;
	}
	
	public Affector constructAffector(String name, String className, String property, int TTL, List<String> functionNames, List<Double> functionValues) {
	       List<String> propertyList = new ArrayList<>();
	       List<List<Function>> functionList = new ArrayList<>();
	       functionNames.removeIf(f -> f == null);
	       functionValues.removeIf(f -> f== null);
	       AffectorData newData = null;
	       if(property == null) {
	           if(functionNames.size() == 0) {
	               newData = new AffectorData();
	           }
	           else {
	               functionList.add(convertToFunctions(functionNames, functionValues));
	               propertyList.add(null);
	               newData = new AffectorData(functionList, propertyList);  
	           }
	       }
	       else if (functionNames.size() == 0){
	            propertyList.add(property);
	            functionList.add(null);
	            newData = new AffectorData(functionList, propertyList);  
	       }
	       else {
	    	   propertyList.add(property);
	    	   functionList.add(convertToFunctions(functionNames, functionValues));
	              newData = new AffectorData(functionList, propertyList);  
	       }       
	       Affector newAffector = null;
	       try {
	            newAffector =  (Affector) Class.forName(className).getConstructor(AffectorData.class)
	                    .newInstance(newData);
	       }
	       catch (Exception e) {
	           e.printStackTrace();
	       }
	       newAffector.setName(name);
	       newAffector.setTTL(TTL);
	       myAffectorLibrary.addAffector(name, newAffector);
	       System.out.println(myAffectorLibrary.getAffectorNames());
	       return newAffector;
	    }

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}
	

}