package game_engine.factories;

import java.util.List;
import game_engine.game_elements.Wave;
import game_engine.libraries.LevelLibrary;
import game_engine.properties.Position;

public class LevelFactory {

	private LevelLibrary myLevelLibrary;

	public LevelFactory(){
		myLevelLibrary = new LevelLibrary();
	}
	
	public LevelLibrary getLevelLibrary(){
		return myLevelLibrary;
	}
	
	public void addWave(String levelName, Wave wave){
		myLevelLibrary.getLevelByName(levelName).addWave(wave);
	}
	
	public void addWaves(String levelName, List<Wave> waves){
		myLevelLibrary.getLevelByName(levelName).addWaves(waves);
	}
	
	public void addSpawn(String levelName, Position spawn){
		myLevelLibrary.getLevelByName(levelName).addSpawn(spawn);
	}
	
	public void addSpawns(String levelName, List<Position> spawns){
		myLevelLibrary.getLevelByName(levelName).addSpawns(spawns);
	}
	
	public void addGoals(String levelName, List<Position> goals){
		myLevelLibrary.getLevelByName(levelName).addGoals(goals);
	}
	
	public void addGoal(String levelName, Position goal){
		myLevelLibrary.getLevelByName(levelName).addGoal(goal);
	}
	
}