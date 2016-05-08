// This entire file is part of my masterpiece.
// Cody Li 
// This class creates the Level tab in the authoring environment and simply groups together all of the levels within a game. 
// I believe this class is well designed because it is easy to read as someone who has no prior knowledge of this class is able to
// determine what exactly is happening. I am able to accomplish this by making sure that each method is well named and has only one responsibility.
// For example, it should be obvious that in the init() method that all it is doing is initializing class variables and that it is calling set-up methods
// that establishes different aspects of this class, such as setting up the second row of tabs that displays all of the levels in the game (setUpLevelTabs()).
// Additionally, this class primarily uses lambda expressions and Java's Stream API for efficient code writing since they are powerful tools for 
// condensing code without compromising readability. le
package auth_environment.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import java.util.ResourceBundle;
import auth_environment.Models.LevelOverviewTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import game_engine.game_elements.Level;
import javafx.scene.layout.BorderPane;

public class LevelOverviewTab extends Tab {
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	private BorderPane myRoot;
	private TabPane myTabs;
	private IAuthModel myAuthModel;
	private ILevelOverviewTabModel myLevelOverviewTabModel;
	
	public LevelOverviewTab(String name, IAuthModel authModel){
		super(name);
		this.myAuthModel = authModel;
		init();
	}
	
	private void init() {
		this.myRoot = new BorderPane();
		this.myTabs = new TabPane();
		this.myLevelOverviewTabModel = new LevelOverviewTabModel(this.myAuthModel.getIAuthEnvironment());
		this.setUpLevelTabs();
		this.setupBorderPane();
		this.setContent(myRoot);
	}
	
	private void setupBorderPane() {
		myRoot.setRight(this.buildNewLevelButton());
		myRoot.setLeft(myTabs);
	}
	
	private void setUpLevelTabs() {
		this.myTabs.getTabs().clear();
		if (this.myLevelOverviewTabModel.getCreatedLevels().size()>0) {
			this.myLevelOverviewTabModel.getCreatedLevels().stream().forEach(level -> this.addLevelTab(level));
		}
		else {
			this.addLevelTab();
		}
	}
	
	private Tab addLevelTab() {
		int newLevelIndex = this.myTabs.getTabs().size() + 1; 
		String newLevelName = "Level " + newLevelIndex;
		myLevelOverviewTabModel.addLevel(newLevelName, Integer.parseInt(myNamesBundle.getString("levelDefaultLife")));
		Tab tab = new LevelTab(newLevelName, 
				newLevelIndex, 
				myAuthModel, 
				this.myLevelOverviewTabModel);
		myTabs.getTabs().addAll(tab);
		return tab;
	}
	
	private void addLevelTab(Level level) {
		Tab tab = new LevelTab("Level " + (this.myLevelOverviewTabModel.getCreatedLevels().indexOf(level)),
				this.myLevelOverviewTabModel.getCreatedLevels().indexOf(level),
				myAuthModel, 
				this.myLevelOverviewTabModel);
		myTabs.getTabs().add(tab);
	}
	
	private Node buildNewLevelButton() {
		Button addNewLevelButton = new Button(this.myNamesBundle.getString("levelItemLabel"));
		addNewLevelButton.setOnAction(e -> {
			myTabs.getSelectionModel().select(this.addLevelTab());
		});
		return addNewLevelButton;
	}
	
	public Node getRoot(){
		return this.myRoot;
	}
}