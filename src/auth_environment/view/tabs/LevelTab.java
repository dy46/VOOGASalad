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

public class LevelTab extends Tab{
	
	private IAuthEnvironment myInterface;
	
	public LevelTab(String name, IAuthEnvironment myInterface){
		super(name);
		this.myInterface = myInterface;
		init();
	}
	
	private void init(){
		
	}
}
