package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;

import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import game_engine.game_elements.Level;
import game_engine.game_elements.Wave;

public class LevelOverviewTabModel implements ILevelOverviewTabModel{
	private List<Wave> myCreatedWaves;
	private List<Level> myCreatedLevels;
	private int myCurrentLevelIndex;
	
	public LevelOverviewTabModel(){
		myCreatedLevels = new ArrayList<Level>();
		myCreatedWaves = new ArrayList<Wave>();
		myCurrentLevelIndex = 0;
	}
	
	private boolean checkBounds(int levelNum){
		return (levelNum >= 0) && (levelNum < this.myCreatedLevels.size());
	}
	
	public void changeEditedLevel(int editLevel){
		if(!this.checkBounds(editLevel-1)){
			// TODO: throw an exception for the front-end
		}
		this.myCurrentLevelIndex = editLevel-1;
	}
	
	public void addLevel(String name, int numLives){
		this.myCreatedLevels.add(new Level(name, numLives));
	}
	
	public void addLevels(String name, int numLives, int numLevelsToAdd){
		for(int i = 1;i <= numLevelsToAdd;i++){
			this.addLevel(name + i, numLives);
		}
	}
	
	public void addWaveToCurrentLevel(int waveIndex){
		Wave w = this.myCreatedWaves.get(waveIndex);
		this.myCreatedLevels.get(this.myCurrentLevelIndex).addWave(w);
	}
	
	
}
