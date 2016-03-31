package game_player;

/**
 * This interface allows the user to change game functionalities 
 * such as switching, replaying, and saving, etc.
 * @author ownzandy
 *
 */
public interface IGamePreferences {
 
    //switches and starts new game
    void switchGame(int gameIndex);
    
    //restarts current game
    void restartGame();
    
    //saves current game state to be played later
    void saveGame();
}
