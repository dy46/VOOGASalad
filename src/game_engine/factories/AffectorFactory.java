package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.functions.Function;
import game_engine.libraries.AffectorLibrary;
import game_engine.properties.Bounds;
import game_engine.properties.Position;

public class AffectorFactory {

	private static final String PACKAGE = "game_engine.affectors.";
	private static final String BASE = "Affector";
	private AffectorLibrary myAffectorLibrary;

	public AffectorFactory(FunctionFactory myFunctionFactory){
		myAffectorLibrary = new AffectorLibrary();
		setDefaultAffectors(myFunctionFactory);
	}

	private void constructAffector(String property,  String effect, List<Function> functions){
		Affector affector = null;
		try {
			affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
					.getConstructor(List.class)
					.newInstance(functions);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		myAffectorLibrary.addAffector(property, effect, affector);
	}

	private void setDefaultAffectors(FunctionFactory myFunctionFactory){
	    
		String property4 = "Constant";
		Function healthFunction = myFunctionFactory.createConstantFunction(1);
		String effect4 ="HealthDamage";
		
		constructAffector(property4, effect4, Arrays.asList(healthFunction));
		
		String expIncrProperty = "ExpIncr";
		Function exprIncrFunction = myFunctionFactory.createExpIncrFunction("Moderate");
		String healthDamageEffect = "HealthDamage";
		constructAffector(expIncrProperty, healthDamageEffect, Arrays.asList(exprIncrFunction));
		
		String randomPoison = "RandomPoison";
		Function randomPoisonFunction = myFunctionFactory.createExpIncrFunction("Weak");
		String randomPoisonEffect = "HealthDamage";
		constructAffector(randomPoison, randomPoisonEffect, Arrays.asList(randomPoisonFunction));

		String property5 = "State";
		String effect5 = "Change";
		constructAffector(property5, effect5, null);

		String property6 = "RangePathFollow";
		String effect6 = "PositionMove";
		constructAffector(property6, effect6, null);
		
	        String property7 = "PathFollow";
	        String effect7 = "PositionMove";
	        constructAffector(property7, effect7, null);

		String property8 = "Homing";
		String effect8 = "Move";
		constructAffector(property8, effect8, null);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}
	
}