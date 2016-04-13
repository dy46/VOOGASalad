package auth_environment.paths;

import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.properties.Position;

public class PathsFactory {

	private PathGraphForest myForest;

	public PathsFactory(){
		this.myForest = new PathGraphForest();
	}

	public PathGraph createGraph(String ID){
		return new PathGraph(ID);
	}

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts path with newPath positions into Graph.
	 * Checks GraphID for valid Graph. If Graph not found, creates new Graph and creates path from newPath positions.
	 * If Graph is found, checks starting and ending positions of newPath.
	 * Initializes newPathNode as new path for creation.
	 * If existing path found with edge position equal to starting or ending position of newPath, adds existing path's children to newPathNode
	 * If existing path found with middle position equal to starting or ending position of newPath, splits path.
	 * In case of split path, creates new path with newPath coordinates AND splits existing path into two.
	 * Two split pieces of new path get neighbors created corresponding to new neighbors that previously were neighbors of unsplit path.
	 * First half of unsplit path stays as same path node. Second half created as new path node.
	 */
	public void insertPath(List<Position> newPath, String GraphID){
		PathGraph myGraph = myForest.getGraphByID(GraphID);
		if(myGraph != null && newPath.size() > 0){
			Position startingPos = newPath.get(0);
			Position endingPos = newPath.get(newPath.size()-1);
			List<PathNode> currentStartingPaths = myGraph.getPathByEdgePosition(startingPos);
			List<PathNode> currentEndingPaths = myGraph.getPathByEdgePosition(endingPos);
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
			PathNode currentMidStartingPath = myGraph.getPathByMidPosition(startingPos);
			PathNode currentMidEndingPath = myGraph.getPathByMidPosition(endingPos);
			if(currentMidStartingPath != null){
				List<Position> cutoffPositions = currentMidStartingPath.cutoffByPosition(startingPos);
				Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
				List<PathNode> cutoffConnectedPaths = myGraph.getPathByEdgePosition(lastCutoff);
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
				List<PathNode> cutoffConnectedPaths = myGraph.getPathByEdgePosition(lastCutoff);
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
				PathGraph newGraph = createGraph(GraphID);
				PathNode path = new PathNode(null, newPath, "TEMP");
				newGraph.setRoot(path);
				myForest.addGraph(newGraph);
			}
		}
	}

	// Adds enemy to Graph on correct path
	public void addEnemy(Enemy enemy, String pathID){
		myForest.getPathByID(pathID).addEnemy(enemy);
	}

	// Adds terrain to Graph on correct path
	public void addTerrain(Terrain terrain, String pathID){
		myForest.getPathByID(pathID).addTerrain(terrain);
	}

	public PathGraphForest getForest(){
		return myForest;
	}

}