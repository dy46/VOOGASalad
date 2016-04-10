package game_player.view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameColorTheme implements IGUIObject {
	
	private static final int VBOX_PADDING = 10;
	private ResourceBundle myResources;
	
	public GameColorTheme(ResourceBundle r) {
		myResources = r;
	}

	@Override
	public Node createNode() {
		VBox colorThemeBox = new VBox(VBOX_PADDING);
		Label themeLabel = new Label(myResources.getString("ThemeLabel"));
		ComboBox themeChoices = new ComboBox();
		return null;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}

}
