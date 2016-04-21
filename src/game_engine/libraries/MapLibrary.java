package game_engine.libraries;

import java.util.ArrayList;
import java.util.List;

import auth_environment.paths.PathGraph;
import game_engine.properties.Position;

public class MapLibrary {

	private PathGraph myPathGraph;
	private List<Position> mySpawns;
	private List<Position> myGoals;
	
	public MapLibrary(){
		this.myPathGraph = new PathGraph();
		mySpawns = new ArrayList<>();
		myGoals = new ArrayList<>();
	}
	
	public PathGraph getPathGraph(){
		return myPathGraph;
	}
	
	public List<Position> getSpawns(){
		return mySpawns;
	}
	
	public List<Position> getGoals(){
		return myGoals;
	}
	
	public void addSpawn(Position spawn){
		this.mySpawns.add(spawn);
	}
	
	public void addGoal(Position goal){
		this.myGoals.add(goal);
	}
	
	public void addSpawns(List<Position> spawns){
		this.mySpawns.addAll(spawns);
	}
	
	public void addGoals(List<Position> goals){
		this.mySpawns.addAll(goals);
	}
	
	public void removeGoal(Position goal){
		this.myGoals.remove(goal);
	}
	
	public void removeSpawn(Position spawn){
		this.mySpawns.remove(spawn);
	}
	
}