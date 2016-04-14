package game_engine;

import java.util.List;
import game_engine.game_elements.Path;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.UnitProperties;

/**
 * This interface is the external API for the game player module. It facilitates 
 * user actions to the game engine. Throws exception when wrong files are passed
 * to setUpEngine or towers cannot be found/modified. Throws exception when games 
 * cannot be saved/loaded correctly.
 * @author ownzandy
 *
 */

public interface IPlayerEngineInterface {
    
    //tells the engine to save the game and returns the list of fileNames that can be retrieved
    List<String> saveGame();
    
    //tells engine to play a specified level
    void playLevel(int levelNumber);
    
    //tells engine to play a specified wave
    void playWave(int waveNumber);
    
    //tells engine to update elements a single time unit 
    void updateElements();
    
    //asks engine about the game status
    String getGameStatus();
    
    //tells engine to add tower to its active tower list given a tower index
    void addTower(String name, double x, double y);
    
    //tells engine to modify tower given an activeTower index and list of changes
    void modifyTower(int activeTowerIndex, UnitProperties newProperties);
    
    //sets up the engine with a list of files
    void setUpEngine(List<String> fileNames);
    
    public List<Unit> getEnemies();
    
    public List<Unit> getTowers();
    
    public List<Unit> getProjectiles();
    
    public List<Path> getPaths();
    
    public int getLives();

    public void clearProjectiles();
    
    public List<Unit> getTerrains();
    
    public List<Tower> getTowerTypes();
    
}