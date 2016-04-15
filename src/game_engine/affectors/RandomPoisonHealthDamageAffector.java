package game_engine.affectors;

import java.util.List;

import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;

public class RandomPoisonHealthDamageAffector extends ExpIncrHealthDamageAffector{

	public RandomPoisonHealthDamageAffector(List<Function> functions) {
		super(functions);
	}

	// poisons with 1/5th chance
	public void apply(Unit unit) {
		if((int) (Math.random()*100) < 20)
			super.apply(unit);
	}

}