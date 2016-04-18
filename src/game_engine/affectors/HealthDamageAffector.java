package game_engine.affectors;

import game_engine.game_elements.Unit;

public class HealthDamageAffector extends Affector{

	public HealthDamageAffector(AffectorData data){
		super(data);
	}

	@Override
	public void apply(Unit unit) {
		super.apply(unit);
		double damage = getData().getFunctions().get(0).get(0).evaluate(getElapsedTime());
		unit.getProperties().getHealth().decrementValue(damage);
	}

}