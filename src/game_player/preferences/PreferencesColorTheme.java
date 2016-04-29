package game_player.preferences;

import java.util.ResourceBundle;
import game_player.GameDataSource;
import game_player.interfaces.IGameView;
import game_player.view.GUIComboBox;
import javafx.collections.ObservableList;


public class PreferencesColorTheme extends GUIComboBox {
	
    private ResourceBundle myResources;

    public PreferencesColorTheme (ResourceBundle r, GameDataSource gameData, IGameView view) {
    	super(r, gameData, view, r.getString("ColorThemeLabel"));
        myResources = r;
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
        // change color theme
    }

    @Override
    public void updateNode () {
        // TODO Auto-generated method stub

    }

}
