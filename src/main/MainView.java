package main;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/15/16.
 * Team member responsible: Shared
 *
 * This View allows a user to choose between entering the Auth Environment OR the Game Player. 
 */


public class MainView implements IMainView {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

    private Stage myStage;
    private Scene myScene; 

    public View (Stage stage) {
        myStage = stage;
        
        Welcome welcome = new Welcome(this);
        setupWorkspace();
    }


	private void setupWorkspace() {
		myScene = new Scene(myTabs, Color.web("292929")); 
        myScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet")); // TODO: allow Developer to toggle stylesheets
        myStage.setScene(myScene);
		myStage.setTitle(myNamesBundle.getString("wompTitle"));
		mainWorkspace = new GameWorkspace(myTabs, this.mySettings);
		Tab mainTab = new Tab(myNamesBundle.getString("mainTabTitle"), mainWorkspace.getRoot());
		mainTab.setClosable(false);
		myTabs.getTabs().add(mainTab);
		
		
		//VAsTesterTab vtest = new VAsTesterTab(myTabs);
    }

    public void display() {
    	this.myStage.show();
    }
    
    public ISettings getSettings() {
    	return this.mySettings;
    }

}