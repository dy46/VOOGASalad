package auth_environment.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import game_engine.game_elements.Level;
import game_engine.game_elements.Wave;

public class LevelOverviewTabModel implements ILevelOverviewTabModel{
    private Map<String, Wave> myCreatedWaves;
    private List<Level> myCreatedLevels;
    private int myCurrentLevelIndex;
    
    public LevelOverviewTabModel(){
        myCreatedLevels = new ArrayList<Level>();
        myCreatedWaves = new HashMap<String, Wave>();
        myCurrentLevelIndex = 0;
    }
    
    private boolean checkBounds(int levelNum){
        return (levelNum >= 0) && (levelNum < this.myCreatedLevels.size());
    }
    
    @Override
    public void changeEditedLevel(int editLevel){
        if(!this.checkBounds(editLevel-1)){
            // TODO: throw an exception for the front-end
        }
        this.myCurrentLevelIndex = editLevel-1;
    }
    
    @Override
    public void addLevel(String name, int numLives){
        this.myCreatedLevels.add(new Level(name, numLives));
    }
    
    @Override
    public void addLevels(String name, int numLives, int numLevelsToAdd){
        for(int i = 1;i <= numLevelsToAdd;i++){
            this.addLevel(name + i, numLives);
        }
    }
    
    @Override
    public void addWaveToCurrentLevel(int waveIndex){
        Wave w = this.myCreatedWaves.get(waveIndex);
        this.myCreatedLevels.get(this.myCurrentLevelIndex).addWave(w);
    }
    
    @Override
    public List<String> getLevelNames(){
        List<String> levelNames = new ArrayList<String>();
        this.myCreatedLevels.forEach(l -> levelNames.add(l.getName()));
        return levelNames;
    }
    
    @Override
    public List<String> getCurrentLevelWaveNames(){
        List<String> names = new ArrayList<String>();
        this.myCreatedLevels.get(this.myCurrentLevelIndex).getWaves().forEach(w -> names.add(w.getName()));
        return names;
    }
    
    @Override
    public List<Level> getCreatedLevels(){
        return new ArrayList<Level>(this.myCreatedLevels);
    }
    
    public void addToCreatedWaves(String level, Wave wave) {
        this.myCreatedWaves.put(level, wave);
    }
}