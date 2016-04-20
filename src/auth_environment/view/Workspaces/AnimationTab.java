package auth_environment.view.Workspaces;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.backend.GameDataController;
import auth_environment.backend.ISelector;
import auth_environment.backend.SelectorModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.ElementPicker;
import auth_environment.view.MapDisplay;
import auth_environment.view.Menus.MenuToolBar;
import game_data.GameData;
import game_engine.game_elements.Tower;

import auth_environment.backend.ISettings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created on 4/19/16
 * @author codyli
 * 
 * This Tab is for customizing the animation of units (towers, enemies, etc.).
 *
 */
public class AnimationTab implements IWorkspace{
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private BorderPane myBorderPane = new BorderPane(); 
	
	public AnimationTab(){
		this.setupBorderPane();
	}
	
	private void setupBorderPane(){
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		
//		this.myBorderPane.setCenter(this.buildCenter());
	}
	
	@Override
	public Node getRoot() {
		// TODO Auto-generated method stub
		return this.myBorderPane; 
	}

}
