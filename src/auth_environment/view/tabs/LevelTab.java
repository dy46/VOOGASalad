package auth_environment.view.tabs;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.UnitPicker;
import game_engine.TestingEngineWorkspace;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
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

public class LevelTab extends Tab{
	private IAuthEnvironment myInterface;
	private BorderPane myBorderPane;
	private GridPane myGridPane; 
	private IAuthModel myAuthModel;
//	private int gridIndex = 0; 
	private NodeFactory myNodeFactory;
	private String myName; 
	
	public LevelTab(String name, IAuthEnvironment myInterface){
		super(name);
		myName = name;
		this.myInterface = myInterface;
		this.myNodeFactory = new NodeFactory();
		init();
	}
	
	private void init(){
		myBorderPane = new BorderPane();
		TitledPane newPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		this.myBorderPane.getChildren().add(this.myNodeFactory.buildButton("hello"));
		createWaveList();
		this.setContent(myBorderPane);
	}
	
	private void createWaveList(){
		int index = 0;
		GridPane newTableInfo = new GridPane();
		newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(175),new ColumnConstraints(150),new ColumnConstraints(200),new ColumnConstraints(100) );
		newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
		newTableInfo.setPrefSize(600, 200);	
		myBorderPane.setLeft(newTableInfo);
		Button waveButton = new Button("waveeee");
		addNewWaveSpace(index, newTableInfo, waveButton);
	}
	
	private void addNewWaveSpace(int index, GridPane newTableInfo, Button waveButton) {
		newTableInfo.getChildren().remove(waveButton);
		int waveNum = index + 1;
		String waveName = "Wave " + waveNum;
		Button wave = new Button(waveName);
		wave.setOnAction(e -> new WaveWindow(myName, waveName));
		newTableInfo.add(wave, 2, index);
		index++;
		Button newWaveButton = new Button("+ Add Wave");
		int num = index;
		newWaveButton.setOnAction(e-> addNewWaveSpace(num, newTableInfo, newWaveButton));
		newTableInfo.add(newWaveButton, 2, index);
	}
	
}
