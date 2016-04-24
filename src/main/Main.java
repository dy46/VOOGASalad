package main;
<<<<<<< HEAD
import exceptions.WompException;
import game_player.view.PlayerGUI;
=======

>>>>>>> 8d680d3b0f9c0fb898e3573924763b9431a5ebb3
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

<<<<<<< HEAD
	//	private View authView; 
	private PlayerGUI myView;

=======
>>>>>>> 8d680d3b0f9c0fb898e3573924763b9431a5ebb3
	public static void main(String[] args) {
		launch(args);
	}

<<<<<<< HEAD
	public void start(Stage primaryStage) throws WompException {
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
		//		myEngineController = new EngineController();
		//		myEngineController.initialize();

		//		authView = new View(primaryStage); // for testing Auth

		myView = new PlayerGUI(645, 614);
		primaryStage.setScene(myView.createPlayerScene());
		primaryStage.show();
=======
	@Override
	public void start(Stage primaryStage) {
		MainView mainView = new MainView(primaryStage); 
>>>>>>> 8d680d3b0f9c0fb898e3573924763b9431a5ebb3
	}

}