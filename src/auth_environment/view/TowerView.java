package auth_environment.view;

import game_engine.game_elements.GameElement;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: va
 */
public class TowerView implements IElementView {

	private GridPane myGridPane;
    
	@Override
	public void show() {
		// TODO Auto-generated method stub
		int w = 50;
		int h = 50;
		
		
		   FlowPane myV = new FlowPane();
		   myV.setVgap(4);
		   myV.setHgap(4);
		   myV.setPrefWrapLength(100);
		   myV.setPrefSize(100,100);
		   myV.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	 	   myV.setStyle("-fx-background-color:pink;-fx-padding:10px;");

	 	   ImageView image = new ImageView();
	 	   image.setImage(new Image("pusheenNoodles.gif"));	
	 	   image.setFitHeight(w);
	 	   image.setFitWidth(h);
//	 	   image.setOnMouseClicked(e -> mySelector.chooseElement(0));
	 	   
	 	   ImageView image2 = new ImageView();
	 	   image2.setImage(new Image("unicornCat.gif"));
	 	   image2.setFitHeight(w);
	 	   image2.setFitWidth(h);
//	 	   image.setOnMouseClicked(e -> mySelector.chooseElement(1));
	 	   
	 	   ImageView image3 = new ImageView();
	 	   image3.setImage(new Image("dj.gif"));
	 	   image3.setFitHeight(w);
	 	   image3.setFitWidth(h);
//	 	   image.setOnMouseClicked(e -> mySelector.chooseElement(2));
	 	   
	 	   ImageView image4 = new ImageView();
	 	   image4.setImage(new Image("coffeeBackground.gif"));
	 	   image4.setFitHeight(w);
	 	   image4.setFitWidth(h);
//	 	   image.setOnMouseClicked(e -> mySelector.chooseElement(3));
	 	   
	 	   ImageView image5 = new ImageView();
	 	   image5.setImage(new Image("catKeyboard.gif"));
	 	   image5.setFitHeight(w);
	 	   image5.setFitWidth(h);
//	 	   image.setOnMouseClicked(e -> mySelector.chooseElement(4));
	 	   
	 	   myV.getChildren().addAll(image, image2, image3, image4, image5);
	 	   Scene myScene = new Scene(myV, 200, 100);
	 	   Stage newStage = new Stage();
	 	   newStage.setScene(myScene);
	 	   newStage.setMinWidth(450.0);
	 	   newStage.setMinHeight(450.0);
	 	   //newStage.resizableProperty().set(false);
	 	   newStage.setTitle("Pick it up");
	 	   newStage.show();
		
	}


	@Override
	public GameElement build() {
		// TODO Auto-generated method stub
		return null;
	}

}
