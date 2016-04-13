package auth_environment.backend;

import java.util.ArrayList;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Path;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class PathTree {

	private PathNode myRoot;
	private String myID;
	
	public PathTree(String ID){
		this.myID = ID;
		this.myRoot = new PathNode();
	}
	
	public PathTree(PathNode root, String ID){
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
		List<PathNode> myNodes = new ArrayList<>();
		myNodes.add(root);
		for(PathNode n : root.getNeighbors()){
			myNodes.addAll(getPathNodes(n));
		}
		return myNodes;
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
	
	public String getID(){
		return myID;
	}
	
	public List<PathNode> getPathByEdgePosition(Position pos){
		List<PathNode> paths = new ArrayList<>();
		for(PathNode n : myRoot.getNeighbors()){
			if(n.getPositions().get(0).equals(pos) || n.getPositions().get(n.getPositions().size()-1).equals(pos)){
				paths.add(n);
			}
		}
		return paths;
	}
	
	public PathNode getPathByMidPosition(Position pos){
		for(PathNode n : myRoot.getNeighbors()){
			if(n.getPositions().contains(pos) && !n.getPositions().get(0).equals(pos) && !n.getPositions().get(n.getPositions().size()-1).equals(pos)){
				return n;
			}
		}
		return null;
	}
	
}