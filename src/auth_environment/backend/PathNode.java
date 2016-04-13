package auth_environment.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.properties.Position;

public class PathNode {

	private List<Position> myPositions;
	private List<Terrain> myTerrains;
	private List<Enemy> myEnemies;
	private PathNode myParent;
	private List<PathNode> myChildren;
	
	public PathNode(PathNode parent, Position startingPos){
		this.myParent = parent;
		myPositions = Arrays.asList(startingPos);
		myTerrains = new ArrayList<>();
		myChildren = new ArrayList<>();
	}
	
	public List<Position> getPositions(){
		return myPositions;
	}
	
	public List<Terrain> getTerrains(){
		return myTerrains;
	}
	
	public List<Enemy> getEnemies(){
		return myEnemies;
	}
	
	public PathNode getParent(){
		return myParent;
	}
	
	public List<PathNode> getChildren(){
		return myChildren;
	}
	
}