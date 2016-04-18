package game_engine.affectors;

import java.util.List;
import game_engine.CollisionDetector;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;

public abstract class SingleTrackRangeAffector extends Affector{

	private Unit trackedUnit;
	private boolean firstApplication;

	public SingleTrackRangeAffector(AffectorData data){
		super(data);
		firstApplication = true;
	}

	public void apply(Unit u) {
		super.apply(u);
		if(firstApplication) {
			trackedUnit = findTrackedUnit(u.getProperties());
			if(trackedUnit != null) {
				firstApply(u, trackedUnit);
				firstApplication = false;
			}
			else {
				u.setInvisible();
				u.incrementElapsedTime(1);
			}
		}
		else {
			futureApply(u, trackedUnit);
		}
	}

	public abstract void firstApply(Unit u, Unit tracked);

	public abstract void futureApply(Unit u, Unit tracked);

	public Unit findTrackedUnit(UnitProperties properties) {
		Position myPos = properties.getPosition();
		double closestDiff = Double.MAX_VALUE;
		Unit closestEnemy = null;
		for(int i = 0; i < getWS().getEnemies().size(); i++) {
			double currDiff;
			Unit currEnemy = getWS().getEnemies().get(i);
			if(currEnemy.isVisible()) {
				Position currPos = currEnemy.getProperties().getPosition();
				if ((currDiff = Math.abs(myPos.getX()-currPos.getX()) 
						+ Math.abs(myPos.getY() - currPos.getY())) < closestDiff && currEnemy.isVisible()) {
					closestDiff = currDiff;
					closestEnemy = currEnemy;
				}
			}
		}
		if(closestEnemy == null) {
			return null;
		}
		return CollisionDetector.encapsulates(CollisionDetector.getUseableBounds(closestEnemy.getProperties().getBounds(), 
				closestEnemy.getProperties().getPosition()), 
				CollisionDetector.getUseableBounds(properties.getRange(), properties.getPosition()))
				? closestEnemy : null;
	}
}
