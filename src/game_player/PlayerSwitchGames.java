package game_player;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayerSwitchGames implements IGUIObject {
	
	private ResourceBundle myResources;
	private Button myButton;
	
	public PlayerSwitchGames(ResourceBundle r) {
		myResources = r;
	}

	@Override
	public Node createNode() {
		myButton = new Button(myResources.getString("SwitchGame"));
		myButton.setOnAction(event -> openSwitchWindow());
		return myButton;
	}
	
	private void openSwitchWindow() {
		Stage stage = new Stage();
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}

}
