package game_player.view;

import java.util.ResourceBundle;

import game_engine.GameEngineInterface;
import game_engine.TestingEngineWorkspace;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PlayerGUI{
	private static final double TABS_OFFSET = 0;
	private static final double NEWTAB_OFFSET = 33;
	private static final String GUI_RESOURCE = "GUI";
	private int windowWidth;
	private int windowHeight;
	private Scene myScene;
	private AnchorPane myRoot;
	private TabPane myTabs;
	private ResourceBundle myResources;
	private GameEngineInterface gameEngine;
	
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
		AnchorPane.setRightAnchor(newTabButton, TABS_OFFSET);
		
		myRoot.getChildren().addAll(myTabs, newTabButton);
		
		return myScene;
	}
	
//	private GameEngineInterface readData() {
//		IDataConverter<GameEngineInterface> dataConverter = new AuthSerializer<GameEngineInterface>();
//		GameData gameData = (GameData) dataConverter.loadElement();
//		gameEngine = new TDGame();
//		gameEngine.setUpEngine(gameData);
//		return gameEngine;
//	}
	
	private void createNewTab() {    
	    
        gameEngine = new TestingEngineWorkspace();
		gameEngine.setUpEngine(null);
		
		
		Tab tab = new PlayerMainTab(gameEngine, myResources, myScene, 
				myResources.getString("TabName") + (myTabs.getTabs().size() + 1)).getTab();
        myTabs.getTabs().add(tab);
        myTabs.getSelectionModel().select(tab);
	}
}
