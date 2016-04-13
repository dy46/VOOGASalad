package auth_environment.backend;

import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.properties.Position;

public class TreeFactory {

	private PathTree myTree;

	public TreeFactory(){
		this.myTree = new PathTree();
	}

	// Adds path to tree in correct position
	public void addPath(List<Position> newPath){
		
	}

	// Adds enemy to tree on correct path
	public void addEnemy(Enemy enemy, PathNode path){

	}

	// Adds terrain to tree on correct path
	public void addTerrain(Enemy enemy, PathNode path){

	}

	// Splits PathNode into two PathNodes at specific point
	private void splitPath(PathNode path, Position position){
		
	}
	
	public PathTree getTree(){
		return myTree;
	}

}