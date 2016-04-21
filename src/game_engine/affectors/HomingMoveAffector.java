package game_engine.affectors;

import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


public class HomingMoveAffector extends SingleTrackRangeAffector {

	public HomingMoveAffector(AffectorData data){
		super(data);
	}
    
    public void futureApply(Unit u, Unit tracked) {
        UnitProperties properties = u.getProperties();
        double speed = properties.getVelocity().getSpeed();
        Position trackedPos = tracked.getProperties().getPosition();
        Position currPos = properties.getPosition();
        double dx = getdx(currPos, trackedPos);
        double dy = getdy(currPos, trackedPos);
        double num = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        properties.getVelocity().setDirection(getDirection(currPos, trackedPos));
        properties.getPosition().addToXY(speed*dx/num, 
                                         speed*dy/num);
        if(!tracked.isVisible()) {
           u.setElapsedTimeToDeath();
        }
    }
    
    public double getDirection(Position currPos, Position trackedPos) {
        double newDir = Math.atan((getdy(currPos, trackedPos)) / (getdx(currPos, trackedPos)));
        return getdx(currPos, trackedPos) < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir); 
    }
    
    public double getdx(Position currPos, Position trackedPos) {
        return trackedPos.getX() - currPos.getX();
    }
    
    public double getdy(Position currPos, Position trackedPos) {
        return trackedPos.getY() - currPos.getY();
    }
    
    public void firstApply(Unit u, Unit tracked) {
        futureApply(u, tracked);
        u.getParents().get(0).getProperties().getVelocity().setDirection(getDirection(u.getProperties().getPosition(),
                                                                                     tracked.getProperties().getPosition()));
    }

}
