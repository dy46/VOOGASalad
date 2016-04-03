package auth_environment.view;

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
	
	// TODO: ask team where to extract these
	private static String wompTitle = "womp"; 
	private static String mainTabTitle = "main"; 
	private static String stylesheet = "auth_environment/view/DarkTheme.css";

    private Stage myStage;
    private Scene myScene; 
    private TabPane myTabs = new TabPane();
    private Workspace mainWorkspace;

    public View (Stage stage) {
        myStage = stage;
        myScene = new Scene(myTabs, Color.LIGHTGRAY); 
        myScene.getStylesheets().add(this.stylesheet); // TODO: allow Developer to toggle stylesheets
        myStage.setScene(myScene);
		myStage.setTitle(this.wompTitle);
		mainWorkspace = new Workspace(myStage, myTabs);
		Tab mainTab = new Tab(this.mainTabTitle, mainWorkspace.getRoot());
		mainTab.setClosable(false);
		myTabs.getTabs().add(mainTab);
    }

    public void display() {
    	this.myStage.show();
    }

}
