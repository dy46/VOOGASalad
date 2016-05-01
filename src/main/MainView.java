package main;

import java.util.ResourceBundle;
import auth_environment.view.AuthView;
import auth_environment.view.Welcome;
import game_player.view.PlayerGUI;
import javafx.stage.Stage;


/**
 * Created by BrianLin on 4/15/16.
 * Team member responsible: Shared
 *
 * This View allows a user to choose between entering the Auth Environment OR the Game Player.
 */

public class MainView implements IMainView {

    private Stage myStage;
    private ResourceBundle myResource;

    public MainView (Stage stage) {
        myStage = stage;
        Welcome welcome = new Welcome(this);
        myResource = ResourceBundle.getBundle("game_player/resources/GUI");
    }

    public void display () {
        this.myStage.show();
    }

    public void displayAuth () {
        AuthView authView = new AuthView(this.myStage, this);
        this.display();
    }

    public void displayPlayer() {
		PlayerGUI playerGUI = new PlayerGUI(Integer.valueOf(myResource.getString("PlayerWidth")),
				Integer.valueOf(myResource.getString("PlayerHeight")), this);
		this.myStage.setScene(playerGUI.createPlayerScene());
		this.display();
    }
    
}