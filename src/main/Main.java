package main;

<<<<<<< HEAD
import auth_environment.view.View;
import game_data.Serializer;
import game_engine.EngineController;
import game_player.view.GameView;
import game_player.view.PlayerGUI;
=======
>>>>>>> auth-environment
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
<<<<<<< HEAD
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

		authView = new View(primaryStage); // for testing Auth
//		myView = new PlayerGUI(645, 587);
//		myEngineController = new EngineController();
//		myEngineController.initialize();
//		primaryStage.setScene(myView.createPlayerScene());
//		primaryStage.show();
=======
		MainView mainView = new MainView(primaryStage); 
>>>>>>> auth-environment
	}

}