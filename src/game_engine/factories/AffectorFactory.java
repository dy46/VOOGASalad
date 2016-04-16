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

	private void constructAffector(String property, Bounds range, String effect, List<Function> functions){
		Affector affector = null;
		try {
			affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
					.getConstructor(List.class, Bounds.class)
					.newInstance(functions, range);
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
		List<Position> l1 = new ArrayList<>();
                l1.add(new Position(-100, -100));
                l1.add(new Position(-100,100));
                l1.add(new Position(100,100));
                l1.add(new Position(100,-100));
                Bounds b = new Bounds(l1);
		constructAffector(property4, b, effect4, Arrays.asList(healthFunction));
		
		String expIncrProperty = "ExpIncr";
		Function exprIncrFunction = myFunctionFactory.createExpIncrFunction("Moderate");
		String healthDamageEffect = "HealthDamage";
		constructAffector(expIncrProperty, b, healthDamageEffect, Arrays.asList(exprIncrFunction));
		
		String randomPoison = "RandomPoison";
		Function randomPoisonFunction = myFunctionFactory.createExpIncrFunction("Weak");
		String randomPoisonEffect = "HealthDamage";
		constructAffector(randomPoison, b, randomPoisonEffect, Arrays.asList(randomPoisonFunction));

		String property5 = "State";
		String effect5 = "Change";
		constructAffector(property5, b, effect5, null);

		String property6 = "RangePathFollow";
		String effect6 = "PositionMove";
		constructAffector(property6, b, effect6, null);
		
	        String property7 = "PathFollow";
	        String effect7 = "PositionMove";
	        constructAffector(property7, b, effect7, null);

		String property8 = "Homing";
		String effect8 = "Move";
		constructAffector(property8, b, effect8, null);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}
	
}