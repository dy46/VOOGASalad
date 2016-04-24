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
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WaveWindow {
	private GridPane myGridPane;
	//TODO: Add Unit Library to WaveWindow constructor	
	public WaveWindow(String level, String wave){
		Stage stage = new Stage();
		Group root = new Group();
		Scene newScene = new Scene(root);
		stage.setScene(newScene);
		myGridPane = new GridPane();
		root.getChildren().add(myGridPane);
		
		stage.setTitle(level + ", " + wave);
		stage.show(); 
		centerStage(stage);
		
		int index = 0;
		Button dummyButton = new Button("Lol why do i exist");
		ComboBox dummyCBox = new ComboBox(); 
		dummyCBox.setValue("test");
		
		addNewEnemySpace(index, myGridPane, dummyButton, dummyCBox);
		
	}
	
	private void centerStage(Stage stage){
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.setMinHeight(500);
		stage.setMinWidth(500);
		
	}
	
	private void addNewEnemySpace (int index, GridPane newTableInfo, Button AffectorButton, ComboBox cbox) {
		if (cbox.getValue() != null) {
			newTableInfo.getChildren().remove(AffectorButton);
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll("FireTower", "IceTower", "TackTower");
			HBox hbox = new HBox();
			hbox.getChildren().add(newcbox);
			TextField input = new TextField();
			input.setMaxWidth(65);
			input.setMinHeight(25);
			hbox.setMinWidth(200);
			hbox.getChildren().add(input);
			newTableInfo.add(hbox, 2, index);
			index++;
			Button newAffectorButton = new Button("+ Add New Enemy");
			int num = index;
			newAffectorButton
			.setOnAction(e -> addNewEnemySpace(num, newTableInfo, newAffectorButton,
					newcbox));
			newTableInfo.add(newAffectorButton, 2, index);
		}
	}
	
	
}
