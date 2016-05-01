package game_engine.controllers;

import java.util.List;
import game_engine.game_elements.Level;
import game_engine.interfaces.ILevelDisplayer;

public class LevelController implements ILevelDisplayer {

    private List<Level> myLevels;
    private Level myCurrentLevel;
    private double score;
    private boolean paused;
    
    public LevelController(List<Level> levels, double score, int currentLevelIndex) {
        this.myLevels = levels;
        this.score = score;
        this.paused = false;
        this.myCurrentLevel = this.myLevels.get(currentLevelIndex);
    }
    
    public Level getCurrentLevel() {
        return myCurrentLevel;
    }
    
    public void setCurrentLevel(Level currentLevel) {
        this.myCurrentLevel = currentLevel;
    }
    
    public void setCurrentWave(int waveIndex) {
    	if (myCurrentLevel.getWaves().size() > waveIndex) {
    		myCurrentLevel.setCurrentWave(waveIndex);
    	}
    }
    
    public String getGameStatus () {
        if (myCurrentLevel.getMyLives() <= 0) {
            return "Waves remaining: " + myCurrentLevel.wavesLeft() + ", Lives remaining: " + "0";
        }
        return "Waves remaining: " + myCurrentLevel.wavesLeft() +
               ", Lives remaining: " + myCurrentLevel.getMyLives();
    }
    
    public int getLives () {
        return myCurrentLevel.getMyLives();
    }
    
    public void addLevel (Level level) {
        myLevels.add(level);
    }
    
    public void decrementLives (int lives) {
        myCurrentLevel.decrementLives(lives);
    }
    

    public void playLevel (int levelNumber) {
        myCurrentLevel = myLevels.get(levelNumber);
        paused = false;
    }

    public void continueWaves () {
        myCurrentLevel.playNextWave();
        paused = false;
    }
    
    public boolean isGameOver () {
        return myLevels.get(myLevels.size() - 1).wavesLeft() == 0 || myCurrentLevel.getMyLives() <= 0;
    }
    
    public List<Level> getLevels () {
        return myLevels;
    }
    
    public double getScore() {
        return score;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
}