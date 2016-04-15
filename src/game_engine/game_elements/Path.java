package game_engine.game_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import auth_environment.paths.Branch;

import java.util.List;
import java.util.Map;
import game_engine.properties.Position;

/*
 * Internal API that will be used in order to represent paths 
 * for enemy movements.
 */
public class Path extends Unit{

	private List<Position> myPositions;
	private Map<Position, Position> nextPositions;
	private boolean cycle;
	private List<Branch> myNeighbors;

	public Path(String name){
		super(name);
		//		setID(getWorkspace().getIDFactory().createID(this));
		cycle = false;
		initialize();
	}

	public Path(String name, List<Position> positions, List<Branch> neighbors) {
		super(name);
		cycle = false;
		initialize(positions, neighbors);
	}

	public void initialize(){
		myPositions = new ArrayList<>();
		nextPositions = new HashMap<Position, Position>();
		myNeighbors = new ArrayList<>();
		setNextPositions();
	}

	public void initialize(List<Position> list, List<Branch> neighbors){
		myPositions = list;
		nextPositions = new HashMap<Position, Position>();
		myNeighbors = neighbors;
		setNextPositions();
	}
	
	public void setCycle(boolean state){
		cycle = state;
		setNextPositions();
	}
	public void addPosition(Position append){
		myPositions.add(append);
		setNextPositions();
	}
	private void setNextPositions(){
		int size = cycle ? myPositions.size() : myPositions.size() - 1;
		if(myPositions.size() < 1){
			return;
		}
		double x = myPositions.get(0).getX();
		double y = myPositions.get(0).getY();
		for(int i = 0;i < size;i++){
			Position p1 = myPositions.get(i);
			Position p2 = myPositions.get((i+1)%myPositions.size());
			double vx = p2.getX() - p1.getX();
			double vy = p2.getY() - p1.getY();
			double mag = Math.sqrt(vx*vx + vy*vy);
			vx /= mag;
			vy /= mag;
			while((vx == 0 || (p2.getX() - x)/vx > 0 ) && (vy == 0 || (p2.getY() - y)/vy > 0)){
				nextPositions.put(new Position(x, y), new Position(x + vx, y + vy));
				x += vx;
				y += vy;
			}
			nextPositions.put(new Position(x - vx, y - vy), p2);
			x = p2.getX();
			y = p2.getY();
		}
	}


	/*
	 * Gets the next position (point) in the path.
	 * This will be used in order to determine which direction 
	 * an Enemy needs to move in next.
	 *
	 * @return	The next Position in the list of Positions that represent the path being taken.
	 */
	public Position getNextPosition(Position currentPosition){
		if(currentPosition.equals(myPositions.get(myPositions.size()-1))){
			return null;
		}
		if(nextPositions.containsKey(currentPosition)){
			return nextPositions.get(currentPosition);
		}
		else{
			double minDist = Double.MAX_VALUE;
			Position closest = null;
			for(Position pos : nextPositions.keySet()){
				if(pos.distanceTo(currentPosition) < minDist){
					closest = pos;
					minDist = pos.distanceTo(currentPosition);
				}
			}
			return nextPositions.get(closest);
		}
	}

	public String toFile(){
		return getID();
	}
	public Path copyPath(){
		Path newPath = new Path("");
		this.myPositions.forEach(t -> {
			newPath.addPosition(t.copyPosition());
		});
		return newPath;
	}

	public List<Position> getAllPositions() {
		List<Position> allPositions = new ArrayList<>();
		allPositions.addAll(nextPositions.keySet());
		return allPositions;
	}

	public boolean isUnitAtLastPosition(Unit u) {
		Position lastPos = myPositions.get(myPositions.size()-1);
		return u.getProperties().getPosition().getX() == lastPos.getX() &&
				u.getProperties().getPosition().getY() == lastPos.getY();
	}

	public List<Position> getMyPositions() {
		return myPositions;
	}

	public Position getFirstPosition() {
		return myPositions.get(0);
	}
	
	public Double getNextDirection (Position currentPosition) {
        Position nextPosition = getNextPosition(currentPosition);
        if(nextPosition == null){
        	return null;
        }
        double dx = 
                nextPosition.getX() - currentPosition.getX();
        double dy = nextPosition.getY() - currentPosition.getY();
        double newDir = Math.atan((dy) / (dx));
        double degreesDir = dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
        return degreesDir;
    }

	public Position getLastPosition() {
		return myPositions.get(myPositions.size()-1);
	}

}