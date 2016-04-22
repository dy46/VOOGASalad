package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.libraries.PathLibrary;
import game_engine.properties.Position;

public class PathGraphFactory {

	private PathLibrary myPathLibrary;
	private PositionHandler myPositionHandler;
	private int currentPathID;
	private int currentBranchID;

	public PathGraphFactory(){
		myPathLibrary = new PathLibrary();
		myPositionHandler = new PositionHandler();
		currentPathID = -1;
		currentBranchID = -1;
	}

	public PathGraphFactory(PathLibrary pathLibrary){
		this.myPathLibrary = pathLibrary;
		currentPathID = myPathLibrary.getLastPathID();
		currentBranchID = myPathLibrary.getLastBranchID();
		myPositionHandler = new PositionHandler();
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
		PathNode currentPath = myPathLibrary.getPathGraph().getPathByPos(branchPos.get(0));
		if(currentPath != null){
			//System.out.println("CONFIGURING " + branchPos.get(0)+" "+branchPos.get(branchPos.size()-1));
			configureBranchInPath(newBranch, currentPath);
		}
		else{
			currentPath = myPathLibrary.getPathGraph().getPathByPos(branchPos.get(branchPos.size()-1));
			if(currentPath != null){
				configureBranchInPath(newBranch, currentPath);
			}
		}
		if(myPathLibrary.getPathGraph().getBranchByID(newBranch.getID()) == null){
			//System.out.println("CREATING NEW PATH " + branchPos.get(0)+" "+branchPos.get(branchPos.size()-1));
			if(branchPos.size() > 0){
				createNewPath(newBranch);
			}
		}
	}

	public void createUnlimitedPathGraph(double width, double length, double sideLength){
		currentPathID = -2;
		Position[][] positionGrid = createPosGrid(width, length, sideLength);
		List<List<Position>> branchPosLists = createBranchPosLists(positionGrid);
		for(List<Position> branchPos : branchPosLists){
			//System.out.println("Branching position list: " + branchPos);
			insertBranch(branchPos);
		}
	}

	private List<List<Position>> createBranchPosLists(Position[][] grid){
		List<List<Position>> branchPosLists = new ArrayList<>();
		List<Position> pastPos = new ArrayList<>();
		for(int r=0; r<grid.length; r++){
			for(int c=0; c<grid[r].length; c++){
				Position pos = grid[r][c];
				for(int x=-1; x<2; x++){
					for(int y=-1; y<2; y++){
						int neighborX = r+x;
						int neighborY = c+y;
						if(neighborX >= 0 && neighborX < grid.length){
							if(neighborY >= 0 && neighborY < grid[r].length){
								if(x==0 || y==0){
									Position neighbor = grid[neighborX][neighborY];
									List<Position> toInterpolate = Arrays.asList(pos, neighbor);
									if(!pastPos.contains(pos) && !pastPos.contains(neighbor)){
										List<Position> interpolated = myPositionHandler.getInterpolatedPositions(toInterpolate, false);
										if(!branchPosLists.contains(interpolated)){
											branchPosLists.add(interpolated);
										}
									}
								}
							}
						}
					}
				}
				pastPos.add(pos);
			}
		}
		return branchPosLists;
	}

	private Position[][] createPosGrid(double width, double length, double sideLength){
		Position[][] posGrid = new Position[(int)Math.floor(width/sideLength)][(int)Math.floor(length/sideLength)];
		for(int x=0; x<posGrid.length; x++){
			for(int y=0; y<posGrid[x].length; y++){
				posGrid[x][y] = new Position(x*sideLength, y*sideLength);
			}
		}
		return posGrid;
	}

	private PathNode createNewPath(Branch branch){
		myPathLibrary.getPathGraph().addPath(new PathNode(getNextPathID(), branch));
		return myPathLibrary.getPathGraph().getLastPath();
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
		//System.out.println("MID SPLITTING: " + branchesToSplit);
		branchesToSplit.stream().filter(b -> b.equals(newBranch));
		for(Branch b : branchesToSplit){
			List<Position> cutoffPositions = b.cutoffByPosition(pos);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			Branch newSplitBranch = new Branch(getNextBranchID());
			newSplitBranch.addPositions(cutoffPositions);
			newSplitBranch.addNeighbor(b);
			newSplitBranch.addNeighbor(newBranch);
			b.addNeighbor(newSplitBranch);
			b.addNeighbor(newBranch);
			List<Branch> cutoffConnectedBranches = myPath.getBranchesByEdgePosition(lastCutoff);
			for(Branch br : cutoffConnectedBranches){
				newSplitBranch.addNeighbors(b.removeNeighbors(br.getNeighbors()));
			}
			newBranch.addNeighbor(b);
			newBranch.addNeighbor(newSplitBranch);
			myPath.addBranch(newSplitBranch);
		}
	}

	public PathLibrary getPathLibrary() {
		return myPathLibrary;
	}

}