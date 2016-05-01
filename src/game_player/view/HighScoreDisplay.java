package game_player.view;

import game_player.GameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;


public class HighScoreDisplay implements IGUIObject {

    private static final String HIGH_SCORE = "High Score";
    private ResourceBundle myResources;
    private Label highScoreLabel;
    private GameDataSource myGameData;
    private IGameView myView;
    public HighScoreDisplay (ResourceBundle r, GameDataSource gameData, IGameView view, PlayerGUI GUI) {
        myResources = r;
        myGameData = gameData;
        myView = view;
    }

    @Override
    public Node createNode () {
        myView.getGameEngine();
        highScoreLabel = new Label();
        updateText();
        return highScoreLabel;
    }

    @Override
    public void updateNode () {
        updateText();
    }

    private void updateText () {
        highScoreLabel.setText(myResources.getString("HighScore") +
                               myGameData.getDoubleValue(HIGH_SCORE));
    }

}
