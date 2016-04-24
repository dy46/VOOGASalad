package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.Property;
import game_engine.properties.UnitProperties;
import java.awt.MouseInfo;
import java.awt.Point;


public class CursorDirectionAffector extends Affector {

    private boolean firstApplication;

    public CursorDirectionAffector (AffectorData data) {
        super(data);
        firstApplication = true;
    }

    @Override
    public void apply (List<Function> functions, Property property, Unit u) {
        UnitProperties properties = u.getProperties();
        if(firstApplication) {
        double speed = properties.getVelocity().getSpeed();
        Position mouse = getWS().getCursorPosition();
        Position currPos = properties.getPosition();
        double dx = getdx(currPos, mouse);
        double dy = getdy(currPos, mouse);
        double num = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        properties.getVelocity().setDirection(getDirection(currPos, mouse));
        
        properties.getPosition().addToXY(speed*dx/num, 
                                         speed*dy/num);
        u.getParents().get(0).getProperties().getVelocity().setDirection(getDirection(u.getProperties().getPosition(),
                                                                                      mouse));
        firstApplication = false;
        }
        else {
            double speed = u.getProperties().getVelocity().getSpeed();
            double direction = u.getProperties().getVelocity().getDirection();
            double xDir = Math.sin(Math.toRadians(direction));
            double yDir = Math.cos(Math.toRadians(direction));
            u.getProperties().getPosition().addToXY(speed*xDir, speed*yDir);
        }
    }

    public double getdx (Position currPos, Position trackedPos) {
        return trackedPos.getX() - currPos.getX();
    }

    public double getdy (Position currPos, Position trackedPos) {
        return trackedPos.getY() - currPos.getY();
    }

    public double getDirection (Position currPos, Position trackedPos) {
        double newDir = Math.atan((getdy(currPos, trackedPos)) / (getdx(currPos, trackedPos)));
        return getdx(currPos, trackedPos) < 0 ? 270 - Math.toDegrees(newDir)
                                              : 90 - Math.toDegrees(newDir);
    }

}
