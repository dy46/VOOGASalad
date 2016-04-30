package game_player.preferences;

import java.util.ResourceBundle;

import game_player.GameDataSource;
import game_player.interfaces.IGameView;
import game_player.view.GUIComboBox;
import javafx.collections.ObservableList;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class PreferencesMusic extends GUIComboBox{
	private ResourceBundle myResources;
	private GameDataSource myGameData;
	private IGameView myView;
	private Scene myScene;
	
	public PreferencesMusic(ResourceBundle r, GameDataSource gameData, IGameView view) {
		super(r, gameData, view, r.getString("MusicLabel"));
        myResources = r;
        myGameData = gameData;
        myView = view;
        myScene = view.getScene();
    }
	
	public ObservableList<String> populateOptions(ObservableList<String> list) {
		String[] musicChoices = myResources.getString("MusicOptions").trim().split(",");
		for (String s: musicChoices) {
			list.add(s);
		}
		return list;
	}
	
	public void performAction() {
		myView.setMusic(super.getComboBox().getValue());
	}
}
