package game_engine.handlers;

import java.util.List;

import game_engine.properties.Position;

public class BoundsHandler {

	public static double getMaxBoundingDistance(List<Position> positions){
		double minX = getMinX(positions);
		double maxX = getMaxX(positions);
		double minY = getMinY(positions);
		double maxY = getMaxY(positions);
		double centerX = (minX + maxX)/2;
		double centerY = (minY + maxY)/2;
		return getMaxDistanceBetween(positions, centerX, centerY);
	}
	
	private static double getMaxDistanceBetween(List<Position> positions, double centerX, double centerY){
		double maxDistance = Integer.MIN_VALUE;
		for(Position pos : positions){
			double distance = getManhattanDistance(centerX, centerY, pos);
			if(distance > maxDistance){
				maxDistance = distance;
			}
		}
		return maxDistance;
	}
	
	private static double getManhattanDistance(double x, double y, Position pos){
		return Math.sqrt(Math.pow(getDx(pos, x), 2) + Math.pow(getDy(pos, y), 2));
	}
	
	private static double getDx(Position pos, double x){
		return Math.abs(pos.getX() - x);
	}
	
	private static double getDy(Position pos, double y){
		return Math.abs(pos.getY() - y);
	}
	
	private static double getMinX(List<Position> positions){
		double minX = Integer.MAX_VALUE;
		for(Position pos : positions){
			double currX = pos.getX();
			if(currX < minX){
				minX = currX;
			}
		}
		return minX;
	}
	
	private static double getMaxX(List<Position> positions){
		double maxX = Integer.MIN_VALUE;
		for(Position pos : positions){
			double currX = pos.getX();
			if(currX > maxX){
				maxX = currX;
			}
		}
		return maxX;
	}
	
	private static double getMinY(List<Position> positions){
		double minY = Integer.MAX_VALUE;
		for(Position pos : positions){
			double currY = pos.getY();
			if(currY < minY){
				minY = currY;
			}
		}
		return minY;
	}
	
	private static double getMaxY(List<Position> positions){
		double maxY = Integer.MIN_VALUE;
		for(Position pos : positions){
			double currY = pos.getY();
			if(currY > maxY){
				maxY = currY;
			}
		}
		return maxY;
	}
	
}