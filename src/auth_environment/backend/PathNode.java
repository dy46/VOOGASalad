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

	public PathNode() {
		this.myTerrains = new ArrayList<>();
		this.myPositions = new ArrayList<>();
		this.myEnemies = new ArrayList<>();
		this.myParent = null;
		this.myChildren = new ArrayList<>();
	}

	public void addEnemy(Enemy enemy){
		this.myEnemies.add(enemy);
	}
	
	public void addTerrain(Terrain terrain){
		this.myTerrains.add(terrain);
	}
	
	public void addChild(PathNode child){
		this.myChildren.add(child);
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