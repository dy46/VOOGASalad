package auth_environment.backend;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Path;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;

public class PathTree {

	private PathNode myRoot;
	
	public PathTree(){
		this.myRoot = new PathNode();
	}
	
	public PathTree(PathNode root){
		this.myRoot = root;
	}
	
	public PathNode getRoot(){
		return myRoot;
	}
	
	public List<Unit> getEnemies(){
		return getUnits(myRoot, new Enemy("0", new ArrayList<Affector>(), 0));
	}
	
	public List<Unit> getPaths(){
		return getUnits(myRoot, new Path("0"));
	}
	
	public List<Unit> getTerrains(){
		return getUnits(myRoot, new Terrain("0", 0));
	}
	
	// Uses post-order traversal to extract List of appropriate Unit
	private List<Unit> getUnits(PathNode node, Unit target){
		List<Unit> myUnits = new ArrayList<>();
		for(PathNode n : node.getChildren()){
			myUnits.addAll(n.getUnitsByType(target));
			myUnits.addAll(getUnits(n, target));
		}
		return myUnits;
	}
	
}