package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import game_engine.affectors.Affector;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Path;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class PathGraph {

	private PathNode myRoot;
	private int myID;
	
	public PathGraph(int graphID){
		this.myID = graphID;
	}
	
	public PathGraph(PathNode root, int ID){
		this.myID = ID;
		this.myRoot = root;
	}
	
	public void setRoot(PathNode root){
		this.myRoot = root;
	}
	
	public PathNode getRoot(){
		return myRoot;
	}
	
	public List<Unit> getEnemies(){
		return getUnits(myRoot, new Enemy("0", new ArrayList<Affector>(), 0));
	}
	
	public List<Unit> getPaths(){
		return getUnits(myRoot, new Path("0"));
	}
	
	public List<Unit> getTerrains(){
		return getUnits(myRoot, new Terrain("0", 0));
	}
	
	public List<PathNode> getPathNodes(PathNode root){
		List<PathNode> nodes = root.getNeighbors().stream().filter(n -> getPathNodes(n) != null).collect(Collectors.toList());
		nodes.add(root);
		return nodes;
	}
	
	// Uses post-order traversal to extract List of appropriate Unit
	private List<Unit> getUnits(PathNode node, Unit target){
		List<Unit> myUnits = new ArrayList<>();
		for(PathNode n : node.getNeighbors()){
			myUnits.addAll(n.getUnitsByType(target));
			myUnits.addAll(getUnits(n, target));
		}
		return myUnits;
	}
	
	public int getID(){
		return myID;
	}
	
	public List<PathNode> getPathByEdgePosition(Position pos){
		return myRoot.getNeighbors().stream().filter(
				n -> n.getPositions().get(0).equals(pos) || n.getPositions().get(n.getPositions().size()-1).equals(pos))
				.collect(Collectors.toList());
	}
	
	public PathNode getPathByMidPosition(Position pos){
		Optional<PathNode> graph = myRoot.getNeighbors().stream().filter(
				n-> n.getPositions().contains(pos) && !n.getPositions().get(0).equals(pos) && !n.getPositions().get(n.getPositions().size()-1).equals(pos))
				.findFirst();
		return graph.isPresent() ? graph.get() : null;
	}
	
}