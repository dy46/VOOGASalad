package game_player.preferences;

import java.util.ResourceBundle;
import game_player.GameDataSource;
import game_player.interfaces.IGameView;
import game_player.view.GUIComboBox;
import game_player.view.PlayerGUI;
import javafx.collections.ObservableList;


public class PreferencesColorTheme extends GUIComboBox {
	
    private ResourceBundle myResources;
    private IGameView myView;

    public PreferencesColorTheme (ResourceBundle r, GameDataSource gameData, IGameView view, PlayerGUI GUI) {
    	super(r, gameData, view, r.getString("ColorThemeLabel"), GUI);
        myResources = r;
        myView = view;
    }

	@Override
	public ObservableList<String> populateOptions(ObservableList<String> list) {
        String[] themeOptions = myResources.getString("ColorThemeOptions").split(",");
        for (String s : themeOptions) {
            list.add(s);
        }
		return list;
	}
	
    public void performAction () {
        myView.setCSS(super.getComboBox().getValue());
    }

    @Override
    public void updateNode () {
        // TODO Auto-generated method stub

    }

}
