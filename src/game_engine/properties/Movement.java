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
		if(getLastPath().equals(currentPath))
			return null;
		return myPaths.get(currentPathIndex++);
	}

	public double getNextDirection(Position currentPosition, double currDirection){
		// TODO: womp exception if nextDirection is null? this shouldn't happen
		if(currentPosition.equals(getLastPath().getLastPosition())) {
			return currDirection;
		}
		return currentPath.getNextDirection(currentPosition);
	}

	public Position getNextPosition(Position currentPosition){
		if(currentPath == null){
			return getLastPath().getLastPosition();
		}
		Position next = currentPath.getNextPosition(currentPosition);
		if(next == null){
			System.out.println("My paths: " + myPaths.size());
			currentPath = getNextPath();
			if(currentPath == null) {
				return getLastPath().getLastPosition();
			}
			next = currentPath.getFirstPosition();
		}
		return next;
	}

}