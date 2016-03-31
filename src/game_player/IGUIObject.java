package game_player;

import javafx.scene.Node;

public interface IGUIObject {
    
    //creates GUI object 
    Node createNode();
    
    //updates GUI object
    void updateNode();
}
