package game_player;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlayerSwitchGames implements IGUIObject {
	
	private ResourceBundle myResources;
	private Button myButton;
	private GridPane myRoot;
	private SwitchWindow mySwitchWindow;
	
	public PlayerSwitchGames(ResourceBundle r) {
		myResources = r;
		myRoot = new GridPane();
		mySwitchWindow = new SwitchWindow(r);
	}

	@Override
	public Node createNode() {
		myButton = new Button(myResources.getString("SwitchGame"));
		myButton.setOnAction(event -> openSwitchWindow());
		return myButton;
	}
	
	private void openSwitchWindow() {
		Stage stage = new Stage();
		myRoot = mySwitchWindow.createWindow();
		Scene switchWindowScene = new Scene(myRoot, 500, 500);
		stage.setScene(switchWindowScene);
		stage.show();
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}

}
