package game_player.view;

import game_engine.IPlayerEngineInterface;

/**
 * This interface specifies the different methods needed to run and animate the game elements.
 * Throws exception when towers/units are placed in wrong locations.
 * @author ownzandy
 *
 */

public interface IGameView {
    
    //event handler that begins the animation and movement of elements
    void playGame(int gameIndex);
    
    void restartGame();
    
    //event handler that toggles the game
    void toggleGame();
    
    void changeColorScheme (int colorIndex);
      
    void changeGameSpeed (double gameSpeed); 
    
    IPlayerEngineInterface getGameEngine();
}
