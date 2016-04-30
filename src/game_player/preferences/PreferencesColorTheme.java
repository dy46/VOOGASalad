package game_player.preferences;

import java.util.ResourceBundle;
import game_player.GameDataSource;
import game_player.interfaces.IGameView;
import game_player.view.GUIComboBox;
import javafx.collections.ObservableList;


public class PreferencesColorTheme extends GUIComboBox {
	
    private ResourceBundle myResources;
    private IGameView myView;

    public PreferencesColorTheme (ResourceBundle r, GameDataSource gameData, IGameView view) {
    	super(r, gameData, view, r.getString("ColorThemeLabel"), r.getString("ColorThemeOptions"));
        myResources = r;
        myView = view;
    }
	
    public void performAction () {
        myView.setCSS(super.getComboBox().getValue());
    }

    @Override
    public void updateNode () {
        // TODO Auto-generated method stub

    }

}
