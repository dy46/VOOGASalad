package game_engine.libraries;

import java.util.List;

import auth_environment.paths.PathGraph;
import auth_environment.paths.PathNode;
import game_engine.game_elements.Branch;

public class PathLibrary {

	private PathGraph myPathGraph;
	private PathNode myPathGrid;
	
	public PathLibrary(){
		myPathGraph = new PathGraph();
	}
	
	public PathLibrary(PathGraph pathGraph, PathNode pathGrid){
		this.myPathGraph = pathGraph;
		this.myPathGrid = pathGrid;
	}

	public void setPathGrid(PathNode pathGrid) {
		this.myPathGrid = pathGrid;
	}
	
	public PathGraph getPathGraph(){
		return myPathGraph;
	}
	
	public PathNode getPathGrid(){
		return myPathGrid;
	}
	
	public List<PathNode> getPaths(){
		return myPathGraph.getPaths();
	}

	public List<Branch> getBranches(){
		return myPathGraph.getBranches();
	}

	public PathGraph getGraph(){
		return myPathGraph;
	}

	public int getLastPathID() {
		int maxPathID = Integer.MIN_VALUE;
		for(PathNode n : myPathGraph.getPaths()){
			int currID = n.getID();
			if(currID > maxPathID){
				maxPathID = currID;
			}
		}
		return maxPathID;
	}
	
	public int getLastBranchID() {
		int maxBranchID = Integer.MIN_VALUE;
		for(Branch b : myPathGraph.getBranches()){
			int currID = b.getID();
			if(currID > maxBranchID){
				maxBranchID = currID;
			}
		}
		return maxBranchID;
	}
	
}