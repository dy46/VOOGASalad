package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.Grid;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PickerMenu extends Accordion {

	public PickerMenu() {
		this.getPanes().addAll(defaultPanes());
		// TODO Auto-generated constructor stub
	}

	public PickerMenu(TitledPane... titledPanes) {
		super(titledPanes);
		// TODO Auto-generated constructor stub
	}
	
	private List<TitledPane> defaultPanes() {
		TitledPane myTowers = new TitledPane();
		   int w = 50;
		   int h = 50;
		
		   FlowPane myV = new FlowPane();
		   myV.setVgap(4);
		   myV.setHgap(4);
		   myV.setPrefWrapLength(300);
		   myV.setPrefSize(300,300);
		   myV.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	 	   myV.setStyle("-fx-background-color:pink;-fx-padding:10px;");

	 	   ImageView image = new ImageView();
	 	   image.setImage(new Image("pusheenNoodles.gif"));	
	 	   image.setFitHeight(w);
	 	   image.setFitWidth(h);
	 	   //image.setOnMouseClicked(e -> mySelector.chooseElement(0));
	 	   DragDelegate drag = new DragDelegate();
	 	   
	 	   
	 	   ImageView image2 = new ImageView();
	 	   image2.setImage(new Image("unicornCat.gif"));
	 	   image2.setFitHeight(w);
	 	   image2.setFitWidth(h);
	 	  
	 	   ImageView image3 = new ImageView();
	 	   image3.setImage(new Image("dj.gif"));
	 	   image3.setFitHeight(w);
	 	   image3.setFitWidth(h);
	 	   //image.setOnMouseClicked(e -> mySelector.chooseElement(2));
	 	   
	 	   ImageView image4 = new ImageView();
	 	   image4.setImage(new Image("coffeeBackground.gif"));
	 	   image4.setFitHeight(w);
	 	   image4.setFitWidth(h);
	 	   //image.setOnMouseClicked(e -> mySelector.chooseElement(3));
	 	   
	 	   ImageView image5 = new ImageView();
	 	   image5.setImage(new Image("catKeyboard.gif"));
	 	   image5.setFitHeight(w);
	 	   image5.setFitWidth(h);
	 	   //image.setOnMouseClicked(e -> mySelector.chooseElement(4));
	 	   
	 	   
	 	   myV.getChildren().addAll(image, image2, image3, image4, image5);
	 	   
//		GridPane grid = new GridPane();
//		grid.setVgap(4);
//		grid.setPadding(new Insets(5, 5, 5, 5));
//		grid.add(new Label("Health: "), 0, 0);
//		grid.add(new TextField(), 1, 0);
//		grid.add(new Label("Coordinates: "), 0, 1);
//		grid.add(new TextField(), 1, 1);
//		grid.add(new Label("Reward "), 0, 2);
//		grid.add(new TextField(), 1, 2); 
//		myTowers.setText("Towers");
//		myTowers.setContent(grid);
	 	   myTowers.setContent(myV);
		
		TitledPane myEnemies = new TitledPane();
		myEnemies.setText("Enemies");
		
		TitledPane myTerrains = new TitledPane();
		myTerrains.setText("Terrains");
		
		Text hello = new Text("Hello"); 
		drag.setupSource(hello);
		myTerrains.setContent(hello);
		
		List<TitledPane> myPanes = Arrays.asList(myTowers,myEnemies,myTerrains);
		return myPanes; 
	}
	

}
