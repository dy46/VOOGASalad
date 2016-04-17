package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.functions.Function;
import game_engine.games.GameEngineInterface;
import game_engine.libraries.AffectorLibrary;
import game_engine.properties.Bounds;
import game_engine.properties.Position;

public class AffectorFactory {

	private static final String PACKAGE = "game_engine.affectors.";
	private static final String BASE = "Affector";
	private AffectorLibrary myAffectorLibrary;
	private GameEngineInterface engineWorkspace;

	public AffectorFactory(FunctionFactory myFunctionFactory){
		myAffectorLibrary = new AffectorLibrary();
		setDefaultAffectors(myFunctionFactory);
	}
	
	public void setWorkspace(GameEngineInterface workspace){
		this.engineWorkspace = workspace;
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
		
		String property8 = "PathFollow";
		String effect8 = "PositionMove";
		constructAffector(property8, effect8, null);
		
		String AI = "AIPath";
		String AIeffect = "Follow";
		constructAffector(AI, AIeffect, null);

		String property9 = "Homing";
		String effect9 = "Move";
		constructAffector(property9, effect9, null);
		
		String property10 = "Death";
		String effect10 = "Activation";
		constructAffector(property10, effect10, null);
		
		String property11 = "RandomPath";
		String effect11 = "Follow";
		constructAffector(property11, effect11, null);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}
	
}