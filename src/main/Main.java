package main;

import game_player.view.PlayerGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private PlayerGUI myView;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//MainView mainView = new MainView(primaryStage);
		
		myView = new PlayerGUI(790, 614);
		primaryStage.setScene(myView.createPlayerScene());
		primaryStage.show();
		
//		MainView mainView = new MainView(primaryStage);
	}

}