package auth_environment.backend;

import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.properties.Position;

public class PathForestFactory {

	private PathForest myForest;

	public PathForestFactory(){
		this.myForest = new PathForest();
	}

	// Adds path to tree in correct position
	public void addPath(List<Position> newPath, String treeID){
		PathTree myTree = myForest.getTreeByID(treeID);
		if(myTree != null && newPath.size() > 0){
			Position startingPos = newPath.get(0);

		}
	}

	// Adds enemy to tree on correct path
	public void addEnemy(Enemy enemy, String pathID){
		myForest.getPathByID(pathID).addEnemy(enemy);
	}

	// Adds terrain to tree on correct path
	public void addTerrain(Terrain terrain, String pathID){
		myForest.getPathByID(pathID).addTerrain(terrain);
	}

	// Splits PathNode into two PathNodes at specific point
	private void splitPath(String pathID, Position position){

	}

	public PathForest getForest(){
		return myForest;
	}

}