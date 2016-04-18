package auth_environment.view.Workspaces;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.backend.GameDataController;
import auth_environment.backend.ISelector;
import auth_environment.backend.SelectorModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.ElementPicker;
import auth_environment.view.MapDisplay;
import auth_environment.view.Menus.MenuToolBar;
import game_data.GameData;
import game_engine.game_elements.Tower;

import auth_environment.backend.ISettings;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class represents a single tab (ie Level) within our View.
 */

public class GlobalGameTab implements IWorkspace {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private BorderPane myBorderPane = new BorderPane(); 

	// TODO: replace with EngineWorkspace class
	private GameData gameData; // This originates here via XML 
//	private GameDataController myGameDataController; // TODO: find this
	private NodeFactory myNodeFactory = new NodeFactory(); 

	public GlobalGameTab() {
		this.gameData = new GameData();
//		myPicker = new ElementPicker();
//		this.myGameDataController = new GameDataController(gameData, myPicker, myDisplay.getGrid().getPathGraphFactory());
		this.setupBorderPane();
	}

	private void setupBorderPane() {
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		
		
		
//		this.myBorderPane.setTop(new MenuToolBar(this.myTabPane, this.myPicker, this.gameData.getSettings(), myGameDataController));
//		myPicker.setPrefSize(Integer.parseInt(myDimensionsBundle.getString("defaultPickerWidth")),
//				Integer.parseInt(myDimensionsBundle.getString("defaultPickerHeight"))); 
//		this.myBorderPane.setRight(myPicker);
//		this.myBorderPane.setCenter(myDisplay);
	}
	
//	private TextField buildTextInput() {
//		this.gameNameInput = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("gameNamePrompt"));
//		this.gameNameInput.setOnAction(e -> this.submitButtonPressed());
//		return this.gameNameInput; 
//	}

	//	public void writeToGameData() {
	////		gameData.setLevels(myPicker.getLevels());
	//		gameData.setEnemies(myPicker.getEnemies());
	//		gameData.setTerrains(myPicker.getTerrains());
	//		gameData.setTowerTypes(myPicker.getTowers());
	//		gameData.setPaths(myDisplay.getGrid().getPathGraphFactory().getPaths());
	//	}

	@Override
	public Node getRoot() {
		return this.myBorderPane; 
	}
}