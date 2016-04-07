package auth_environment.view;

import java.util.ResourceBundle;

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
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	
	public Workspace(TabPane tabPane) {
		this.myTabPane = tabPane; 
		this.setupBorderPane();
	}
	
	private void setupBorderPane() {
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
									  Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setTop(new MenuToolBar(this.myTabPane));
		this.myBorderPane.setLeft(new VBox());
		this.myBorderPane.setRight(new VBox());
		this.myBorderPane.setCenter(new MapDisplay());
	}


    public Node getRoot() {
    	return this.myBorderPane; 
    }
}
