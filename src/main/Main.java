package main;

import auth_environment.view.AuthView;
import game_data.AuthSerializer;
import game_player.view.GameView;
import game_player.view.PlayerGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


	private AuthView authView; 
	private PlayerGUI myView;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		authView = new AuthView(primaryStage); // for testing Auth
//		myView = new PlayerGUI(645, 587);
//		myEngineController = new EngineController();
//		myEngineController.initialize();
//		primaryStage.setScene(myView.createPlayerScene());
//		primaryStage.show();
//		authView = new View(primaryStage); // for testing Auth
		
		myView = new PlayerGUI(645, 587);
		primaryStage.setScene(myView.createPlayerScene());
		primaryStage.show();
		MainView mainView = new MainView(primaryStage); 
	}

}