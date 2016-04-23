package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		List<List<Function>> f1 = Arrays.asList(Arrays.asList(myFunctionFactory.createConstantFunction(10)));
		List<String> p1 = Arrays.asList("Health");
		AffectorData d1 = new AffectorData(f1, p1);
		Affector basic = new BasicDecrementAffector(d1);
		basic.setTTL(1);
		myAffectorLibrary.addAffector(pr1, e1, basic);

		//		String pr2 = "ExpIncr";
		//		String e2 = "HealthDamage";
		//		List<List<Function>> f2 = Arrays.asList(Arrays.asList(myFunctionFactory.createExpIncrFunction("Moderate")));
		//		List<List<String>> p2 = new ArrayList<>();
		//		AffectorData d2 = new AffectorData(f2, p2);
		//		constructAffector(pr2, e2, d2);

		//		String pr3 = "RandomPoison";
		//		String e3 = "HealthDamage";
		//		List<List<Function>> f3 = Arrays.asList(Arrays.asList(myFunctionFactory.createExpIncrFunction("Weak")));
		//		List<List<String>> p3 = new ArrayList<>();
		//		AffectorData d3 = new AffectorData(f3, p3);
		//		constructAffector(pr3, e3, d3);

		String pr4 = "State";
		String e4 = "Change";
		List<List<Function>> f4 = Arrays.asList(Arrays.asList(myFunctionFactory.createConstantFunction(4)));
		List<String> p4 = Arrays.asList("State");
		AffectorData d4 = new AffectorData(f4, p4);
		Affector basic2 = new BasicSetAffector(d4);
		myAffectorLibrary.addAffector(pr4, e4, basic2);

		String pr5 = "RangePathFollow";
		String e5 = "PositionMove";
		List<List<Function>> f5 = new ArrayList<>();
		List<List<String>> p5 = new ArrayList<>();
		AffectorData d5 = new AffectorData();
		constructAffector(pr5, e5, d5);

		String pr6 = "PathFollow";
		String e6 = "PositionMove";
		List<List<Function>> f6 = new ArrayList<>();
		List<List<String>> p6 = new ArrayList<>();
		AffectorData d6 = new AffectorData();
		constructAffector(pr6, e6, d6);

		String pr7 = "AIPath";
		String e7 = "Follow";
		List<List<Function>> f7 = new ArrayList<>();
		List<List<String>> p7 = new ArrayList<>();
		AffectorData d7 = new AffectorData();
		constructAffector(pr7, e7, d7);

		String pr8 = "Homing";
		String e8 = "Move";
		List<List<Function>> f8 = new ArrayList<>();
		List<List<String>> p8 = new ArrayList<>();
		AffectorData d8 = new AffectorData();
		constructAffector(pr8, e8, d8);

//		String pr9 = "Death";
//		String e9 = "Activation";
//		List<List<Function>> deathfunction = Arrays.asList(Arrays.asList(myFunctionFactory.createConstantFunction(0)));
//		List<String> deathproperty = Arrays.asList("Health");
//		AffectorData deathdata = new AffectorData(deathfunction, deathproperty);
//		Affector deathbasic = new BasicSetAffector(deathdata);
//		myAffectorLibrary.addAffector(pr9, e9, deathbasic);

		String pr10 = "RandomPath";
		String e10 = "Follow";
		List<List<Function>> f10 = new ArrayList<>();
		List<List<String>> p10 = new ArrayList<>();
		AffectorData d10 = new AffectorData();
		constructAffector(pr10, e10, d10);

		//		String pr11 = "Explosion";
		//		String e11 = "Radius";
		//		List<List<Function>> f11 = new ArrayList<>();
		//		List<List<String>> p11 = Arrays.asList(Arrays.asList("Damage"));
		//		AffectorData d11 = new AffectorData(f11, p11);
		//		constructAffector(pr11, e11, d11);

		String property12 = "Position";
		String effect12 = "Move";
		List<List<Function>> f12 = new ArrayList<>();
		List<List<String>> p12 = new ArrayList<>();
		AffectorData d12 = new AffectorData();
		constructAffector(property12, effect12, d12);

		String property13 = "RangeConstantPosition";
		String effect13 = "Move";
		List<List<Function>> f13 = new ArrayList<>();
		List<List<String>> p13 = new ArrayList<>();
		AffectorData d13 = new AffectorData();
		constructAffector(property13, effect13, d13);

		String property14 = "Firing";
		String effect14 = "Children";
		Function function14 = myFunctionFactory.createConstantFunction(30);
		List<List<Function>> f14 = Arrays.asList(Arrays.asList(function14));
		List<String> p14 = new ArrayList<>();
		p14.add(null);
		AffectorData d14 = new AffectorData(f14, p14);
		constructAffector(property14, effect14, d14);

		//		String pr15 = "MoveTo";
		//		String e15 = "Spawn";
		//		Function function1 = myFunctionFactory.createConstantFunction(0);
		//		Function function2 = myFunctionFactory.createConstantFunction(0);
		//		List<List<Function>> f15 = Arrays.asList(Arrays.asList(function1, function2));
		//		List<List<String>> p15 = Arrays.asList(Arrays.asList("Position"));
		//		AffectorData d15 = new AffectorData(f15, p15);
		//		constructAffector(pr15, e15, d15);
//		
//		String property15 = "Velocity";
//		String effect15 = "Stop";
//		Function function15 = myFunctionFactory.createConstantFunction(0);
//		AffectorData d15 = new AffectorData();
		
		String pr15 = "Velocity";
		String e15 = "Stop";
		List<List<Function>> f15 = new ArrayList<List<Function>>();
		List<Function> functions = new ArrayList<Function>();
		functions.add(myFunctionFactory.createConstantFunction(0));
		functions.add(myFunctionFactory.createConstantFunction(0)); // change this later
		f15.add(functions);
		List<String> p15 = Arrays.asList("Velocity");
		AffectorData d15 = new AffectorData(f15, p15);
		Affector basic3 = new BasicSetAffector(d15);
		basic3.setTTL(Integer.MAX_VALUE);
		myAffectorLibrary.addAffector(pr15, e15, basic3);
		Unit u = new Unit("String", new ArrayList<>(), 6);
//		System.out.println(u);
//		System.out.println(u.getProperties().getVelocity().getValues());
		basic3.apply(u);
//		System.out.println("this should be different");
//		System.out.println(u.getProperties().getVelocity().getValues());
		
		String pr16 = "Increase";
                String e16 ="Range";
                List<List<Function>> f16 = Arrays.asList(Arrays.asList(myFunctionFactory.createConstantFunction(-25),
                                                                       myFunctionFactory.createConstantFunction(-25),
                                                                       myFunctionFactory.createConstantFunction(-25),
                                                                       myFunctionFactory.createConstantFunction(25),
                                                                       myFunctionFactory.createConstantFunction(25),
                                                                       myFunctionFactory.createConstantFunction(25),
                                                                       myFunctionFactory.createConstantFunction(25),
                                                                       myFunctionFactory.createConstantFunction(-25)));
                List<String> p16 = Arrays.asList("Range");
                AffectorData d16 = new AffectorData(f16, p16);
                Affector basic16 = new BasicIncrementAffector(d16);
                basic16.setTTL(1);
                myAffectorLibrary.addAffector(pr16, e16, basic16);
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorLibrary;
	}
	

}