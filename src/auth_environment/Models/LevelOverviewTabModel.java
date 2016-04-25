package auth_environment.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import game_engine.game_elements.Level;
import game_engine.game_elements.Wave;

public class LevelOverviewTabModel implements ILevelOverviewTabModel{
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
    private Map<String, Wave> myCreatedWaves;
    private List<Level> myCreatedLevels;
    private int myCurrentLevelIndex;
    
    public LevelOverviewTabModel(IAuthEnvironment auth){
        this.myCreatedLevels = auth.getLevels();
        this.myCreatedWaves = new HashMap<String, Wave>(); // ex. Level1Wave1
        this.myCreatedLevels.stream().forEach(level -> this.addWave(level));
        this.myCurrentLevelIndex = 0;
    }
    
    private void addWave(Level level) {
    	String levelNum = Integer.toString(this.myCreatedLevels.indexOf(level)); 
    	for (int i=0; i<level.getWaves().size(); i++) {
    		String label = this.myNamesBundle.getString("levelPrefix") + levelNum + 
    				this.myNamesBundle.getString("wavePrefix") + Integer.toString(i); 
    		this.myCreatedWaves.put(label, level.getWaves().get(i));
    	}
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