package game_engine.factories;

import game_engine.libraries.SpawnLibrary;

public class SpawnFactory {

	public SpawnLibrary mySpawnLibrary;
	
	public SpawnFactory(){
		mySpawnLibrary = new SpawnLibrary();
	}
	
}