package main;

import java.util.ResourceBundle;
import auth_environment.view.AuthView;
import auth_environment.view.Welcome;
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

    private Stage myStage;

    public MainView (Stage stage) {
        myStage = stage;
        Welcome welcome = new Welcome(this);
    }

    public void display () {
        this.myStage.show();
    }

    public void displayAuth () {
        AuthView authView = new AuthView(this.myStage, this);
        this.display();
    }

    
    public void displayPlayer() {
		PlayerGUI playerGUI = new PlayerGUI(860, 765, this); // TODO: extract constants
		this.myStage.setScene(playerGUI.createPlayerScene());
		this.display();
    }
}
