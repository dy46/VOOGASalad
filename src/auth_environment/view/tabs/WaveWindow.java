package auth_environment.view.tabs;
import java.util.ArrayList;
import java.util.List;

import auth_environment.Models.WaveWindowModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import auth_environment.Models.Interfaces.IWaveWindowModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WaveWindow {
	
	private GridPane myLeftGridPane;
	private GridPane myRightGridPane;
	private BorderPane myBorderPane; 
	
	private List<ComboBox<String>> spawningNames;
	private List<ComboBox<String>> placingNames;
	private List<TextField> spawningTimes;
	
	private IAuthModel myAuthModel;
	private IWaveWindowModel myWaveWindowModel; 
	private ILevelOverviewTabModel myLevelOverviewTabModel; 

	private NodeFactory myNodeFactory; 
	
	//TODO: Add Unit Library to WaveWindow constructor	
	public WaveWindow(String level, String wave, IAuthModel authModel, ILevelOverviewTabModel levelOverview){
		this.myAuthModel = authModel;
		this.myLevelOverviewTabModel = levelOverview; 
		this.myNodeFactory = new NodeFactory(); 
		this.spawningNames = new ArrayList<ComboBox<String>>();
		this.placingNames = new ArrayList<ComboBox<String>>();
		this.spawningTimes = new ArrayList<TextField>();
		this.myWaveWindowModel = new WaveWindowModel(authModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary(),
				this.myLevelOverviewTabModel); 
		
		Stage stage = new Stage();
		Group root = new Group();
		Scene newScene = new Scene(root);
		stage.setScene(newScene);
		myLeftGridPane = new GridPane();
		myRightGridPane = new GridPane();
		myBorderPane = new BorderPane();
		myBorderPane.setLeft(myLeftGridPane);
		myBorderPane.setRight(myRightGridPane);
//		root.getChildren().add(myLeftGridPane);
//		root.getChildren().add(myRightGridPane);
		root.getChildren().add(myBorderPane);
		
		String title = level + ", " + wave;
		stage.setTitle(title);
		stage.show(); 
		centerStage(stage);
		
		int index = 0;
		Button dummyButton = new Button("Lol why do i exist");
		ComboBox dummyCBox = new ComboBox(); 
		dummyCBox.setValue("test");
		
		addNewEnemySpace(index, myLeftGridPane, dummyButton, dummyCBox);
		addNewTowerSpace(index, myRightGridPane, dummyButton, dummyCBox);
		
		Button ok = new Button("Ok");
		myBorderPane.setBottom(ok);
		String levelNum = level.split(" ")[1]; 
		String waveNum = wave.split(" ")[1];
		ok.setOnAction(e -> createNewWave(title, levelNum + " " + waveNum));
	}
	
	//createWave(String name, String level, List<String> spawningNames, List<Integer> spawningTimes, List<String> placingNames)
	private void createNewWave(String title, String level) {
		List<String> sn = new ArrayList<String>();
		List<Integer> st = new ArrayList<Integer>();
		List<String> pn = new ArrayList<String>();
		for(ComboBox<String> cb: spawningNames){
			sn.add(cb.getValue());
		}
		for(ComboBox<String> cb: placingNames){
			pn.add(cb.getValue());
		}
		for(TextField hb: spawningTimes){
			st.add(Integer.parseInt(hb.getText()));
		}
		this.myWaveWindowModel.createWave(title, level, sn, st, pn, 4); 
	}

	private void centerStage(Stage stage){
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.setMinHeight(500);
		stage.setMinWidth(500);
		
	}
	
	//TODO: Refactor addNewEnemySpace and addNewTowerSpace methods 
	private void addNewEnemySpace (int index, GridPane newTableInfo, Button dButton, ComboBox cbox) {
		if (cbox.getValue() != null) {
			newTableInfo.getChildren().remove(dButton);
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());

			newTableInfo.add(addSpawnTimeHBox(true, newcbox), 2, index);
			
			index++;
			Button newAffectorButton = new Button("+ Add New Enemy");
			int num = index;
			newAffectorButton
			.setOnAction(e -> addNewEnemySpace(num, newTableInfo, newAffectorButton,
					newcbox));
			newTableInfo.add(newAffectorButton, 2, index);
			spawningNames.add(newcbox);
		}
	}
	
	private void addNewTowerSpace(int index, GridPane newTableInfo, Button dButton, ComboBox cbox){
		if (cbox.getValue() != null) {
			newTableInfo.getChildren().remove(dButton);
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());

			newTableInfo.add(addSpawnTimeHBox(false, newcbox), 2, index);
			
			index++;
			Button newAffectorButton = new Button("+ Add New Tower");
			int num = index;
			newAffectorButton
			.setOnAction(e -> addNewTowerSpace(num, newTableInfo, newAffectorButton,
					newcbox));
			newTableInfo.add(newAffectorButton, 2, index);
			placingNames.add(newcbox);
		}
	}
	
	private Node addSpawnTimeHBox(boolean makeSTBox, ComboBox cBox){
		if(makeSTBox){
			HBox hbox = new HBox();
			hbox.getChildren().add(cBox);
			TextField input = this.myNodeFactory.buildTextFieldWithPrompt("Delay");
			input.setMaxWidth(65);
			input.setMinHeight(25);
			hbox.setMinWidth(200);
			hbox.getChildren().add(input);
			spawningTimes.add(input);
			return hbox;
		}
		else{
			return cBox;
		}	
	}
}
