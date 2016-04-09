package game_engine.affectors;

import java.util.Arrays;
import java.util.List;

import game_engine.functions.Function;
import game_engine.functions.FunctionFactory;

public class AffectorFactory {

	private static final String PACKAGE = "game_engine.affectors.";
	private static final String BASE = "Affector";
	private AffectorLibrary myAffectorLibrary;

	public AffectorFactory(FunctionFactory myFunctionFactory){
		myAffectorLibrary = new AffectorLibrary();
		setDefaultAffectors(myFunctionFactory);
	}

	private void constructAffector(String property, String effect, List<Function> functions){
		Affector affector = null;
		try {
			affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
					.getConstructor(List.class).newInstance(functions);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		myAffectorLibrary.addAffector(property, effect, affector);
	}

	private void setDefaultAffectors(FunctionFactory myFunctionFactory){
		String property = "Constant";
		Function speedFunction = myFunctionFactory.createConstantFunction(0);
		Function directionFunction = myFunctionFactory.createConstantFunction(0);
		String effect = "PositionMove";
		constructAffector(property, effect, Arrays.asList(speedFunction, directionFunction));
		
		String property2 = "ExpIncr";
		Function speedFunction2 = myFunctionFactory.createExpIncrFunction("Strong");
		Function directionFunction2 = myFunctionFactory.createConstantFunction(0);
		String effect2 = "PositionMove";
		constructAffector(property2, effect2, Arrays.asList(speedFunction2, directionFunction2));
		
		String property3 = "ExpDecr";
		Function speedFunction3 = myFunctionFactory.createExpDecrFunction("Weak");
		Function directionFunction3 = myFunctionFactory.createConstantFunction(0);
		String effect3 = "PositionMove";
		constructAffector(property3, effect3, Arrays.asList(speedFunction3, directionFunction3));
		
		String property4 = "Constant";
		Function healthFunction = myFunctionFactory.createConstantFunction(0);
		String effect4 ="HealthDamage";
		constructAffector(property4, effect4, Arrays.asList(healthFunction));
		
		String property5 = "State";
		String effect5 = "Change";
		constructAffector(property5, effect5, null);	
		
		String property6 = "PathFollow";
		String effect6 = "PositionMove";
		constructAffector(property6, effect6, null);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}

}