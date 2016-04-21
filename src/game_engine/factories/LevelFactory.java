package game_engine.factories;

import game_engine.game_elements.Level;
import game_engine.game_elements.Wave;
import game_engine.libraries.LevelLibrary;

public class LevelFactory {

	private LevelLibrary myLevelLibrary;

	public LevelFactory(){
		myLevelLibrary = new LevelLibrary();
	}
	
	public LevelLibrary getLevelLibrary(){
		return myLevelLibrary;
	}
	
	public void addWave(Level l, Wave w){
		
	}
	
}