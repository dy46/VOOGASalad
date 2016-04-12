package game_engine.affectors;

import java.util.List;

import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public class RandomPoisonHealthDamageAffector extends ExpIncrHealthDamageAffector{

	public RandomPoisonHealthDamageAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace) {
		super(functions, engineWorkspace);
	}

	// poisons with 1/5th chance
	public void apply(UnitProperties properties) {
		if((int) (Math.random()*100) < 20)
			super.apply(properties);
	}

}