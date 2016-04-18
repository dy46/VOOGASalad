package game_engine.affectors;

import java.util.List;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;

public class ExplosionRadiusAffector extends Affector{

	public ExplosionRadiusAffector(AffectorData data){
		super(data);
	}

	public void apply(Unit u){
		Bounds range = u.getProperties().getRange();
		List<Unit> affectedUnits = getWS().getCollisionDetector().getUnitsInRange(range);
		for(Unit unit : affectedUnits){
			apply(unit);
		}
	}

}