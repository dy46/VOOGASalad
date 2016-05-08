// This entire file is part of my masterpiece.
// David Yang

/**
 * I added this class to give a short example of how the interface is 
 * used to create new front end elements.
 */

package game_player.view;

import game_player.GameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import java.util.ResourceBundle;
import game_engine.GameEngineInterface;
import javafx.scene.Node;
import javafx.scene.control.Label;


public class GameStatusDisplay implements IGUIObject {
	
    private ResourceBundle myResources;
    private Label statusLabel;
	private IGameView myView;
    private GameEngineInterface myEngine;

    public GameStatusDisplay (ResourceBundle r, GameDataSource gameData, IGameView view, PlayerGUI GUI) {
        myResources = r;
        myView = view;
    }

    @Override
    public Node createNode () {
        myEngine = myView.getGameEngine();
        statusLabel = new Label();
        updateNode();
        return statusLabel;
    }

    @Override
    public void updateNode () {
        updateText();
    }

    private void updateText () {
        statusLabel.setText(myResources.getString("GameStatus") + myEngine.getLevelDisplay().getGameStatus());
    }

}
