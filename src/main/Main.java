package main;

import auth_environment.view.View;
import game_data.AuthSerializer;
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
		//		myView = new GameView(primaryStage);
		//		myEngineController = new EngineController();
		//		myEngineController.initialize();
		//		myView.display();
		//		authView = new View(primaryStage); // for testing Auth
		//		myView = new GameView(primaryStage);
		//		myEngineController = new EngineController();
		//		myEngineController.initialize();
		//		myView.display();

		//		String hello = "hello"; 
		//		AuthSerializer writer = new AuthSerializer(); 
		//		writer.SerializeData(hello); //Sample object saving
		//		String out = (String) writer.Deserialize();
		//		System.out.println(out); 

		//		authView = new View(primaryStage); // for testing Auth
//		myView = new GameView(primaryStage);
//		myView.display();

		//		authView = new View(primaryStage); // for testing Auth
		myView = new PlayerGUI(645, 587);
		myEngineController = new EngineController();
		myEngineController.initialize();
		primaryStage.setScene(myView.createPlayerScene());
		primaryStage.show();
	}

}