package auth_environment.view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.view.Menus.ElementMenu;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This is the most general frontend/view class and contains a reference to the main Stage and tabs. 
 */

public class View {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	
	
	
    private Stage myStage;
    private Scene myScene; 
    private TabPane myTabs = new TabPane();
    private Workspace mainWorkspace;

    public View (Stage stage) {
        myStage = stage;
        myScene = new Scene(myTabs, Color.LIGHTGRAY); 
        myScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet")); // TODO: allow Developer to toggle stylesheets
        myStage.setScene(myScene);
		myStage.setTitle(myNamesBundle.getString("wompTitle"));
		mainWorkspace = new Workspace(myTabs);
		Tab mainTab = new Tab(myNamesBundle.getString("mainTabTitle"));
		mainTab.setClosable(false);
		myTabs.getTabs().add(mainTab);
		
		
		//stuff I've added to test
		ElementMenu elmen = new ElementMenu();
		Tab elTabo = new Tab("WOOO");
		myTabs.getTabs().add(elTabo);
		
        GridPane myGridPane = new GridPane();
        myGridPane.getStyleClass().add("myGridPane");

        Button myGoButton = new Button("Create New Tower");
        myGoButton.setOnAction(e -> elmen.createNewTower());
        
        Button myGoButton2 = new Button("Create New Enemy");
        myGoButton2.setOnAction(e -> doNothing());
        
        Button myGoButton3 = new Button("Create New Terrain");
        myGoButton3.setOnAction(e -> doNothing());
        
        List<Node> myButtonsList =
                new ArrayList<>(Arrays
                        .asList(myGoButton, myGoButton2, myGoButton3));
        myGridPane.add(makeBox(new HBox(), "WHAT AM I DOING", myButtonsList, false),
                2, 6, 3, 6);
        
		elTabo.setContent(myGridPane);
		// 99% sure everyone else can ignore those
		

    }

    private void doNothing(){
    	System.out.println("NOTHING");
    }
    
    private Node makeBox (Pane box, String cssID, List<Node> items, Boolean scrollable) {
        Pane myBox = box;
        myBox.getStyleClass().add(cssID);
        myBox.getChildren().addAll(items);
        if (scrollable) {
            ScrollPane scroll = new ScrollPane(myBox);
            scroll.setMaxHeight(510);
            scroll.setMinWidth(300);
            return scroll;
        }
        return myBox;
    }

    public void display() {
    	this.myStage.show();
    }

}
