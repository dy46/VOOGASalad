package auth_environment.view.tabs;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.WaveWindowModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import auth_environment.Models.Interfaces.IWaveWindowModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
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
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private GridPane myLeftGridPane;
	private GridPane myRightGridPane;
	private BorderPane myBorderPane; 
	
	private List<ComboBox<String>> spawningNames;
	private List<ComboBox<String>> placingNames;
	private List<TextField> spawningTimes;
	
	private IAuthModel myAuthModel;
	private IWaveWindowModel myWaveWindowModel; 
	private ILevelOverviewTabModel myLevelOverviewTabModel; 
	
	private Stage stage;
	private Group root;
	private Scene newScene;
	private Wave myWave;
	private Button dummyButton;
	private ComboBox dummyCBox;
	private ComboBox lastCBox;
	private int indexLeft;
	private int indexRight;
	private NodeFactory myNodeFactory; 
		
	public WaveWindow(String level, String wave, IAuthModel authModel, ILevelOverviewTabModel levelOverview){
		this.myAuthModel = authModel;
		this.myLevelOverviewTabModel = levelOverview; 
		this.myWaveWindowModel = new WaveWindowModel(authModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary(),
				this.myLevelOverviewTabModel); 
		init();
		String title = level + " " + wave;
		showWindow(title);
		indexLeft = 0;
		indexRight = 0;
		dummyButton = new Button();
		dummyCBox = new ComboBox(); 
		dummyCBox.setValue("test");
		addNewElementSpace(indexLeft, myLeftGridPane, dummyButton, dummyCBox, true);
		addNewElementSpace(indexRight, myRightGridPane, dummyButton, dummyCBox, false);
		Button ok = new Button("Ok"); 
		myBorderPane.setBottom(ok);
		String levelNum = level.split(" ")[1]; 
		String waveNum = wave.split(" ")[1];
		ok.setOnAction(e -> createNewWave(title, levelNum + " " + waveNum));
	}
	
	public WaveWindow(Wave wave, IAuthModel authModel, ILevelOverviewTabModel levelOverview){
		this.myAuthModel = authModel;
		this.myLevelOverviewTabModel = levelOverview;
		this.myWave = wave;
		this.myWaveWindowModel = new WaveWindowModel(authModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary(),
				this.myLevelOverviewTabModel); 
		init();
		String title = wave.getName();
		showWindow(title);
		indexLeft = 0;
		indexRight = 0;
		dummyButton = new Button();
		dummyCBox = new ComboBox(); 
		dummyCBox.setValue("test");
//		addEnemySpace();
//		this.addNewElementButton(myLeftGridPane, true);
//		addTowerSpace();
//		this.addNewElementButton(myRightGridPane, false);
		reloadInformation();
		Button ok = new Button("Ok");
		myBorderPane.setBottom(ok);
		String levelNum = title.split(" ")[1];
		String waveNum = title.split(" ")[3];
		ok.setOnAction(e -> createNewWave(title, levelNum + " " + waveNum));
	}
	
	
	private void reloadInformation(){
		addEnemySpace();
		this.addNewElementButton(myLeftGridPane, true);
		addTowerSpace();
		this.addNewElementButton(myRightGridPane, false);
	}
	
	private void addEnemySpace(){
		if(myWave.getSpawningUnits().size() > 0){
			int unitIndex = 0;
			for(Unit u: myWave.getSpawningUnits()){
				reloadElementSpace(indexLeft, myLeftGridPane, true, u, myWave.getSpawnTimes().get(unitIndex));
			}
			
		}
	}
	
	private void addTowerSpace(){
		if(myWave.getPlacingUnits().size() > 0){
			for(Unit u: myWave.getPlacingUnits()){
				reloadElementSpace(indexRight, myRightGridPane, false, u, 0);
			}
		}
	}
	
	private void showWindow(String title){
		stage.setTitle(title);
		stage.show();
		centerStage(stage);
	}
	
	private void init(){
		this.myNodeFactory = new NodeFactory(); 
		this.spawningNames = new ArrayList<ComboBox<String>>();
		this.placingNames = new ArrayList<ComboBox<String>>();
		this.spawningTimes = new ArrayList<TextField>();
		stage = new Stage();
		root = new Group();
		newScene = new Scene(root);
		stage.setScene(newScene);
		myLeftGridPane = new GridPane();
		myRightGridPane = new GridPane();
		myBorderPane = new BorderPane();
		myBorderPane.setLeft(myLeftGridPane);
		myBorderPane.setRight(myRightGridPane);
		root.getChildren().add(myBorderPane);
	}
	
	private void createNewWave(String title, String level) {
		List<String> sn = new ArrayList<String>();
		List<Integer> st = new ArrayList<Integer>();
		List<String> pn = new ArrayList<String>();
		
		spawningNames.stream().forEach(s -> sn.add(s.getValue()));
		placingNames.stream().forEach(s -> pn.add(s.getValue()));
		spawningTimes.stream().forEach(s -> st.add(Integer.parseInt(s.getText())));
		this.myWaveWindowModel.createWave(title, level, sn, st, pn, 4); 
	}

	private void centerStage(Stage stage){
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.setMinHeight(500);
		stage.setMinWidth(500);
		
	}
	
	private void addNewElementSpace(int index, GridPane newTableInfo, Button dButton, ComboBox cBox, boolean makeInputField){
		if(cBox.getValue() != null){
			newTableInfo.getChildren().remove(dButton);
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
			
			newTableInfo.add(addSpawnTimeHBox(makeInputField, newcbox), 2, index);
			index++;
			
			Button newAffectorButton = new Button(myNamesBundle.getString("waveAddNewElement"));
			int num = index;
			newAffectorButton
			.setOnAction(e -> addNewElementSpace(num, newTableInfo, newAffectorButton,
					newcbox, makeInputField));
			newTableInfo.add(newAffectorButton, 2, index);
			
			if(makeInputField){
				spawningNames.add(newcbox);
			}
			else{
				placingNames.add(newcbox);
			}
		}
	}
	
	private void reloadElementSpace(int index, GridPane newTableInfo, boolean makeInputField, Unit u, int spawnDelay){
		ComboBox<String> newCBox = new ComboBox<String>();
		lastCBox = newCBox;
		newCBox.getItems().addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
		newCBox.setValue(u.getName());
		newTableInfo.add(reloadSpawnTimeHBox(makeInputField, newCBox, spawnDelay), 2, index);
//		index++; 
		
		if(makeInputField){
			spawningNames.add(newCBox);
		}
		else{
			placingNames.add(newCBox);
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
		
	private void addNewElementButton(GridPane myGP, boolean makeInputField){
		Button newElement = new Button(myNamesBundle.getString("waveAddNewElement"));
		newElement.setOnAction(e -> addNewElementSpace(indexLeft, myGP, newElement, lastCBox, makeInputField));
	}
	
	private Node reloadSpawnTimeHBox(boolean makeSTBox, ComboBox cBox, int spawnDelay){
		if(makeSTBox){
			HBox hbox = new HBox();
			hbox.getChildren().add(cBox);
			TextField input = this.myNodeFactory.buildTextFieldWithPrompt("Delay");
			input.setText(Integer.toString(spawnDelay));
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
