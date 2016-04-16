package game_engine.games;

import java.util.List;

import game_data.GameData;
import game_engine.game_elements.Level;
import game_engine.CollisionDetector;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.genres.TD.TDGame;
import game_engine.properties.UnitProperties;
import game_engine.games.Timer;

/**
 * This interface is the external API for the game player module. It facilitates 
 * user actions to the game engine. Throws exception when wrong files are passed
 * to setUpEngine or towers cannot be found/modified. Throws exception when games 
 * cannot be saved/loaded correctly.
 * @author ownzandy
 *
 */

public interface GameEngineInterface {
    
    //tells the engine to save the game and returns the list of fileNames that can be retrieved
    List<String> saveGame();
    
    //tells engine to play a specified level
    void playLevel(int levelNumber);
    
    //tells engine to play a specified wave
    void playWave(int waveNumber);
    
    //tells engine to update elements a single time unit 
    void update();
    
    //asks engine about the game status
    String getGameStatus();
    
    //tells engine to add tower to its active tower list given a tower index
    void addTower(String name, double x, double y);
    
    //tells engine to modify tower given an activeTower index and list of changes
    void modifyTower(int activeTowerIndex, UnitProperties newProperties);
    
    //sets up the engine with a list of files
    void setUpEngine(GameData gameData);
    
    public List<Unit> getEnemies();
    
    public List<Unit> getTowers();    
    
    public List<Unit> getTerrains();

	public List<Level> getLevels();
	
    public int getLives();
	
    public List<Unit> getProjectiles();

    public void clearProjectiles();
	
    public List<Tower> getTowerTypes();

	public boolean isPaused();
	
	public void setPaused();
	
	public default CollisionDetector getCollisionDetector(){
		return new CollisionDetector(this);
	}
	
	public void addEnemy(Enemy e);
	
	public boolean isGameOver();

	public Timer getTimer();

	public Level getCurrentLevel();
	
	public void decrementLives();
    
}