package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorData;
import game_engine.functions.Function;
import game_engine.games.GameEngineInterface;
import game_engine.libraries.AffectorLibrary;
import game_engine.properties.Bounds;
import game_engine.properties.Position;

public class AffectorFactory {

	private static final String PACKAGE = "game_engine.affectors.";
	private static final String BASE = "Affector";
	private AffectorLibrary myAffectorLibrary;
	private final int SET_AFFECTOR = 0;
	private final int INCREMENT_AFFECTOR = 1;
	private final int DECREMENT_AFFECTOR = 2;
	private final int UNIQUE_AFFECTOR = -1;

	public AffectorFactory(FunctionFactory myFunctionFactory){
		myAffectorLibrary = new AffectorLibrary();
		setDefaultAffectors(myFunctionFactory);
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
		myAffectorLibrary.addAffector(property, effect, affector);
	}

	private void setDefaultAffectors(FunctionFactory myFunctionFactory){
		String pr1 = "Constant";
		String e1 ="HealthDamage";
		List<List<Function>> f1 = Arrays.asList(Arrays.asList(myFunctionFactory.createConstantFunction(1)));
		List<List<String>> p1 = Arrays.asList(Arrays.asList("Health"));
		List<Integer> i1 = Arrays.asList(DECREMENT_AFFECTOR);
		AffectorData d1 = new AffectorData(f1, p1, i1);
		constructAffector(pr1, e1, d1);
		
		String pr2 = "ExpIncr";
		String e2 = "HealthDamage";
		List<List<Function>> f2 = Arrays.asList(Arrays.asList(myFunctionFactory.createExpIncrFunction("Moderate")));
		List<List<String>> p2 = Arrays.asList(Arrays.asList("Health"));
		List<Integer> i2 = Arrays.asList(DECREMENT_AFFECTOR);
		AffectorData d2 = new AffectorData(f2, p2, i2);
		constructAffector(pr2, e2, d2);
		
		String pr3 = "RandomPoison";
		String e3 = "HealthDamage";
		List<List<Function>> f3 = Arrays.asList(Arrays.asList(myFunctionFactory.createExpIncrFunction("Weak")));
		List<List<String>> p3 = Arrays.asList(Arrays.asList("Health"));
		List<Integer> i3 = Arrays.asList(DECREMENT_AFFECTOR);
		AffectorData d3 = new AffectorData(f3, p3, i3);
		constructAffector(pr3, e3, d3);

		String pr4 = "State";
		String e4 = "Change";
		List<List<Function>> f4 = Arrays.asList(Arrays.asList(myFunctionFactory.createConstantFunction(0)));
		List<List<String>> p4 = Arrays.asList(Arrays.asList("State"));
		List<Integer> i4 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d4 = new AffectorData(f4, p4, i4);
		constructAffector(pr4, e4, d4);
		
		String pr5 = "RangePathFollow";
		String e5 = "PositionMove";
		List<List<Function>> f5 = new ArrayList<>();
		List<List<String>> p5 = Arrays.asList(Arrays.asList("Position"));
		List<Integer> i5 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d5 = new AffectorData(f5, p5, i5);
		constructAffector(pr5, e5, d5);
		
		String pr6 = "PathFollow";
		String e6 = "PositionMove";
		List<List<Function>> f6 = new ArrayList<>();
		List<List<String>> p6 = Arrays.asList(Arrays.asList("Position"));
		List<Integer> i6 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d6 = new AffectorData(f6, p6, i6);
		constructAffector(pr6, e6, d6);
		
		String pr7 = "AIPath";
		String e7 = "Follow";
		List<List<Function>> f7 = new ArrayList<>();
		List<List<String>> p7 = Arrays.asList(Arrays.asList("Position"));
		List<Integer> i7 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d7 = new AffectorData(f7, p7, i7);
		constructAffector(pr7, e7, d7);

		String pr8 = "Homing";
		String e8 = "Move";
		List<List<Function>> f8 = new ArrayList<>();
		List<List<String>> p8 = Arrays.asList(Arrays.asList("Position"));
		List<Integer> i8 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d8 = new AffectorData(f8, p8, i8);
		constructAffector(pr8, e8, d8);
		
		String pr9 = "Death";
		String e9 = "Activation";
		List<List<Function>> f9 = new ArrayList<>();
		List<List<String>> p9 = Arrays.asList(Arrays.asList("Position"));
		List<Integer> i9 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d9 = new AffectorData(f9, p9, i9);
		constructAffector(pr9, e9, d9);
		
		String pr10 = "RandomPath";
		String e10 = "Follow";
		List<List<Function>> f10 = new ArrayList<>();
		List<List<String>> p10 = Arrays.asList(Arrays.asList("Position"));
		List<Integer> i10 = Arrays.asList(UNIQUE_AFFECTOR);
		AffectorData d10 = new AffectorData(f10, p10, i10);
		constructAffector(pr10, e10, d10);
		
		String pr11 = "Explosion";
		String e11 = "Radius";
		List<List<Function>> f11 = new ArrayList<>();
		List<List<String>> p11 = Arrays.asList(Arrays.asList("Damage"));
		List<Integer> i11 = Arrays.asList(DECREMENT_AFFECTOR);
		AffectorData d11 = new AffectorData(f11, p11, i11);
		constructAffector(pr11, e11, d11);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}
	
}