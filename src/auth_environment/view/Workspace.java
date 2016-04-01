package auth_environment.view;

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
	private Parent myRoot; 
	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	
	private VBox leftVBox = new VBox(); // TODO: replace with custom class 
	private VBox rightVBox = new VBox(); // TODO: replace with custom class 
	
	public Workspace(Stage stage, TabPane tabPane) {
		this.myStage = stage;
		this.myTabPane = tabPane; 
	}


    public Node getRoot() {
    	return this.myRoot;
    }
}
