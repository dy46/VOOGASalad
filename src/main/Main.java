package main;

import game_engine.factories.BoundsFactory;
import game_player.view.PlayerGUI;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utility.CloudStorageFrontend;

public class Main extends Application {

	private PlayerGUI myView;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		MainView mainView = new MainView(primaryStage);
	}
	
}