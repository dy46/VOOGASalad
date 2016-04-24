package auth_environment.view.tabs;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.UnitPicker;
import game_engine.TestingEngineWorkspace;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WaveWindow {
	
	//TODO: Add Unit Library to WaveWindow constructor	
	public WaveWindow(String level, String wave){
		Stage stage = new Stage();
		stage.setTitle(level + ", " + wave);
		stage.show(); 
		centerStage(stage);
	}
	
	private void centerStage(Stage stage){
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		double x = (primScreenBounds.getWidth() - stage.getWidth()) / 2;
		double y = (primScreenBounds.getHeight() - stage.getHeight()) / 2;
	
		System.out.println(x + " " + y);
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		
	}
	
	
}
