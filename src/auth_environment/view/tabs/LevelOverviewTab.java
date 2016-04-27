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
		this.setRefresh();
		this.setupBorderPane();
		this.setContent(myRoot);
	}
	
	private void setupBorderPane() {
		myRoot.setTop(this.buildNewLevelButton());
		myRoot.setLeft(myTabs);
	}
	
	private void setRefresh() {
		this.myRoot.setOnMouseEntered(e -> {
			this.refresh();
		});
	}
	
	private void refresh() {
		this.myLevelOverviewTabModel = new LevelOverviewTabModel(this.myAuthModel.getIAuthEnvironment());
//		System.out.println(this.myLevelOverviewTabModel.getCreatedLevels().size());
		this.setUpLevelTabs();
	}
	
	private void setUpLevelTabs() {
		this.myTabs.getTabs().clear();
		this.myLevelOverviewTabModel.getCreatedLevels().stream().forEach(level -> this.addLevelTab(level));
	}
	
	private Tab addNewLevelTab() {
		int newLevelIndex = ((LevelTab)myTabs.getTabs().get(myTabs.getTabs().size()-1)).getIndex() + 1;
		String newLevelName = "Level " + newLevelIndex;
		myLevelOverviewTabModel.addLevel(newLevelName, 10); // TODO: Not hardcode number of lives
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
		myTabs.getTabs().addAll(tab);
	}
	
	private Node buildNewLevelButton() {
		Button addNewLevelButton = new Button(this.myNamesBundle.getString("levelItemLabel"));
		addNewLevelButton.setOnAction(e -> {
			myTabs.getSelectionModel().select(this.addNewLevelTab());
		});
		return addNewLevelButton;
	}
	
	public Node getRoot(){
		return this.myRoot;
	}
}