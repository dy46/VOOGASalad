package game_engine.factories;

import java.util.List;

import game_engine.libraries.SpawnLibrary;
import game_engine.properties.Position;

public class SpawnFactory {

	public SpawnLibrary mySpawnLibrary;
	
	public SpawnFactory(){
		mySpawnLibrary = new SpawnLibrary();
	}
	
	public SpawnFactory(SpawnLibrary spawnLibrary){
		this.mySpawnLibrary = spawnLibrary;
	}
	
	public void createSpawn(Position spawn){
		this.mySpawnLibrary.addSpawn(spawn);
	}
	
	public void createSpawns(List<Position> spawns){
		this.mySpawnLibrary.addSpawns(spawns);
	}
	
	public void setSpawns(List<Position> spawns){
		this.mySpawnLibrary.setSpawns(spawns);
	}
	
	public void removeSpawn(Position spawn){
		this.mySpawnLibrary.removeSpawn(spawn);
	}
	
	public SpawnLibrary getSpawnLibrary(){
		return mySpawnLibrary;
	}
	
}