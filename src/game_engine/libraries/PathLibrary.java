package game_engine.libraries;

import java.util.List;

import auth_environment.paths.PathGraph;
import game_engine.game_elements.Branch;

/**
 * This class represents a library or database of paths, stored in a path graph, which is a list of branches.
 * 
 * @author adamtache
 *
 */

public class PathLibrary {

	private PathGraph myPathGraph;

	public PathLibrary(){
		myPathGraph = new PathGraph();
	}

	public PathLibrary(List<Branch> branches){
		this.myPathGraph = new PathGraph(branches);
	}

	public PathGraph getPathGraph(){
		return myPathGraph;
	}

	public List<Branch> getBranches(){
		return myPathGraph.getBranches();
	}

	public PathGraph getGraph(){
		return myPathGraph;
	}

}