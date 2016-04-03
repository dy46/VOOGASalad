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
	
	// TODO: ask team where to extract these constants
	private static double defaultBorderPaneWidth = 600;
	private static double defaultBorderPaneHeight = 600; 
	
	private Stage mainStage; 
	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	
	public Workspace(TabPane tabPane) {
		this.myTabPane = tabPane; 
		this.init();
	}
	
	private void init() {
		this.myBorderPane.setPrefSize(this.defaultBorderPaneWidth, this.defaultBorderPaneHeight);
		this.myBorderPane.setTop(new MenuToolBar(this.myTabPane));
		this.myBorderPane.setLeft(new VBox());
		this.myBorderPane.setRight(new VBox());
		this.myBorderPane.setCenter(new MapDisplay());
	}


    public Node getRoot() {
    	return this.myBorderPane; 
    }
}
