package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


public class HomingMoveAffector extends SingleTrackRangeAffector {

    public HomingMoveAffector(List<Function> functions){
	      super(functions);
	}
    
    public void futureApply(Unit u, Unit tracked) {
        UnitProperties properties = u.getProperties();
        double speed = properties.getVelocity().getSpeed();
        Position trackedPos = tracked.getProperties().getPosition();
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
    }
    
    public void firstApply(Unit u, Unit tracked) {
        futureApply(u, tracked);
        Unit tower = getEngineWorkspace().getTowers().get((u.getNumberList().get(0)).intValue());
        tower.getProperties().getVelocity().setDirection(u.getProperties().getVelocity().getDirection());
    }

}
