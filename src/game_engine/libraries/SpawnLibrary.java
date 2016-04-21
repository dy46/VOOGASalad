package game_engine.libraries;

import java.util.ArrayList;
import java.util.List;

import game_engine.properties.Position;

public class SpawnLibrary {

	private List<Position> mySpawns;
	
	public SpawnLibrary(){
		mySpawns = new ArrayList<>();
	}
	
	public SpawnLibrary(List<Position> spawns){
		mySpawns = spawns;
	}
	
	public void addSpawn(Position spawn){
		this.mySpawns.add(spawn);
	}

	public void addSpawns(List<Position> spawns){
		this.mySpawns.addAll(spawns);
	}

	public void setSpawns(List<Position> spawns){
		this.mySpawns = spawns;
	}
	
	public void removeSpawn(Position spawn) {
		this.mySpawns.remove(spawn);
	}
	
	public List<Position> getSpawns(){
		return this.mySpawns;
	}
	
}