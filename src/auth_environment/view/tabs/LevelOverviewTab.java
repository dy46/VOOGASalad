package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.view.tabs.ElementTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.LevelOverviewTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import auth_environment.view.UnitPicker;
import auth_environment.view.Workspaces.GlobalGameTab;
import game_engine.TestingEngineWorkspace;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelOverviewTab extends Tab{
	
	private BorderPane myRoot;
	private TabPane myTabs;
	private IAuthModel myAuthModel;
	private IAuthEnvironment myInterface;
	private Button addNewLevelButton;
	private ILevelOverviewTabModel overviewModel;
	
	public LevelOverviewTab(String name, IAuthModel authModel){
		super(name);
		this.myTabs = new TabPane();
		this.addNewLevelButton = new Button("Add New Level");
		this.myAuthModel = authModel;
		this.overviewModel = new LevelOverviewTabModel();
		this.myInterface = this.myAuthModel.getIAuthEnvironment(); 
		init();
	}
	
	private void init(){
		myRoot = new BorderPane();
		myRoot.setTop(addNewLevelButton);
		addSubTabs();
		myRoot.setLeft(myTabs);
		this.setContent(myRoot);
	}
	
	private void addSubTabs(){
		addNewLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Tab tab = new LevelTab("Level " + (myTabs.getTabs().size() + 1), myInterface);
				myTabs.getTabs().addAll(tab);
				myTabs.getSelectionModel().select(tab);
			}
		});
	}
	
	public Node getRoot(){
		return this.myRoot;
	}
}
