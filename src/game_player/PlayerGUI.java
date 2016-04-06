package game_player;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PlayerGUI {
	private static final double TABS_OFFSET = 5.0;
	private static final double NEWTAB_OFFSET = 40.0;
	private int windowWidth;
	private int windowHeight;
	private AnchorPane myRoot;
	private TabPane myTabs;
	
	public PlayerGUI(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	public Scene createPlayerScene() {
		myRoot = new AnchorPane();
		myTabs = new TabPane();
		
		//TODO: Create resource file for UI text.
		Button newTabButton = new Button("Create New Tab");
		newTabButton.setOnAction(event -> createNewTab());
		
		myTabs.getTabs().add(new PlayerMainTab().getTab());
		
		AnchorPane.setTopAnchor(myTabs, TABS_OFFSET);
		AnchorPane.setTopAnchor(newTabButton, NEWTAB_OFFSET);
		
		myRoot.getChildren().addAll(myTabs, newTabButton);
		
		Scene myScene = new Scene(myRoot, windowWidth, windowHeight);
		
		return myScene;
	}
	
	private void createNewTab() {
		Tab tab = new PlayerMainTab().getTab();
        myTabs.getTabs().add(tab);
        myTabs.getSelectionModel().select(tab);
	}
}
