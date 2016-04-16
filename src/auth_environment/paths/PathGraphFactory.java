package auth_environment.paths;

import java.util.Arrays;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PathGraphFactory {

	private PathGraph myGraph;
	private int currentGraphID;
	private int currentPathID;

	public PathGraphFactory(){
		this.myGraph = new PathGraph();
	}

	public Path createGraph(){
		return new Path(currentGraphID++);
	}

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts path with newPath positions into forest of path graphs
	 */
	public void insertPath(List<Position> newPath){
		if(newPath.size() == 0)
			return;
		Path myPath = myGraph.getGraphByPos(newPath.get(0));
		if(myPath != null){
			Branch newPathNode = new Branch(currentPathID++);
			newPathNode.addPositions(newPath);
			configure(newPathNode, myPath);
		}
		else{
			if(newPath.size() > 0){
				Path splitPath = createGraph();
				Branch branch = new Branch(newPath, currentPathID++);
				splitPath.addBranch(branch);
				myGraph.addPath(splitPath);
			}
		}
	}

	public void configure(Branch newPathNode, Path myGraph){
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
		return myGraph;
	}
	
	public List<Branch> getPaths(){
		System.out.println(myGraph.getPaths());
		return myGraph.getPaths();
	}

}