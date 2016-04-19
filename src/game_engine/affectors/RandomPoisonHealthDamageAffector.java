package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.games.GameEngineInterface;

public class RandomPoisonHealthDamageAffector extends ExpIncrHealthDamageAffector{

	public RandomPoisonHealthDamageAffector(AffectorData data) {
		super(data);
	}

	// poisons with 1/5th chance
	public void apply(Unit unit) {
		if((int) (Math.random()*100) < 20)
			super.apply(unit);
	}

}