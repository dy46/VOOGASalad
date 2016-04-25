package game_engine.physics;

import game_engine.properties.Position;

public class DirectionHandler {

	public static Double getDirectionBetween(Position currentPosition, Position nextPosition){
		double dx = nextPosition.getX() - currentPosition.getX();
		double dy = nextPosition.getY() - currentPosition.getY();
		double newDir = Math.atan2((dy), (dx));
		return dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
	}
	
}