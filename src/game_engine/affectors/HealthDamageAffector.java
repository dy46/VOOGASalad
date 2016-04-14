package game_engine.affectors;

import java.util.List;

import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;

public class HealthDamageAffector extends Affector{

	public HealthDamageAffector(List<Function> functions){
		super(functions);
	}

	@Override
	public void apply(Unit unit) {
		super.apply(unit);
		double damage = getFunctions().get(0).evaluate(getElapsedTime());
		unit.getProperties().getHealth().decrementValue(damage);   
	}

}