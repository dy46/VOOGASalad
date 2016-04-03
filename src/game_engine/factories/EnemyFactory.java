package game_engine.factories;

import java.util.ArrayList;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.affectors.AffectorFactory;
import game_engine.affectors.PositionMoveAffector;
import game_engine.functions.Function;
import game_engine.game_elements.Enemy;

public class EnemyFactory {

	private AffectorFactory myAffectorFactory;

	public EnemyFactory(AffectorFactory affectorFactory){
		this.myAffectorFactory = affectorFactory;
	}

	public Enemy createEnemy(String name, Function speedFunction, Function directionFunction, int TTL){
		List<Function> functions = new ArrayList<>();
		functions.add(speedFunction);
		functions.add(directionFunction);
		Affector moveAffector = new PositionMoveAffector(functions, TTL);
		myAffectorFactory.constructAffector("Position",
				"Move", 
				functions,
				TTL);
		return new Enemy(name, moveAffector);
	}

}