
package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.AuthModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.tabs.GameSettingsTab;
import auth_environment.view.tabs.PathTab;
import auth_environment.view.tabs.StoreTab;
import auth_environment.view.tabs.ElementCreationTab;
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

public class AuthView  {
	
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
    
    // TODO: refactor
    private List<Tab> defaultTabs() {
    	List<Tab> tabs = new ArrayList<Tab>(); 
    	
    	tabs.add(new GameSettingsTab(myNamesBundle.getString("mainTabTitle"), globalAuthModel, myMainView)); 
    	tabs.add(new ElementCreationTab(myNamesBundle.getString("creationTabLabel"), globalAuthModel));
    	tabs.add(new MapEditorTab(myNamesBundle.getString("mapTabTitle"), globalAuthModel));
    	tabs.add(new PathTab(myNamesBundle.getString("pathTabTitle"), globalAuthModel));
    	tabs.add(new LevelOverviewTab("Level", globalAuthModel));
//    	tabs.add(new StoreTab(myNamesBundle.getString("storeTabTitle"), globalAuthModel));
    	
    	tabs.stream().forEach(s -> s.setClosable(false));
    	return tabs; 
    }

	private void setupApperance() {
    	myTabs = new TabPane();
		myScene = new Scene(myTabs);
        myScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet")); 
        myStage.setScene(myScene);
		myStage.setTitle(myNamesBundle.getString("wompTitle"));
		myTabs.getTabs().addAll(defaultTabs());
    }

}