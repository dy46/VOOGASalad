package auth_environment.paths;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class PathNode {

	private List<Position> myPositions;
	private List<PathNode> myNeighbors;
	private int myID;

	public PathNode(List<Position> positions, int ID){
		this.myID = ID;
		myPositions = positions;
		myNeighbors = new ArrayList<>();
	}

	public PathNode(int ID) {
		this.myPositions = new ArrayList<>();
		this.myNeighbors = new ArrayList<>();
	}

	public void addNeighbor(PathNode neighbor){
		this.myNeighbors.add(neighbor);
	}

	public void addPositions(List<Position> positions){
		this.myPositions.addAll(positions);
	}

	public List<Position> getPositions(){
		return myPositions;
	}

	public List<PathNode> getNeighbors(){
		return myNeighbors;
	}

	public int getID(){
		return myID;
	}

	public List<Unit> getUnitsByType(Unit type){
		String className = type.getClass().getSimpleName();
		String instanceVarName = "my" + className + "s";
		Field f = null;
		try {
			f = getClass().getDeclaredField(instanceVarName);
		}
		catch (NoSuchFieldException | SecurityException e1) {
			// TODO: womp exception
			e1.printStackTrace();
		}
		f.setAccessible(true);
		List<Unit> listInstanceVar = null;
		try {
			listInstanceVar = (List<Unit>) f.get(this);
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception
			e.printStackTrace();
		}
		return listInstanceVar;
	}

	public List<Position> cutoffByPosition(Position pos){
		List<Position> cutoff = myPositions.subList(myPositions.indexOf(pos), myPositions.size());
		cutoff.clear();
		return cutoff;
	}

	public void addNeighbors(List<PathNode> neighbors) {
		myNeighbors.addAll(neighbors);
	}

	public List<PathNode> removeNeighbors(List<PathNode> neighbors){
		List<PathNode> removed = new ArrayList<>();
		for(PathNode neighbor : neighbors){
			if(myNeighbors.contains(neighbor)){
				myNeighbors.remove(neighbor);
				removed.add(neighbor);
			}
		}
		return removed;
	}

}