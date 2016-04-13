package auth_environment.backend;

import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.properties.Position;

public class PathForestFactory {

	private PathForest myForest;

	public PathForestFactory(){
		this.myForest = new PathForest();
	}

	public PathTree createTree(String ID){
		return new PathTree(ID);
	}

	/**
	 * @param newPath
	 * @param treeID
	 * Inserts path with newPath positions into tree.
	 * Checks treeID for valid tree. If tree not found, creates new tree and creates path from newPath positions.
	 * If tree is found, checks starting and ending positions of newPath.
	 * Initializes newPathNode as new path for creation.
	 * If existing path found with edge position equal to starting or ending position of newPath, adds existing path's children to newPathNode
	 * If existing path found with middle position equal to starting or ending position of newPath, splits path.
	 * In case of split path, creates new path with newPath coordinates AND splits existing path into two.
	 * Two split pieces of new path get neighbors created corresponding to new neighbors that previously were neighbors of unsplit path.
	 * First half of unsplit path stays as same path node. Second half created as new path node.
	 */
	public void insertPath(List<Position> newPath, String treeID){
		PathTree myTree = myForest.getTreeByID(treeID);
		if(myTree != null && newPath.size() > 0){
			Position startingPos = newPath.get(0);
			Position endingPos = newPath.get(newPath.size()-1);
			List<PathNode> currentStartingPaths = myTree.getPathByEdgePosition(startingPos);
			List<PathNode> currentEndingPaths = myTree.getPathByEdgePosition(endingPos);
			PathNode newPathNode = new PathNode();
			newPathNode.addPositions(newPath);
			for(PathNode path : currentStartingPaths){
				List<PathNode> pathNeighbors = path.getNeighbors();
				for(PathNode p : pathNeighbors){
					p.addNeighbor(newPathNode);
				}
				newPathNode.addNeighbor(pathNeighbors);
			}
			for(PathNode path : currentEndingPaths){
				List<PathNode> pathChildren = path.getNeighbors();
				for(PathNode p : pathChildren){
					p.addNeighbor(newPathNode);
				}
				newPathNode.addNeighbor(pathChildren);
			}
			PathNode currentMidStartingPath = myTree.getPathByMidPosition(startingPos);
			PathNode currentMidEndingPath = myTree.getPathByMidPosition(endingPos);
			if(currentMidStartingPath != null){
				List<Position> cutoffPositions = currentMidStartingPath.cutoffByPosition(startingPos);
				Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
				List<PathNode> cutoffConnectedPaths = myTree.getPathByEdgePosition(lastCutoff);
				PathNode newSplitPath = new PathNode();
				newSplitPath.addPositions(cutoffPositions);
				newSplitPath.addNeighbor(currentMidStartingPath);
				for(PathNode path : cutoffConnectedPaths){
					newSplitPath.addNeighbor(currentMidStartingPath.removeNeighbors(path.getNeighbors()));
				}
				newPathNode.addNeighbor(currentMidStartingPath);
				newPathNode.addNeighbor(newSplitPath);
				newSplitPath.addNeighbor(newPathNode);
			}
			if(currentMidEndingPath != null){
				List<Position> cutoffPositions = currentMidEndingPath.cutoffByPosition(startingPos);
				Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
				List<PathNode> cutoffConnectedPaths = myTree.getPathByEdgePosition(lastCutoff);
				PathNode newSplitPath = new PathNode();
				newSplitPath.addPositions(cutoffPositions);
				newSplitPath.addNeighbor(currentMidEndingPath);
				for(PathNode path : cutoffConnectedPaths){
					newSplitPath.addNeighbor(currentMidEndingPath.removeNeighbors(path.getNeighbors()));
				}
				newPathNode.addNeighbor(currentMidEndingPath);
				newPathNode.addNeighbor(newSplitPath);
				newSplitPath.addNeighbor(newPathNode);
			}
		}
		else{
			if(newPath.size() > 0){
				PathTree newTree = createTree(treeID);
				PathNode path = new PathNode(null, newPath, "TEMP");
				newTree.setRoot(path);
				myForest.addTree(newTree);
			}
		}
	}

	// Adds enemy to tree on correct path
	public void addEnemy(Enemy enemy, String pathID){
		myForest.getPathByID(pathID).addEnemy(enemy);
	}

	// Adds terrain to tree on correct path
	public void addTerrain(Terrain terrain, String pathID){
		myForest.getPathByID(pathID).addTerrain(terrain);
	}

	public PathForest getForest(){
		return myForest;
	}

}