package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.backend.GameDataController;
import auth_environment.backend.ISelector;
import auth_environment.backend.SelectorModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.Menus.MenuToolBar;
import game_data.GameData;
import game_engine.game_elements.Tower;

import auth_environment.backend.ISettings;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class represents a single tab (ie Level) within our View.
 */

public class Workspace {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	private MapDisplay myDisplay = new MapDisplay();
	private ElementPicker myPicker;

	private ISettings mySettings;

	private GameData gameData;
	private GameDataController myGameDataController;

	public Workspace(TabPane tabPane, ISettings settings) {
		this.myTabPane = tabPane; 
		this.mySettings = settings;
		this.gameData = new GameData();
		myPicker = new ElementPicker();
		this.myGameDataController = new GameDataController(gameData, myPicker, myDisplay.getGrid().getPathGraphFactory());
		this.setupBorderPane();
	}

	private void setupBorderPane() {


		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setTop(new MenuToolBar(this.myTabPane, this.myPicker, this.mySettings, myGameDataController));
		//		this.myBorderPane.setLeft(hello);
		myPicker.setPrefSize(400,400);
		this.myBorderPane.setRight(myPicker);
		this.myBorderPane.setCenter(myDisplay);
	}

	//	public void writeToGameData() {
	////		gameData.setLevels(myPicker.getLevels());
	//		gameData.setEnemies(myPicker.getEnemies());
	//		gameData.setTerrains(myPicker.getTerrains());
	//		gameData.setTowerTypes(myPicker.getTowers());
	//		gameData.setPaths(myDisplay.getGrid().getPathGraphFactory().getPaths());
	//	}

	public Node getRoot() {
		return this.myBorderPane; 
	}
}