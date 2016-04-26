package game_engine.physics;

import game_engine.properties.Position;


public class DirectionHandler {
    
    public static double getDirection(Position from, Position to) {
        double newDir = Math.atan((getdy(from, to)) / (getdx(from, to)));
        return getdx(from, to) < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir); 
    }
    
    public static double getdx(Position from, Position to) {
        return to.getX() - from.getX();
    }
    
    public static double getdy(Position from, Position to) {
        return to.getY() - from.getY();
    }

}
