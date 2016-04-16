package game_player.view;

import game_player.GameDataSource;
import java.util.ResourceBundle;

import game_engine.games.IPlayerEngineInterface;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HighScoreDisplay implements IGUIObject {
	
	private static final int VBOX_PADDING = 10;
	private static final String HIGH_SCORE = "High Score";
	private ResourceBundle myResources;
	private Label highScoreLabel;
	private GameDataSource myGameData;
	private IGameView myView;
	private IPlayerEngineInterface myEngine;
	
	public HighScoreDisplay(ResourceBundle r, GameDataSource gameData, IGameView view) {
		myResources = r;
		myGameData = gameData;
		myView = view;
	}

	@Override
	public Node createNode() {
		myEngine = myView.getGameEngine();
		highScoreLabel = new Label();
		highScoreLabel.setFont(new Font("Arial", 20));
		updateText();
		return highScoreLabel;
	}

	@Override
	public void updateNode() {
		updateText();
	}
	
	private void updateText() {
		highScoreLabel.setText(myResources.getString("HighScore") 
				+ myEngine.getGameStatus());
	}

}
