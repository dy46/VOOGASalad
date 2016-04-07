package game_player;

import javafx.application.Application;
import javafx.stage.Stage;

public class PlayerTestMain extends Application{
	
	private Stage s;
	PlayerGUI myGui;
    @Override
    public void start(Stage myStage) throws Exception {
    	s = new Stage();
    	myGui = new PlayerGUI(1200, 720);
    	s.setScene(myGui.createPlayerScene());
    	
    	s.show();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
}
