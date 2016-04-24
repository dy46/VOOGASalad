package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.view.tabs.ElementTab;
import auth_environment.view.tabs.TowerTab;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
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
	
	private BorderPane myBorderPane;
	private IAuthModel myAuthModel;
	
	
	public LevelTab(IAuthModel authModel){
		this.myAuthModel = authModel;
		init();
	}
	
	private void init(){
		myBorderPane = new BorderPane();
		TitledPane newPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		
		newPane.setText("New");
		this.setContent(myBorderPane);
	}
	
	public Node getRoot(){
		return this.myBorderPane;
	}
}
