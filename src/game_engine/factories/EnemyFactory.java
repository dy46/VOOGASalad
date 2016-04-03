package game_engine.factories;

import java.util.ArrayList;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.affectors.AffectorFactory;
import game_engine.affectors.PositionMoveAffector;
import game_engine.functions.Function;
import game_engine.functions.FunctionFactory;
import game_engine.game_elements.Enemy;

public class EnemyFactory {

	private AffectorFactory myAffectorFactory;
	private FunctionFactory myFunctionFactory;

	public EnemyFactory(AffectorFactory affectorFactory, FunctionFactory functionFactory){
		this.myAffectorFactory = affectorFactory;
		this.myFunctionFactory = functionFactory;
	}

	public Enemy createEnemy(String name, Function speedFunction, Function directionFunction, int TTL){
		List<Function> functions = new ArrayList<>();
		functions.add(speedFunction);
		functions.add(directionFunction);
		return new Enemy(name, createPositionMoveAffector(functions, TTL));
	}
	
	public Enemy createStationaryEnemy(String name, double direction){
		List<Function> functions = new ArrayList<>();
		functions.add(myFunctionFactory.createConstantFunction(0));
		functions.add(myFunctionFactory.createConstantFunction(direction));
		return new Enemy(name, createPositionMoveAffector(functions, 0));
	}
	
	private Affector createPositionMoveAffector(List<Function> functions, int TTL){
		return myAffectorFactory.constructAffector("Position",
				"Move", 
				functions,
				TTL);
	}

}