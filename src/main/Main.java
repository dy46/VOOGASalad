package main;

import auth_environment.view.View;
import game_engine.EngineController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private View myView;
	private EngineController myEngineController;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		myView = new View(primaryStage);
		myEngineController = new EngineController();
		myEngineController.initialize();
		myView.display();
	}

}