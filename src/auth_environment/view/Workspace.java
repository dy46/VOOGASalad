package auth_environment.view;

import auth_environment.view.Menus.MenuToolBar;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class represents a single tab within our View.
 */

public class Workspace {
	
	private Stage myStage;
	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	
	private VBox leftVBox = new VBox(); // TODO: replace with custom class 
	private VBox rightVBox = new VBox(); // TODO: replace with custom class 
	private MenuToolBar topMenuBar;  
	
	public Workspace(Stage stage, TabPane tabPane) {
		this.myStage = stage;
		this.myTabPane = tabPane; 
		this.topMenuBar = new MenuToolBar(this.myTabPane);
		this.init();
	}
	
	private void init() {
		this.myBorderPane.setTop(this.topMenuBar);
		this.myBorderPane.setLeft(this.leftVBox);
		this.myBorderPane.setRight(this.rightVBox);
	}


    public Node getRoot() {
    	return this.myBorderPane; 
    }
}
