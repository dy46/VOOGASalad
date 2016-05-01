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
import javafx.scene.layout.HBox;

public class LevelOverviewTab extends Tab {
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	private BorderPane myRoot;
	private TabPane myTabs;
	private IAuthModel myAuthModel;
	private ILevelOverviewTabModel myLevelOverviewTabModel;
	
	private Button loadButton = new Button("Refresh from XML");
	
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
		setRefresh();
	}
	
	private void setupBorderPane() {
		HBox customize = new HBox();
		customize.getChildren().addAll(this.buildNewLevelButton(), loadButton);
		myRoot.setRight(customize);
		loadButton.setOnAction(e -> setUpLevelTabs());
		myRoot.setLeft(myTabs);
	}
	
	private void setRefresh(){
		this.setOnSelectionChanged(e -> refresh());
	}
	
	private void refresh(){
		myLevelOverviewTabModel.refresh(myAuthModel.getIAuthEnvironment());
		System.out.println("refresh");
	}
	
	private void setUpLevelTabs() {
		this.myTabs.getTabs().clear();
		System.out.println("refreshing levels" + myLevelOverviewTabModel.getCreatedLevels().size());
		if (this.myLevelOverviewTabModel.getCreatedLevels().size()>0) {
			System.out.println("Heyheyheyeheyhey");
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
		Tab tab = new LevelTab("Level " + (this.myLevelOverviewTabModel.getCreatedLevels().indexOf(level) + 1),
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