package game_player.view;

import game_player.GameDataSource;
import java.util.ResourceBundle;

import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
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
	private Scene myScene;
	private AnchorPane myRoot;
	private TabPane myTabs;
	private ResourceBundle myResources;
	private IPlayerEngineInterface gameEngine;
	
	public PlayerGUI(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
	}
	
	public Scene createPlayerScene() {
		myRoot = new AnchorPane();
		myTabs = new TabPane();
		
		myScene = new Scene(myRoot, windowWidth, windowHeight);
		
		//TODO: Create resource file for UI text.
		Button newTabButton = new Button(myResources.getString("NewTabText"));
		newTabButton.setOnAction(event -> createNewTab());
		
		createNewTab();
		
		AnchorPane.setTopAnchor(myTabs, TABS_OFFSET);
		AnchorPane.setTopAnchor(newTabButton, NEWTAB_OFFSET);
		
		myRoot.getChildren().addAll(myTabs, newTabButton);
		
		myScene.getStylesheets().add("game_player/view/PlayerTheme1.css");
		myRoot.getStyleClass().add("background");
		
		return myScene;
	}
	
	private void createNewTab() {
		gameEngine = new EngineWorkspace();
		Tab tab = new PlayerMainTab(myResources, myScene, 
				myResources.getString("TabName") + (myTabs.getTabs().size() + 1)).getTab();
        myTabs.getTabs().add(tab);
        myTabs.getSelectionModel().select(tab);
	}
}
