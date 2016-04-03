package main;

import game_engine.EngineController;
import game_player.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private GameView myView;
	private EngineController myEngineController;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		myView = new GameView(primaryStage);
		myEngineController = new EngineController();
		myEngineController.initialize();
		myView.display();
	}

}
