
package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.AuthModel;
import auth_environment.Models.SampleAuthData;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.Interfaces.IAuthView;

import auth_environment.view.Workspaces.GlobalGameTab;
import auth_environment.view.Workspaces.PathTab;

import auth_environment.view.tabs.AnimationLoaderTab;
import game_engine.game_elements.Unit;
import game_engine.properties.UnitProperties;

import auth_environment.view.tabs.LevelOverviewTab;
//import auth_environment.view.tabs.LevelViewTab;
import javafx.scene.Scene;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

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
    private TabPane myTabs = new TabPane();
    private IAuthModel globalAuthModel;

    public AuthView (Stage stage) {
        myStage = stage;
        this.globalAuthModel = new AuthModel(); 
        setupApperance();
    }
    
    private List<Tab> defaultTabs() {
    	List<Tab> tabs = new ArrayList<Tab>(); 
    	// TODO: cleanup
    	GlobalGameTab globalGameTab = new GlobalGameTab(this.globalAuthModel); 
    	PathTab pathTab = new PathTab(this.globalAuthModel); 
    	AnimationLoaderTab at = new AnimationLoaderTab(new Unit("Tower", new UnitProperties()));

//    	LevelOverviewTab levelTab = new LevelOverviewTab(this.authModel);
    	tabs.add(new Tab(myNamesBundle.getString("mainTabTitle"), globalGameTab.getRoot()));
    	tabs.add(new VAsTesterTab("WOOOO", this.globalAuthModel));
    	tabs.add(new Tab("Stringgoeshere", at.getRoot())); 
    	tabs.add(new Tab(myNamesBundle.getString("pathTabTitle"), pathTab.getRoot()));
    	tabs.add(new LevelOverviewTab("Level", this.globalAuthModel));
    	tabs.stream().forEach(s -> s.setClosable(false));
    	return tabs; 
    }

	private void setupApperance() {
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
