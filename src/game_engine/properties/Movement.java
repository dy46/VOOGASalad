package game_engine.properties;

import java.util.List;

import game_engine.game_elements.Path;
import game_engine.game_elements.Unit;

public class Movement {

	private List<Path> myPaths;
	private int currentPathIndex;
	private Path currentPath;

	public Movement(List<Path> paths){
		this.myPaths = paths;
		currentPathIndex = 0;
		if(paths.size() > 0)
			currentPath = myPaths.get(0);
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
		return currentPath;
	}

	private Path getLastPath(){
		return myPaths.get(myPaths.size()-1);
	}

	public Path getNextPath() {
		currentPath = myPaths.get(currentPathIndex++);
		return currentPath;
	}

}