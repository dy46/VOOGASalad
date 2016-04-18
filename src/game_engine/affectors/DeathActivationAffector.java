package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Property;

public class DeathActivationAffector extends Affector{

	public DeathActivationAffector(AffectorData data){
		super(data);
	}
	
	public List<Double> transformValues(Property p, List<Double> values){
		return values;
	}
	
	public void apply(Unit unit){
		unit.setElapsedTimeToDeath();
	}
	
}