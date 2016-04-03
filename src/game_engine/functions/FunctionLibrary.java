package game_engine.functions;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents a library or database of functions for a single instance of a game.
 * The library holds pre-created default functions but can be expanded in-game by the player.
 * @author adamtache
 *
 */
public class FunctionLibrary {

	private HashMap<String, Function> myFunctions;
	private HashMap<String, List<Term>> myFunctionTypes;
	private HashMap<String, Constant> myStrengths;
	private HashMap<String, Constant> mySpecialConstants;
	private FunctionFactory myFactory;
	
	public FunctionLibrary(){
		myFunctions = new HashMap<>();
		myFunctionTypes = new HashMap<>();
		myStrengths = new HashMap<>();
		myFactory = new FunctionFactory();
	}

	public List<Term> getTerms(String type, String strength) {
		return myFunctions.get(myFactory.getName(type, strength)).getTerms();
	}

	public void addFunction(Function function) {
		myFunctions.put(function.getName(), function);
	}
	
	public Function getFunction(String name){
		return myFunctions.get(name);
	}
	
	public List<Term> getFunctionType(String type){
		return myFunctionTypes.get(type);
	}
	
	public Constant getStrength(String str){
		return myStrengths.get(str);
	}
	
	public void addSpecialConstant(Constant constant){
		mySpecialConstants.put(constant.getName(), constant);
	}
	
}