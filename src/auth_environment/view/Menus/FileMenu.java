package auth_environment.view.Menus;

import java.io.File;
import java.util.ResourceBundle;

import auth_environment.backend.GameDataController;
import auth_environment.backend.ISettings;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import game_data.AuthSerializer;
import game_data.GameData;
import game_engine.IPlayerEngineInterface;
import game_engine.TestingEngineWorkspace;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by BrianLin on 4/1/16.
 * 
 * Team member responsible:
 * 
 * This drop down menu should allow the Developer to 1) Save 2) Load 
 */
public class FileMenu extends Menu {

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private FileChooserDelegate myFileChooser = new FileChooserDelegate(); 

	private ISettings mySettings; 

	private GameDataController myGameDataController;

	public FileMenu(ISettings settings, GameDataController gameDataController) {
		this.mySettings = settings; 
		this.myGameDataController = gameDataController;
		this.init();
	}

	// TODO: setup these items in a Node factory of some sort?
	private void init() {
		this.setText(myNamesBundle.getString("fileMenuLabel"));
		MenuItem saveItem = new MenuItem(myNamesBundle.getString("saveItemLabel"));
		saveItem.setOnAction(e -> this.save());
		MenuItem loadItem = new MenuItem(myNamesBundle.getString("loadItemLabel")); 
		loadItem.setOnAction(e -> this.load());
		this.getItems().addAll(saveItem, loadItem); 
	}

	// TODO: should pass ONE object to XStream. (We need to decide how to store that) 
	private void save() {
		AuthSerializer writer = new AuthSerializer();
		writeToGameData();
		writer.saveElement(myGameDataController.getGameData());
	}

	private void writeToGameData() {
		//	gameData.setLevels(myPicker.getLevels());
//		myGameDataController.getGameData().setEnemies(myGameDataController.getPicker().getEnemies());
//		myGameDataController.getGameData().setTerrains(myGameDataController.getPicker().getTerrains());
//		myGameDataController.getGameData().setTowerTypes(myGameDataController.getPicker().getTowers());
//		myGameDataController.getGameData().setPaths(myGameDataController.getPathGraphFactory().getPaths());
		TestingEngineWorkspace test = new TestingEngineWorkspace();
		myGameDataController.getGameData().setPaths(test.getPaths());
		myGameDataController.getGameData().setEnemies(test.getEnemies());
		myGameDataController.getGameData().setTerrains(test.getTerrains());
		myGameDataController.getGameData().setTowerTypes(test.getTowerTypes());
		myGameDataController.getGameData().setTowers(test.getTowers());
		myGameDataController.getGameData().setProjectiles(test.getProjectiles());
		myGameDataController.getGameData().setLevels(test.getLevels());
		myGameDataController.getGameData().setAffectors(test.getAffectors());
	}

	private void load() {
		File file = myFileChooser.chooseFile(myNamesBundle.getString("fileChooserTitle"));
		System.out.println(file.getAbsolutePath());
	}

}