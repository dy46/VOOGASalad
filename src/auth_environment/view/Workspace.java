package auth_environment.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
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
	
	public Workspace(Stage stage, TabPane tabPane) {
		this.myStage = stage;
		this.myTabPane = tabPane; 
	}

    public void addPane(Pane pane) {
    	
    }

    public Node getRoot() {
    	return this.myRoot;
    }
}
