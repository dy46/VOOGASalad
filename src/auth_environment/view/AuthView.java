package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.backend.GameSettings;
import auth_environment.backend.ISettings;
import auth_environment.view.Workspaces.*;
import javafx.scene.Scene;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This is the most general frontend/view class and contains a reference to the main Stage and tabs. 
 */

public class AuthView {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

    private Stage myStage;
    private Scene myScene; 
    private TabPane myTabs = new TabPane();
    private GlobalGameTab globalGameTab; 
    private AnimationTab animTab; 
    private GameSettings mySettings = new GameSettings(); 

    public AuthView (Stage stage) {
        myStage = stage;
        setupApperance();
    }
    
    private List<Tab> defaultTabs() {
    	List<Tab> tabs = new ArrayList<Tab>(); 
    	// TODO: cleanup
    	globalGameTab = new GlobalGameTab(); 
    	animTab = new AnimationTab();
    	tabs.add(new Tab(myNamesBundle.getString("mainTabTitle"), globalGameTab.getRoot()));
    	tabs.add(new Tab(myNamesBundle.getString("animationTabTitle"), animTab.getRoot()));
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
    
    public ISettings getSettings() {
    	return this.mySettings;
    }

}