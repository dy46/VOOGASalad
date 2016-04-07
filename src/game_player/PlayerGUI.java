package game_player;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PlayerGUI{
	private static final double TABS_OFFSET = 32.5;
	private static final double NEWTAB_OFFSET = 2.5;
	private static final String GUI_RESOURCE = "GUI";
	private int windowWidth;
	private int windowHeight;
	private AnchorPane myRoot;
	private TabPane myTabs;
	private ResourceBundle myResources;
	
	public PlayerGUI(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
	}
	
	public Scene createPlayerScene() {
		myRoot = new AnchorPane();
		myTabs = new TabPane();
		
		//TODO: Create resource file for UI text.
		Button newTabButton = new Button(myResources.getString("NewTabText"));
		newTabButton.setOnAction(event -> createNewTab());
		
		myTabs.getTabs().add(new PlayerMainTab(myResources).getTab());
		
		AnchorPane.setTopAnchor(myTabs, TABS_OFFSET);
		AnchorPane.setTopAnchor(newTabButton, NEWTAB_OFFSET);
		
		myRoot.getChildren().addAll(myTabs, newTabButton);
		
		Scene myScene = new Scene(myRoot, windowWidth, windowHeight);
		
		return myScene;
	}
	
	private void createNewTab() {
		Tab tab = new PlayerMainTab(myResources).getTab();
        myTabs.getTabs().add(tab);
        myTabs.getSelectionModel().select(tab);
	}
}
