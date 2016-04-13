package auth_environment.backend;

import java.util.List;

// PathForest is a set of disjoint PathTrees that represent all the paths for an instance of a game
public class PathForest {

	private List<PathTree> myTrees;
	
	public PathForest(List<PathTree> trees){
		this.myTrees = trees;
	}
	
	public List<PathTree> getTrees(){
		return myTrees;
	}
	
}