package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


public class HomingMoveAffector extends Affector {

    private Unit trackedUnit;
    private boolean firstApplication;

    public HomingMoveAffector(List<Function> functions){
	      super(functions);
	      firstApplication = true;
	}

    public void apply (Unit u) {
        if(firstApplication) {
            trackedUnit = findTrackedUnit(u.getProperties());
            if(trackedUnit != null) {
                updateDirectionAndPosition(u.getProperties());
                changeEnemyDirection(u);
                firstApplication = false;
            }
        }
        else {
            updateDirectionAndPosition(u.getProperties());
        }
    }
    
    public void updateDirectionAndPosition(UnitProperties properties) {
        double speed = properties.getVelocity().getSpeed();
        Position trackedPos = trackedUnit.getProperties().getPosition();
        Position currPos = properties.getPosition();
        double currX = currPos.getX();
        double currY = currPos.getY();
        double dx = trackedPos.getX() - currX;
        double dy = trackedPos.getY() - currY;
        double newDir = Math.atan((dy) / (dx));
        double num = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double degreesDir = dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
        properties.getVelocity().setDirection(degreesDir);
        properties.getPosition().addToXY(speed*dx/num, speed*dy/num);
        if(!trackedUnit.isVisible()) {
            properties.getPosition().addToXY(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
    }
    
    public Unit findTrackedUnit(UnitProperties properties) {
        Position myPos = properties.getPosition();
        double closestDiff = Double.MAX_VALUE;
        Unit closestEnemy = null;
        for(int i = 0; i < getEngineWorkspace().getEnemies().size(); i++) {
            double currDiff;
            Unit currEnemy = getEngineWorkspace().getEnemies().get(i);
            Position currPos = currEnemy.getProperties().getPosition();
            if ((currDiff = Math.abs(myPos.getX()-currPos.getX()) 
                    + Math.abs(myPos.getY() - currPos.getY())) < closestDiff && currEnemy.isVisible()) {
                closestDiff = currDiff;
                closestEnemy = currEnemy;
            }
        }
        return closestEnemy;
    }
    
    public void changeEnemyDirection(Unit u) {
        Unit tower = getEngineWorkspace().getTowers().get((u.getNumberList().get(0)).intValue());
        tower.getProperties().getVelocity().setDirection(u.getProperties().getVelocity().getDirection());
    }

}
