package game_engine.affectors;

import java.util.List;

import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public class HealthDamageAffector extends Affector{

	public HealthDamageAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
		super(functions, engineWorkspace);
	}

	public void apply(UnitProperties properties) {
		super.apply(properties);
		double damage = getBaseNumbers().get(0) + getFunctions().get(0).evaluate(getElapsedTime());
		properties.getHealth().decrementValue(damage);   
	}

}