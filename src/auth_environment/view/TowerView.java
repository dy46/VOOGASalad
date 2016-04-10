package auth_environment.view;

import game_engine.game_elements.GameElement;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
		   FlowPane myV = new FlowPane();
	 	   myV.setStyle("-fx-background-color:pink;-fx-padding:10px;");
	 	   ImageView image = new ImageView();
	 	   image.setImage(new Image("pusheenNoodles.gif"));	
	 	   image.setFitHeight(150.0);
	 	   image.setFitWidth(150.0);
	 	   ImageView image2 = new ImageView();
	 	   image2.setImage(new Image("unicornCat.gif"));
	 	   image2.setFitHeight(150.0);
	 	   image2.setFitWidth(150.0);
	 	   myV.getChildren().addAll(image, new Button("womp"), new Button("ye"), new Button("idk"), image2, new Button("WRWERWEG"));
	 	   Scene myScene = new Scene(myV, 200, 100);
	 	   Stage newStage = new Stage();
	 	   newStage.setScene(myScene);
	 	   newStage.setMinWidth(350.0);
	 	   newStage.setMinHeight(350.0);
	 	   newStage.setTitle("Pick it up");
	 	   newStage.show();
		
	}

	@Override
	public GameElement build() {
		// TODO Auto-generated method stub
		return null;
	}

}
