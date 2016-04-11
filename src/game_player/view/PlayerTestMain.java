package game_player.view;

import java.util.Arrays;

import game_player.GameDataSource;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlayerTestMain extends Application{
	
	private Stage s;
	PlayerGUI myGui;
	private GameDataSource data;
    @Override
    public void start(Stage myStage) throws Exception {
    	s = new Stage();
    	data = new GameDataSource();
    	data.setDoubleValue("High Score", 100.5);
    	myGui = new PlayerGUI(1200, 720, data);
    	s.setScene(myGui.createPlayerScene());
    	
    	s.show();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
}
