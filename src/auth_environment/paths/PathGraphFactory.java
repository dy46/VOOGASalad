package auth_environment.paths;

import java.util.ArrayList;
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

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts branch with newPath positions into path graph
	 */
	public void insertBranch(List<Position> branchPos){
		if(branchPos.size() == 0)
			return;
		Branch newBranch = new Branch(getNextBranchID(), branchPos);
		PathNode currentPath = myPathGraph.getPathByPos(branchPos.get(0));
		if(currentPath != null){
			configureBranchInPath(newBranch, currentPath);
		}
		else{
			currentPath = myPathGraph.getPathByPos(branchPos.get(branchPos.size()-1));
			if(currentPath != null){
				configureBranchInPath(newBranch, currentPath);
			}
			else{
				if(branchPos.size() > 0){
					createNewPath(newBranch);
				}
			}
		}
	}

	private PathNode createNewPath(Branch branch){
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

	private void configureBranchInPath(Branch newPathNode, PathNode myPath){
		Position startPos = newPathNode.getFirstPosition();
		Position endPos = newPathNode.getLastPosition();
		configureBranchesWithEdgePos(newPathNode, myPath, startPos);
		if(!startPos.equals(endPos)){
			configureBranchesWithEdgePos(newPathNode, myPath, endPos);
		}
		configureBranchesWithMidPos(newPathNode, myPath, startPos);
		if(!startPos.equals(endPos)){
			configureBranchesWithMidPos(newPathNode, myPath, endPos);
		}
	}

	private void configureBranchesWithEdgePos(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesAtPos = myPath.getBranchByEdgePosition(pos);
		List<Branch> branchesChecked = new ArrayList<>();
		for(Branch branch : branchesAtPos){
			if(!branchesChecked.contains(branch)){
				branchesChecked.add(branch);
				if(!branch.equals(newBranch)){
					newBranch.addNeighbor(branch);
					branch.addNeighbor(newBranch);
				}
			}
		}
		if(!myPath.getBranches().contains(newBranch))
			myPath.addBranch(newBranch);
	}

	private void configureBranchesWithMidPos(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesToSplit = new ArrayList<>();
		for(Branch b : myPath.getBranches()){
			if(b.getPositions().contains(pos)){
				if(!b.getFirstPosition().equals(pos) && !b.getLastPosition().equals(pos))
					branchesToSplit.add(b);
			}
		}
		splitBranches(newBranch, myPath, pos, branchesToSplit);
	}

	private void splitBranches(Branch newBranch, PathNode myPath, Position pos, List<Branch> branchesToSplit){
		for(Branch b : branchesToSplit){
			List<Position> cutoffPositions = b.cutoffByPosition(pos);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			List<Branch> cutoffConnectedPaths = myPath.getBranchByEdgePosition(lastCutoff);
			Branch newSplitPath = new Branch(getNextPathID());
			newSplitPath.addPositions(cutoffPositions);
			newSplitPath.addNeighbor(b);
			for(Branch p : cutoffConnectedPaths){
				if(!branchesToSplit.contains(p)){
					branchesToSplit.add(p);
					if(p.getPositions().contains(pos))
						newSplitPath.addNeighbors(b.removeNeighbors(p.getNeighbors()));
				}
			}
			newBranch.addNeighbor(b);
			newBranch.addNeighbor(newSplitPath);
			newSplitPath.addNeighbor(newBranch);
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