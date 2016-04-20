package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PathGraphFactory {

	private PathGraph myPathGraph;
	private int currentPathID;
	private int currentBranchID;

	public PathGraphFactory(){
		this.myPathGraph = new PathGraph();
		currentPathID = -1;
		currentPathID = -1;
	}

	public PathNode createPath(Branch branch){
		myPathGraph.addPath(new PathNode(getNextPathID(), branch));
		return myPathGraph.getLastPath();
	}

	private int getNextPathID(){
		currentPathID++;
		return currentPathID;
	}

	private int getNextBranchID(){
		currentBranchID++;
		return currentBranchID;
	}

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts branch with newPath positions into path graph
	 */
	public void insertBranch(List<Position> branchPos){
		if(branchPos.size() == 0)
			return;
		PathNode currentPath = myPathGraph.getPathByPos(branchPos.get(0));
		Branch newBranch = new Branch(getNextBranchID(), branchPos);
		if(currentPath != null){
			configureBranchInPath(newBranch, currentPath);
			//System.out.println(myPathGraph.getBranches());
		}
		else{
			if(branchPos.size() > 0){
				createPath(newBranch);
			}
		}
	}

	private void configureBranchInPath(Branch newPathNode, PathNode myPath){
		configureNeighboringBranches(newPathNode, myPath);
		configureSplitBranches(newPathNode, myPath);
	}

	private void configureNeighboringBranches(Branch newPathNode, PathNode myPath){
		Position startPos = newPathNode.getFirstPosition();
		Position endPos = newPathNode.getLastPosition();
		configureBranchesAtEdgePos(newPathNode, myPath, startPos);
		if(!newPathNode.getFirstPosition().equals(newPathNode.getLastPosition()))
			configureBranchesAtEdgePos(newPathNode, myPath, endPos);
	}

	private void configureBranchesAtEdgePos(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesAtPos = myPath.getBranchByEdgePosition(pos);
		List<Branch> branchesChecked = new ArrayList<>();
		for(Branch branch : branchesAtPos){
			if(!branch.equals(newBranch)){
				newBranch.addNeighbor(branch);
				branch.addNeighbor(newBranch);
				List<Branch> neighbor = branch.getNeighbors();
				for(Branch p : neighbor){
					if(!branchesChecked.contains(p)){
						branchesChecked.add(p);
						if(p.getAllPositions().contains(pos))
							p.addNeighbor(newBranch);
					}
				}
				newBranch.addNeighbors(neighbor);
			}
		}
		if(!myPath.getBranches().contains(newBranch))
			myPath.addBranch(newBranch);
	}

	private void configureSplitBranches(Branch newPathNode, PathNode myGraph){
		Position startingPos = newPathNode.getFirstPosition();
		Position endingPos = newPathNode.getLastPosition();
		Branch currentMidStartingPath = myGraph.getBranchByMidPosition(startingPos);
		Branch currentMidEndingPath = myGraph.getBranchByMidPosition(endingPos);
		List<Branch> edgePaths = Arrays.asList(currentMidStartingPath, currentMidEndingPath);
		for(Branch edgePath : edgePaths){
			if(edgePath != null){
				List<Position> cutoffPositions = edgePath.cutoffByPosition(startingPos);
				Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
				List<Branch> cutoffConnectedPaths = myGraph.getBranchByEdgePosition(lastCutoff);
				Branch newSplitPath = new Branch(getNextPathID());
				newSplitPath.addPositions(cutoffPositions);
				newSplitPath.addNeighbor(edgePath);
				for(Branch path : cutoffConnectedPaths){
					newSplitPath.addNeighbors(edgePath.removeNeighbors(path.getNeighbors()));
				}
				newPathNode.addNeighbor(edgePath);
				newPathNode.addNeighbor(newSplitPath);
				newSplitPath.addNeighbor(newPathNode);
			}
		}
	}

	public PathGraph getGraph(){
		return myPathGraph;
	}

	public List<PathNode> getPaths(){
		return myPathGraph.getPaths();
	}

	public List<Branch> getBranches(){
		return myPathGraph.getBranches();
	}

}