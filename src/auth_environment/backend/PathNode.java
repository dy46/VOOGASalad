package auth_environment.backend;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class PathNode {

	private List<Position> myPositions;
	private List<Terrain> myTerrains;
	private List<Enemy> myEnemys;
	private PathNode myParent;
	private List<PathNode> myChildren;
	private String myID;

	public PathNode(PathNode parent, Position startingPos, String ID){
		this.myID = ID;
		this.myParent = parent;
		myPositions = Arrays.asList(startingPos);
		myTerrains = new ArrayList<>();
		myChildren = new ArrayList<>();
	}

	public PathNode() {
		this.myID = "0";
		this.myTerrains = new ArrayList<>();
		this.myPositions = new ArrayList<>();
		this.myEnemys = new ArrayList<>();
		this.myParent = null;
		this.myChildren = new ArrayList<>();
	}

	public void addEnemy(Enemy enemy){
		this.myEnemys.add(enemy);
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
		return myEnemys;
	}

	public PathNode getParent(){
		return myParent;
	}

	public List<PathNode> getChildren(){
		return myChildren;
	}

	public String getID(){
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
}