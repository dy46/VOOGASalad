package main;

<<<<<<< HEAD
=======
import game_engine.factories.BoundsFactory;
import game_player.view.PlayerGUI;
>>>>>>> master
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		MainView mainView = new MainView(primaryStage);
	}
	
}