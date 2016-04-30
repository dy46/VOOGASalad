package game_player.preferences;

import java.util.ResourceBundle;

import game_player.GameDataSource;
import game_player.interfaces.IGameView;
import game_player.view.GUIComboBox;
import javafx.collections.ObservableList;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import game_player.view.PlayerGUI;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class PreferencesMusic extends GUIComboBox{
	private ResourceBundle myResources;
	private GameDataSource myGameData;
	private IGameView myView;
	private Scene myScene;
	
	public PreferencesMusic(ResourceBundle r, GameDataSource gameData, IGameView view, PlayerGUI GUI) {
    	super(r, gameData, view, r.getString("MusicLabel"), r.getString("MusicOptions"), GUI);
        myResources = r;
        myGameData = gameData;
        myView = view;
        myScene = view.getScene();
    }
	
	public void performAction() {
		myView.setMusic(super.getComboBox().getValue());
	}
}
