package main;

import java.util.ResourceBundle;

import auth_environment.view.AuthView;
import auth_environment.view.Welcome;
import game_engine.EngineController;
import game_player.view.PlayerGUI;
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

    public MainView (Stage stage) {
        myStage = stage;
        Welcome welcome = new Welcome(this);
    }
    
    public void display() {
    	this.myStage.show();
    }
    
    public void displayAuth() {
    	AuthView authView = new AuthView(this.myStage);
    	this.display(); 
    }
    
    public void displayPlayer() {
    	System.out.println("David, please modify as you see fit. Thanks!");
		PlayerGUI playerGUI = new PlayerGUI(645, 587); // TODO: extract constants
		this.myStage.setScene(playerGUI.createPlayerScene());
		this.display();
    }
}