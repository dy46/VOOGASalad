package game_engine.properties;

import java.util.List;

import game_engine.game_elements.Path;
import game_engine.game_elements.Unit;

public class Movement {

	private List<Path> myPaths;
	private int currentPath;
	
	public Movement(List<Path> paths){
		this.myPaths = paths;
		currentPath = 0;
	}
	
	public List<Path> getPaths(){
		return myPaths;
	}
	
	public void setPaths(List<Path> paths){
		this.myPaths = paths;
	}
	
	public boolean isUnitAtLastPosition(Unit u) {
		return getLastPath().isUnitAtLastPosition(u);
	}
	
	public Path getCurrentPath(){
		return myPaths.get(currentPath);
	}
	
	private Path getLastPath(){
		return myPaths.get(myPaths.size()-1);
	}
	
}