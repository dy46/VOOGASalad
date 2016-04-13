package main;

import auth_environment.view.View;
import game_engine.EngineController;
import game_player.view.GameView;
import game_player.view.PlayerGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private View authView; 
	private PlayerGUI myView;
	private EngineController myEngineController;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
//		authView = new View(primaryStage); // for testing Auth
		myView = new PlayerGUI(1000, 700);
		myEngineController = new EngineController();
		myEngineController.initialize();
		primaryStage.setScene(myView.createPlayerScene());
		primaryStage.show();
	}
	
}
