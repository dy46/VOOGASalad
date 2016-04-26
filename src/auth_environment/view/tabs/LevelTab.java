package auth_environment.view.tabs;

import auth_environment.Models.LevelOverviewTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;

public class LevelTab extends Tab{
	
	private BorderPane myBorderPane;
	private IAuthModel myAuthModel;
	private int myLevelIndex; 
	private String myName; 
	
	private ILevelOverviewTabModel myLevelOverviewTabModel; 
	
	public LevelTab(String name, int levelIndex, IAuthModel authModel, ILevelOverviewTabModel levelOverview){
		super(name);
		this.myLevelIndex = levelIndex; 
		this.myAuthModel = authModel;
		this.myLevelOverviewTabModel = levelOverview; 
		this.myName = name;
		init();
	}
	
	private void init(){
		this.myBorderPane = new BorderPane();
		this.addRefresh();
		this.createWaveList();
		this.setContent(myBorderPane);
	}
	
	private void addRefresh() {
		this.myBorderPane.setOnMouseEntered(e -> {
			this.refresh();
		});
	}
	
	private void refresh() {
		this.myLevelOverviewTabModel.changeEditedLevel(this.myLevelIndex);
	}
	
	private void createWaveList() { 
		int index = 0;
		GridPane newTableInfo = new GridPane();
		newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(175),new ColumnConstraints(150),new ColumnConstraints(200),new ColumnConstraints(100) );
		newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
		newTableInfo.setPrefSize(600, 200);	
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
	
}
