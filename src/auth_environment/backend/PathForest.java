package auth_environment.backend;

import java.util.ArrayList;
import java.util.List;

// PathForest is a set of disjoint PathTrees that represent all the paths for an instance of a game
public class PathForest {

	private List<PathTree> myTrees;

	public PathForest(List<PathTree> trees){
		this.myTrees = trees;
	}

	public PathForest() {
		myTrees = new ArrayList<>();
	}
	
	public void addTree(PathTree tree){
		myTrees.add(tree);
	}

	public List<PathTree> getTrees(){
		return myTrees;
	}

	public PathTree getTreeByID(String ID){
		for(PathTree tree : myTrees){
			if(tree.getID().equals(ID)){
				return tree;
			}
		}
		return null;
	}

	public PathNode getPathByID(String ID){
		for(PathTree tree : myTrees){
			List<PathNode> paths = tree.getPathNodes(tree.getRoot());
			for(PathNode p : paths){
				if(p.getID().equals(ID)){
					return p;
				}
			}
		}
		return null;
	}

}