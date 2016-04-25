package auth_environment.view.tabs;

import auth_environment.Models.Interfaces.IAuthModel;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;

public class LevelTab extends Tab{
	
	private BorderPane myBorderPane;
	private IAuthModel myAuthModel;
	private String myName; 
	
	public LevelTab(String name, IAuthModel authModel){
		super(name);
		this.myAuthModel = authModel;
		this.myName = name;
		init();
	}
	
	private void init(){
		this.myBorderPane = new BorderPane();
		this.createWaveList();
		this.setContent(myBorderPane);
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
		wave.setOnAction(e -> new WaveWindow(myName, waveName, myAuthModel));
		newTableInfo.add(wave, 2, index);
		index++;
		Button newWaveButton = new Button("+ Add Wave");
		int num = index;
		newWaveButton.setOnAction(e -> addNewWaveSpace(num, newTableInfo, newWaveButton));
		newTableInfo.add(newWaveButton, 2, index);
	}
	
}
