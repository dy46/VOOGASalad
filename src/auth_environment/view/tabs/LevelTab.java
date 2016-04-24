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
	private IAuthModel myAuthModel;
	
	private NodeFactory myNodeFactory;
	
	public LevelTab(String name, IAuthEnvironment myInterface){
		super(name);
		this.myInterface = myInterface;
		this.myNodeFactory = new NodeFactory();
		init();
	}
	
	private void init(){
		System.out.println("test");
		myBorderPane = new BorderPane();
		TitledPane newPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		this.myBorderPane.getChildren().add(this.myNodeFactory.buildButton("hello"));
		this.setContent(myBorderPane);
	}
}
