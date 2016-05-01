package game_player.view;

import game_player.GameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import java.util.ResourceBundle;
import game_engine.GameEngineInterface;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class GameStatusDisplay implements IGUIObject {
	
    private ResourceBundle myResources;
    private Label statusLabel;
    private GameDataSource myGameData;
    private IGameView myView;
    private GameEngineInterface myEngine;

    public GameStatusDisplay (ResourceBundle r, GameDataSource gameData, IGameView view, PlayerGUI GUI) {
        myResources = r;
        myGameData = gameData;
        myView = view;
    }

    @Override
    public Node createNode () {
        myEngine = myView.getGameEngine();
        statusLabel = new Label();
        statusLabel.setFont(new Font("Arial", 20));
        updateText();
        return statusLabel;
    }

    @Override
    public void updateNode () {
        updateText();
    }

    private void updateText () {
        statusLabel.setText(myResources.getString("GameStatus") + myEngine.getLevelDisplay().getGameStatus() + " " +
                            myEngine.getLevelController().getScore());
    }

}
