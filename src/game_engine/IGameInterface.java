package game_engine;

import java.util.List;

/**
 * This interface is the external API for the game player module. It facilitates 
 * user actions to the game engine. 
 * @author ownzandy
 *
 */

public interface IGameInterface {
    
    //tells engine to play a specified level
    void playLevel(int levelNumber);
    
    //tells engine to update elements based on current positions and activities
    void updateElements();
    
    //asks engine about the game status
    List<Double> getGameStatus();
    
    //tells engine to add tower to its active tower list given a tower index
    void addTower(int towerIndex);
    
    //tells engine to modify tower given an activeTower index and list of changes
    void modifyTower(int activeTowerIndex, List<Double> changes);
    
    //sets up the engine with a list of 
    void setUpEngine(List<String> fileNames);
    
}
