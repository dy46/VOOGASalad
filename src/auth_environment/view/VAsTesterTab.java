package auth_environment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.backend.ISelector;
import auth_environment.view.Menus.ElementMenu;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VAsTesterTab {

	
	public VAsTesterTab(TabPane myTabs){
//		int w = 50;
//		int h = 50;
//		
//		   ScrollPane scroll = new ScrollPane();
//		   scroll.setFitToWidth(true);
//		   //scroll.setFitToHeight(true);
//		   FlowPane myV = new FlowPane(Orientation.VERTICAL);
//		   myV.setVgap(4);
//		   myV.setHgap(4);
//		   scroll.setContent(myV);
//		  // myV.setAlignment(Pos.TOP_RIGHT);
//		   myV.setPadding(new Insets(10,10,10,10));
//		   myV.setPrefWrapLength(210);
////	   myV.setPrefSize(100, 100);
//		   myV.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
//		   myV.setStyle("-fx-background-color:pink;-fx-padding:10px;");
//
//	 	   ImageView image = new ImageView();
//	 	   image.setImage(new Image("pusheenNoodles.gif"));	
//	 	   image.setFitHeight(w);
//	 	   image.setFitWidth(h);
//	 	   image.setOnMouseClicked(e -> System.out.println("wrwer"));
//	 	   
//	 	   ImageView image2 = new ImageView();
//	 	   image2.setImage(new Image("unicornCat.gif"));
//	 	   image2.setFitHeight(w);
//	 	   image2.setFitWidth(h);
//	 	   
//	 	   ImageView image3 = new ImageView();
//	 	   image3.setImage(new Image("dj.gif"));
//	 	   image3.setFitHeight(w);
//	 	   image3.setFitWidth(h);
//	 	   
//	 	   ImageView image4 = new ImageView();
//	 	   image4.setImage(new Image("coffeeBackground.gif"));
//	 	   image4.setFitHeight(w);
//	 	   image4.setFitWidth(h);
//	 	   
//	 	   ImageView image5 = new ImageView();
//	 	   image5.setImage(new Image("catKeyboard.gif"));
//	 	   image5.setFitHeight(w);
//	 	   image5.setFitWidth(h);
//	 	   
//	 	   myV.getChildren().addAll(image, image2, image3, image4, image5);
	 	   
	 	   
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
        //myGridPane.add(myV, 0, 2);
        myGridPane.add(new Button("WEREWRE"), 0, 3);
        myGridPane.add(new Button("ack"), 1, 2);
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

