package game_player;

import java.util.List;

/**
 * This class holds user-specific information such as high scores, game-speed, color themes, etc.
 * as well as types of games
 * @author ownzandy
 *
 */

public interface IGameDataSource {
    
    //sets double values 
    void setDoubleValue(String key, double value);
    
    //get double values 
    List<Double> getDoubleValue(String key);
     
    //sets string values 
    void setStringValue(String key, List<String> values);
    
    //gets string values 
    List<String> getStringValue(String key);
}
