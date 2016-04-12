package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.backend.ISelector;
import auth_environment.backend.SelectorModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.Menus.MenuToolBar;
import auth_environment.view.Menus.PickerMenu;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class represents a single tab (ie Level) within our View.
 */

public class Workspace {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private ISelector mySelector = new SelectorModel(); 
	
	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	private MapDisplay myDisplay = new MapDisplay(this.mySelector);
	
	public Workspace(TabPane tabPane) {
		this.myTabPane = tabPane; 
		this.setupBorderPane();
	}
	
	private void setupBorderPane() {
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
									  Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setTop(new MenuToolBar(this.myTabPane));
		Text hello = new Text("Hello"); 
		DragDelegate drag = new DragDelegate();
		drag.setupSource(hello);
		this.myBorderPane.setLeft(hello);
		this.myBorderPane.setRight(new PickerMenu());
		this.myBorderPane.setCenter(myDisplay);
	}
	
	public ISelector getSelector() {
		return this.mySelector;
	}

    public Node getRoot() {
    	return this.myBorderPane; 
    }
}
