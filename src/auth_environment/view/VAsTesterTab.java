package auth_environment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.view.Menus.ElementMenu;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VAsTesterTab {

	
	public VAsTesterTab(TabPane myTabs){
		ElementMenu elmen = new ElementMenu();
		TowerView tview = new TowerView();
		Tab elTabo = new Tab("WOOO");
		myTabs.getTabs().add(elTabo);
		
        GridPane myGridPane = new GridPane();
        myGridPane.getStyleClass().add("myGridPane");

        Button myGoButton = new Button("Create New Tower");
        myGoButton.setOnAction(e -> elmen.createNewTower());
        
        Button myGoButton2 = new Button("Create New Enemy");
        myGoButton2.setOnAction(e -> doNothing());
        
        Button myGoButton3 = new Button("Create New Terrain");
        myGoButton3.setOnAction(e -> elmen.createNewTerrain());
        
        Button butt = new Button("HERE IS MY PICKER");
        butt.setOnAction(e -> tview.show());
        
        List<Node> myButtonsList =
                new ArrayList<>(Arrays
                        .asList(myGoButton, myGoButton2, myGoButton3));
        myGridPane.add(makeBox(new HBox(), "WHAT AM I DOING", myButtonsList, false),
                0, 0);
        
        List<Node> myTView = new ArrayList<>(Arrays.asList(butt, new Button("huehuheu")));
 
        myGridPane.add(makeBox(new VBox(), "WERERK", myTView, false), 0, 1);
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
}

