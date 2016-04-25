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
									if(pos != null && neighbor != null){
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
				}
				pastPos.add(pos);
			}
		}
		return branchPosLists;
	}

	private Position[][] createPosGrid(double width, double length, double sideLength){
		int numSquareCols = (int)Math.floor(width/sideLength);
		int numSquareRows = (int)Math.floor(length/sideLength);
		int numExtraCols = extraColNeeded(numSquareCols, width, sideLength) ? 1 : 0;
		int numExtraRows = extraRowNeeded(numSquareRows, length, sideLength) ? 1 : 0;
		Position[][] posGrid = new Position[numSquareCols + numExtraCols +1][numSquareRows + numExtraRows + 1];
		for(int x=0; x<=numSquareCols; x++){
			for(int y=0; y<=numSquareRows; y++){
				posGrid[x][y] = new Position(x*sideLength, y*sideLength);
			}
		}
		for(int x=numSquareCols+1; x<posGrid.length; x++){
			posGrid[x][numSquareRows-1] = new Position(x*sideLength, numSquareRows*sideLength);
		}
		for(int y=numSquareRows+1; y<posGrid[0].length; y++){
			posGrid[numSquareCols-1][y] = new Position(numSquareCols*sideLength, y*sideLength);
		}
		return posGrid;
	}

	private boolean extraColNeeded(int numSquareCols, double width, double sideLength){
		return width/sideLength > numSquareCols;
	}

	private boolean extraRowNeeded(int numSquareRows, double length, double sideLength){
		return length/sideLength > numSquareRows;
	}

}