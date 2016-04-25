package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class GridFactory extends PathGraphFactory{

	private PositionHandler myPositionHandler;
	private BranchHandler myBranchHandler;

	public GridFactory(){
		myPositionHandler = new PositionHandler();
		myBranchHandler = new BranchHandler();
	}

	public PathGraph createUnlimitedPathGraph(double width, double length, double sideLength){
		PathGraph pathGrid = new PathGraph();
		Position[][] positionGrid = createPosGrid(width, length, sideLength);
		List<List<Position>> branchPosLists = createBranchPosLists(positionGrid);
		for(List<Position> branchPos : branchPosLists){
			insertBranchInPath(branchPos, pathGrid);
		}
		return pathGrid;
	}

	private void insertBranchInPath(List<Position> branchPos, PathGraph path){
		Branch newBranch = new Branch(branchPos);
		myBranchHandler.configureBranch(newBranch, path);
	}

	private List<List<Position>> createBranchPosLists(Position[][] grid){
		List<List<Position>> branchPosLists = new ArrayList<>();
		for(int r=0; r<grid.length; r++){
			for(int c=0; c<grid[r].length; c++){
				Position pos = grid[r][c];
				if((r+1) < grid.length){
					Position neighbor1 = grid[r+1][c];
					List<Position> branchEndPos = Arrays.asList(pos, neighbor1);
					branchPosLists.add(myPositionHandler.getInterpolatedPositions(branchEndPos, false)); 
				}
				if((c+1) < grid[0].length){
					Position neighbor2 = grid[r][c+1];
					List<Position> branchEndPos = Arrays.asList(pos, neighbor2);
					branchPosLists.add(myPositionHandler.getInterpolatedPositions(branchEndPos, false)); 
				}
			}
		}
		return branchPosLists;
	}

	private Position[][] createPosGrid(double width, double length, double sideLength){
		int numSquareCols = (int)Math.floor(width/sideLength) + 1;
		int numSquareRows = (int)Math.floor(length/sideLength) + 1;
		Position[][] posGrid = new Position[numSquareCols][numSquareRows];
		for(int x=0; x<numSquareCols; x++){
			for(int y=0; y<numSquareRows; y++){
				posGrid[x][y] = new Position(x*sideLength, y*sideLength);
			}
		}
		return posGrid;
	}

}