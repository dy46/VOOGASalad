package main;

import auth_environment.view.View;
import game_engine.EngineController;
import game_player.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private View authView; 
	private GameView myView;
	private EngineController myEngineController;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
//		authView = new View(primaryStage); // for testing Auth
		myView = new GameView(primaryStage);
		myEngineController = new EngineController();
		myEngineController.initialize();
		myView.display();
	}
	
}