package auth_environment.backend;

import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Path;
import game_engine.game_elements.Terrain;

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
	
	public List<Enemy> getEnemies(){
		return null;
	}
	
	public List<Path> getPaths(){
		return null;
	}
	
	public List<Terrain> getTerrains(){
		return null;
	}
	
}