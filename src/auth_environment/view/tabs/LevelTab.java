package auth_environment.view.tabs;

import java.util.ResourceBundle;

import auth_environment.Models.LevelTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;

public class LevelTab extends Tab{
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private BorderPane myBorderPane;
	private IAuthModel myAuthModel;
	private String myName; 
	private LevelTabModel myLevelTabModel;
	private ILevelOverviewTabModel myLevelOverviewTabModel; 
	
	public LevelTab(String name, int levelIndex, IAuthModel authModel, ILevelOverviewTabModel levelOverview){
		super(name);
		this.myLevelTabModel = new LevelTabModel(levelIndex); 
		this.myAuthModel = authModel;
		this.myLevelOverviewTabModel = levelOverview; 
		this.myName = name;
		init();
	}
	
	private void init() {
		this.myBorderPane = new BorderPane();
		this.setRefresh();
		this.createWaveList();
		this.setContent(myBorderPane);
	}
	
	private void setRefresh() {
		this.myBorderPane.setOnMouseEntered(e -> {
			this.refresh();
		});
		this.setOnSelectionChanged(e -> {
			this.refresh();
		});
	}
	
	private void refresh() {
		this.myLevelOverviewTabModel.changeEditedLevel(this.myLevelTabModel.getLevelIndex());
	}
	
	private void createWaveList() { 
		int index = 0;
		GridPane newTableInfo = new GridPane();
		newTableInfo.setPadding(new Insets(Double.parseDouble(myDimensionsBundle.getString("waveListPaddingTop")), Double.parseDouble(myDimensionsBundle.getString("waveListPaddingRight")), 
				Double.parseDouble(myDimensionsBundle.getString("waveListPaddingBottom")), Double.parseDouble(myDimensionsBundle.getString("waveListPaddingLeft"))));
		newTableInfo.setVgap(Double.parseDouble(myDimensionsBundle.getString("waveListVGap")));
		newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("waveListConstraintsTop"))), 
				new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("waveListConstraintsRight"))), 
				new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("waveListConstraintsBottom"))), 
				new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("waveListConstraintsLeft"))));
		newTableInfo.getRowConstraints().addAll(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("waveListConstraintsRow"))));
		newTableInfo.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("waveListPrefSizeX")), Double.parseDouble(myDimensionsBundle.getString("waveListPrefSizeY")));	//TODO: Avoid hard-coded values
		this.myBorderPane.setLeft(newTableInfo);
		Button dummyWaveButton = new Button();
		this.addNewWaveSpace(index, newTableInfo, dummyWaveButton);
	}
	
	private void addNewWaveSpace(int index, GridPane newTableInfo, Button waveButton) {
		newTableInfo.getChildren().remove(waveButton);
		int waveNum = index + 1;
		String waveName = "Wave " + waveNum;
		Button wave = new Button(waveName);
		wave.setOnAction(e -> new WaveWindow(myName, waveName, myAuthModel, this.myLevelOverviewTabModel));
		newTableInfo.add(wave, 2, index);
		index++;
		Button newWaveButton = new Button("+ Add Wave");
		int num = index;
		newWaveButton.setOnAction(e -> addNewWaveSpace(num, newTableInfo, newWaveButton));
		newTableInfo.add(newWaveButton, 2, index);
	}
	
	public int getIndex() {
		return myLevelTabModel.getLevelIndex();
	}
	
}
