package auth_environment.paths;

import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.properties.Position;

public class PathGraphFactory {

	private PathGraph myForest;
	private int currentGraphID;
	private int currentPathID;

	public PathGraphFactory(){
		this.myForest = new PathGraph();
	}

	public PathNode createGraph(){
		return new PathNode(currentGraphID++);
	}

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts path with newPath positions into forest of path graphs
	 */
	public void insertPath(List<Position> newPath){
		PathNode myGraph = myForest.getGraphByPos(newPath.get(0));
		if(myGraph != null){
			Branch newPathNode = new Branch(currentPathID++);
			newPathNode.addPositions(newPath);
			configure(newPathNode, myGraph);
		}
		else{
			if(newPath.size() > 0){
				PathNode newGraph = createGraph();
				Branch path = new Branch(newPath, currentPathID++);
				newGraph.setRoot(path);
				myForest.addGraph(newGraph);
			}
		}
	}

	public void configure(Branch newPathNode, PathNode myGraph){
		List<Position> positions = newPathNode.getPositions();
		Position startingPos = positions.get(0);
		Position endingPos = positions.get(positions.size()-1);
		List<Branch> currentStartingPaths = myGraph.getPathByEdgePosition(startingPos);
		List<Branch> currentEndingPaths = myGraph.getPathByEdgePosition(endingPos);
		for(Branch path : currentStartingPaths){
			List<Branch> pathNeighbors = path.getNeighbors();
			for(Branch p : pathNeighbors){
				p.addNeighbor(newPathNode);
			}
			newPathNode.addNeighbors(pathNeighbors);
		}
		for(Branch path : currentEndingPaths){
			List<Branch> pathChildren = path.getNeighbors();
			for(Branch p : pathChildren){
				p.addNeighbor(newPathNode);
			}
			newPathNode.addNeighbors(pathChildren);
		}
		Branch currentMidStartingPath = myGraph.getPathByMidPosition(startingPos);
		Branch currentMidEndingPath = myGraph.getPathByMidPosition(endingPos);
		List<Branch> edgePaths = Arrays.asList(currentMidStartingPath, currentMidEndingPath);
		for(Branch edgePath : edgePaths){
			if(edgePath != null){
				List<Position> cutoffPositions = edgePath.cutoffByPosition(startingPos);
				Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
				List<Branch> cutoffConnectedPaths = myGraph.getPathByEdgePosition(lastCutoff);
				Branch newSplitPath = new Branch(currentPathID++);
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

	public PathGraph getForest(){
		return myForest;
	}

}