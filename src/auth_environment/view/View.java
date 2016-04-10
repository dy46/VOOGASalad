package auth_environment.view;

import java.util.ResourceBundle;

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

public class View {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
    private Stage myStage;
    private Scene myScene; 
    private TabPane myTabs = new TabPane();
    private Workspace mainWorkspace;

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
		mainWorkspace = new Workspace(myTabs);
		Tab mainTab = new Tab(myNamesBundle.getString("mainTabTitle"), mainWorkspace.getRoot());
		mainTab.setClosable(false);
		myTabs.getTabs().add(mainTab);
	}


    public void display() {
    	this.myStage.show();
    }

}
