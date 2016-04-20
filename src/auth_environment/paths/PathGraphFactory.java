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
		currentPath = myPathGraph.getPathByPos(branchPos.get(branchPos.size()-1));
		if(currentPath != null){
			configureBranchInPath(newBranch, currentPath);
		}
		if(myPathGraph.getBranchByID(newBranch.getID()) == null){
			if(branchPos.size() > 0){
				createNewPath(newBranch);
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
		configureMidBranchSplits(newPathNode, myPath, startPos);
		if(!startPos.equals(endPos)){
			configureMidBranchSplits(newPathNode, myPath, endPos);
		}
		configureNewSplits(newPathNode, myPath);
	}

	private void configureBranchesWithEdgePos(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesAtPos = myPath.getBranchesByEdgePosition(pos);
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

	private void configureMidBranchSplits(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesToSplit = myPath.getBranchesByMidPosition(pos);
		branchesToSplit.stream().filter(b -> b.equals(newBranch));
		for(Branch b : branchesToSplit){
			System.out.println("SPLITTING: " + b+" AT POS: " + pos);
			List<Position> cutoffPositions = b.cutoffByPosition(pos);
			System.out.println("CUTOFF POSITIONS: " + cutoffPositions);
			System.out.println("ORIGINAL BRANCH POSITIONS: " + b);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			Branch newSplitBranch = new Branch(getNextBranchID());
			newSplitBranch.addPositions(cutoffPositions);
			newSplitBranch.addNeighbor(b);
			newSplitBranch.addNeighbor(newBranch);
			b.addNeighbor(newSplitBranch);
			b.addNeighbor(newBranch);
			List<Branch> cutoffConnectedBranches = myPath.getBranchesByEdgePosition(lastCutoff);
			for(Branch br : cutoffConnectedBranches){
				System.out.println("Cutoff connected branch: " + b);
				if(br.getPositions().contains(pos))
					newSplitBranch.addNeighbors(b.removeNeighbors(br.getNeighbors()));
			}
			//newBranch.addNeighbor(b);
			newBranch.addNeighbor(newSplitBranch);
			myPath.addBranch(newSplitBranch);
		}
	}

	private void configureNewSplits(Branch myBranch, PathNode myPath){
		for(Position pos : myBranch.getPositions()){
			List<Branch> branches = myPath.getBranchesByMidPosition(pos);
			for(Branch b : branches){
				if(!b.equals(myBranch)){

				}
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