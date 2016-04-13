package game_engine.factories;

import java.util.Arrays;
import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.affectors.Affector;
import game_engine.functions.Function;
import game_engine.libraries.AffectorLibrary;

public class AffectorFactory {

	private static final String PACKAGE = "game_engine.affectors.";
	private static final String BASE = "Affector";
	private AffectorLibrary myAffectorLibrary;
	private IPlayerEngineInterface engineWorkspace;

	public AffectorFactory(FunctionFactory myFunctionFactory, IPlayerEngineInterface engineWorkspace){
		myAffectorLibrary = new AffectorLibrary();
		this.engineWorkspace = engineWorkspace;
		setDefaultAffectors(myFunctionFactory);
	}

	private void constructAffector(String property, String effect, List<Function> functions){
		Affector affector = null;
		try {
			affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
					.getConstructor(List.class, IPlayerEngineInterface.class)
					.newInstance(functions, engineWorkspace);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		myAffectorLibrary.addAffector(property, effect, affector);
	}

	private void setDefaultAffectors(FunctionFactory myFunctionFactory){
		String property4 = "Constant";
		Function healthFunction = myFunctionFactory.createConstantFunction(0.0009);
		String effect4 ="HealthDamage";
		constructAffector(property4, effect4, Arrays.asList(healthFunction));

		String expIncrProperty = "ExpIncr";
		Function exprIncrFunction = myFunctionFactory.createExpIncrFunction("Moderate");
		String healthDamageEffect = "HealthDamage";
		constructAffector(expIncrProperty, healthDamageEffect, Arrays.asList(exprIncrFunction));
		
		String randomPoison = "RandomPoison";
		Function randomPoisonFunction = myFunctionFactory.createExpIncrFunction("Moderate");
		String randomPoisonEffect = "HealthDamage";
		constructAffector(randomPoison, randomPoisonEffect, Arrays.asList(randomPoisonFunction));

		String property5 = "State";
		String effect5 = "Change";
		constructAffector(property5, effect5, null);

		String property6 = "PathFollow";
		String effect6 = "PositionMove";
		constructAffector(property6, effect6, null);


		String property7 = "Homing";
		String effect7 = "Move";
		constructAffector(property7, effect7, null);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}

}