package main;

import auth_environment.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private View myView;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		myView = new View(primaryStage);
		myView.display();
	}

}
