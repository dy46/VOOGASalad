package game_engine.game_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import game_engine.properties.Position;

import java.util.List;

/*
 * Internal API that will be used in order to represent paths 
 * for enemy movements.
 */
public class Branch{

	private List<Position> myPositions;
	private Map<Position, Position> nextPositions;
	private boolean cycle;
	private List<Branch> myNeighbors;
	private int myID;

	public Branch(List<Position> positions, int ID){
		this.myID = ID;
		myPositions = positions;
		myNeighbors = new ArrayList<>();
	}

	public Branch(int ID) {
		this.myPositions = new ArrayList<>();
		this.myNeighbors = new ArrayList<>();
	}

	public Branch(String name){
		//		setID(getWorkspace().getIDFactory().createID(this));
		cycle = false;
		initialize();
	}

	public Branch(String name, List<Position> positions){
		cycle = false;
		initialize(positions, new ArrayList<>());
	}

	public Branch(String name, List<Position> positions, List<Branch> neighbors) {
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
				Position newPosition = new Position(x + vx, y + vy);
				//myPositions.add(newPosition);
				nextPositions.put(new Position(x, y), newPosition);
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

	public Branch copyBranch(){
		Branch newPath = new Branch("");
		this.myPositions.forEach(t -> {
			newPath.addPosition(t.copyPosition());
		});
		newPath.addNeighbors(myNeighbors.stream().map(b -> b.copyBranch()).collect(Collectors.toList()));
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
			nextPosition = currentPosition;
		}
		double dx = nextPosition.getX() - currentPosition.getX();
		double dy = nextPosition.getY() - currentPosition.getY();
		double newDir = Math.atan((dy) / (dx));
		double degreesDir = dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
		return degreesDir;
	}

	public Position getLastPosition() {
		return myPositions.get(myPositions.size()-1);
	}
	
	public Position getSecondPosition(){
		if(getAllPositions().size() <= 1)
			return null;
		return getAllPositions().get(1);
	}

	public void addNeighbor(Branch neighbor){
		this.myNeighbors.add(neighbor);
	}

	public void addPositions(List<Position> positions){
		this.myPositions.addAll(positions);
	}

	public List<Position> getPositions(){
		return myPositions;
	}

	public List<Branch> getNeighbors(){
		return myNeighbors;
	}

	public int getID(){
		return myID;
	}

	public List<Position> cutoffByPosition(Position pos){
		List<Position> cutoff = myPositions.subList(myPositions.indexOf(pos), myPositions.size());
		cutoff.clear();
		return cutoff;
	}

	public void addNeighbors(List<Branch> neighbors) {
		myNeighbors.addAll(neighbors);
	}

	public List<Branch> removeNeighbors(List<Branch> neighbors){
		List<Branch> removed = new ArrayList<>();
		for(Branch neighbor : neighbors){
			if(myNeighbors.contains(neighbor)){
				myNeighbors.remove(neighbor);
				removed.add(neighbor);
			}
		}
		return removed;
	}

	public String toString(){
		return "Branch positions: " + myPositions;
	}

	public int getLength(){
		return getAllPositions().size();
	}

	public List<Branch> getForwardNeighbors(){
		List<Branch> forwards = new ArrayList<>();
		for(Branch b : myNeighbors){
			if(b.getFirstPosition().equals(getLastPosition())){
				forwards.add(b);
			}
		}
		return forwards;
	}

	public boolean isAccessible(Position p){
		for(Branch b : getForwardNeighbors()){
			if(b.getPositions().contains(p)){
				return true;
			}
		}
		return false;
	}

}