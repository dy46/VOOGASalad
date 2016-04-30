package auth_environment.view.tabs;

import java.util.ResourceBundle;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class OkPane {
	private GridPane myPane;
	
	public OkPane(AnimationPane animation){
		
	}
	
	public OkPane(String name){
		
	}
	
	
	private Pane setUpPane(ResourceBundle myDimensionsBundle, String s){
		GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s+1))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s+2))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
		return gridPane;
	}

}
