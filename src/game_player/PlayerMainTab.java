package game_player;

import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class PlayerMainTab {
	private Tab myTab;
	private BorderPane myRoot;
	
	public PlayerMainTab() {
		
	}
	protected Tab getTab() {
		myTab = new Tab();
		myRoot = new BorderPane();
		
		myTab.setContent(myRoot);
		return myTab;
	}
}
