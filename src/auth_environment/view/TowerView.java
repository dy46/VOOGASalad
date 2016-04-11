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
public class TowerView implements UnitView {

	private GridPane myGridPane;
    
	@Override
	public void show() {
		// TODO Auto-generated method stub
		int w = 100;
		int h = 100;
		
		
		   FlowPane myV = new FlowPane(Orientation.VERTICAL);
		   myV.setVgap(8);
		   myV.setHgap(4);
		   myV.setPrefWrapLength(300);
		   myV.setPrefSize(300, 200);
		   myV.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
	 	   myV.setStyle("-fx-background-color:pink;-fx-padding:10px;");

	 	   ImageView image = new ImageView();
	 	   image.setImage(new Image("pusheenNoodles.gif"));	
	 	   image.setFitHeight(w);
	 	   image.setFitWidth(h);
	 	   image.setOnMouseClicked(e -> clicked());
	 	   
	 	   ImageView image2 = new ImageView();
	 	   image2.setImage(new Image("unicornCat.gif"));
	 	   image2.setFitHeight(w);
	 	   image2.setFitWidth(h);
	 	   
	 	   ImageView image3 = new ImageView();
	 	   image3.setImage(new Image("dj.gif"));
	 	   image3.setFitHeight(w);
	 	   image3.setFitWidth(h);
	 	   
	 	   ImageView image4 = new ImageView();
	 	   image4.setImage(new Image("coffeeBackground.gif"));
	 	   image4.setFitHeight(w);
	 	   image4.setFitWidth(h);
	 	   
	 	   ImageView image5 = new ImageView();
	 	   image5.setImage(new Image("catKeyboard.gif"));
	 	   image5.setFitHeight(w);
	 	   image5.setFitWidth(h);
	 	   
	 	   
	 	   
	 	   
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

	private void clicked() {
		System.out.println("werwerwerwerk");
	}

	@Override
	public GameElement build() {
		// TODO Auto-generated method stub
		return null;
	}

}
