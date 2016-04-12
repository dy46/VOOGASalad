package game_engine.affectors;

import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


public class HomingMoveAffector extends Affector {

    private Unit trackedUnit;
    private boolean firstApplication;

    public HomingMoveAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
	      super(functions, engineWorkspace);
	      firstApplication = true;
	}

    public void apply (UnitProperties properties) {
        if(firstApplication) {
            trackedUnit = findTrackedUnit(getEngineWorkspace(), properties);
            if(trackedUnit != null) {
                firstApplication = false;
            }
        }
        double speed = properties.getVelocity().getSpeed();
        if(trackedUnit != null) {
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
    }
    
    public Unit findTrackedUnit(IPlayerEngineInterface engineWorkspace, UnitProperties properties) {
        Position myPos = properties.getPosition();
        double closestDiff = Double.MAX_VALUE;
        Unit closestEnemy = null;
        for(int i = 0; i < engineWorkspace.getEnemies().size(); i++) {
            double currDiff;
            Unit currEnemy = engineWorkspace.getEnemies().get(i);
            Position currPos = currEnemy.getProperties().getPosition();
            if ((currDiff = Math.abs(myPos.getX()-currPos.getX()) 
                    + Math.abs(myPos.getY() - currPos.getY())) < closestDiff && currEnemy.isVisible()) {
                closestDiff = currDiff;
                closestEnemy = currEnemy;
            }
        }
        return closestEnemy;
    }

}
