
package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.AuthModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.Interfaces.IAuthView;
import auth_environment.view.tabs.GameSettingsTab;
import auth_environment.view.tabs.PathTab;
import auth_environment.view.tabs.LevelOverviewTab;
import auth_environment.view.tabs.MapEditorTab;
import javafx.scene.Scene;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import main.IMainView;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This is the most general frontend/view class and contains a reference to the main Stage and tabs. 
 */

public class AuthView implements IAuthView {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

    private Stage myStage;
    private Scene myScene; 
    private IMainView myMainView; 
    private TabPane myTabs;
    private IAuthModel globalAuthModel;

    public AuthView (Stage stage, IMainView mainView) {
        myStage = stage;
        myMainView = mainView; 
        globalAuthModel = new AuthModel(); 
        setupApperance();
    }
    
    private List<Tab> defaultTabs() {
    	List<Tab> tabs = new ArrayList<Tab>(); 
    	// TODO: cleanup
    	GameSettingsTab globalGameTab = new GameSettingsTab(this.globalAuthModel, myMainView); 
    	MapEditorTab mapEditorTab = new MapEditorTab(this.globalAuthModel); 
//    	AnimationLoaderTab at = new AnimationLoaderTab(new Unit("Tower", new UnitProperties()));
    	tabs.add(new Tab(myNamesBundle.getString("mainTabTitle"), globalGameTab.getRoot()));
    	tabs.add(new VAsTesterTab("WOOOO", this.globalAuthModel));
//    	tabs.add(new Tab("Stringgoeshere", at.getRoot())); 
    	tabs.add(new Tab("Edit Map", mapEditorTab.getRoot())); 
    	tabs.add(new PathTab(myNamesBundle.getString("pathTabTitle"), this.globalAuthModel));
    	tabs.add(new LevelOverviewTab("Level", this.globalAuthModel));
    	tabs.stream().forEach(s -> s.setClosable(false));
    	return tabs; 
    }

	private void setupApperance() {
    	myTabs = new TabPane();
		myScene = new Scene(myTabs);
        myScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet")); // TODO: allow Developer to toggle stylesheets
        myStage.setScene(myScene);
		myStage.setTitle(myNamesBundle.getString("wompTitle"));
		myTabs.getTabs().addAll(this.defaultTabs());
    }

    public void display() {
    	this.myStage.show();
    } 
}