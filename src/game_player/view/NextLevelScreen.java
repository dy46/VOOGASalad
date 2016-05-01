package game_player.view;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NextLevelScreen {
	
	private ResourceBundle myResources;
	private PlayerGUI myGUI;
	private BorderPane myRoot;
	
	public NextLevelScreen(ResourceBundle r, PlayerGUI GUI) {
		myResources = r;
		myGUI = GUI;
		displayScreen();
	}
	
	private void displayScreen() {
		createRoot();
		Stage loadStage = new Stage();
		Scene scene = new Scene(myRoot);
		loadStage.setScene(scene);
		loadStage.show();
	}
	
	private void createRoot() {
		myRoot = new BorderPane();
		Text winMessage = new Text(myResources.getString("WinText"));
		ImageView winImage = new ImageView(new Image(myResources.getString("WinImage")));
		Button loadNextLevel = new Button(myResources.getString("NextLevelButton"));
		loadNextLevel.setOnAction(e -> myGUI.loadNewTab());
		
		myRoot.setTop(winMessage);
		myRoot.setCenter(winImage);
		myRoot.setBottom(loadNextLevel);
	}
}
