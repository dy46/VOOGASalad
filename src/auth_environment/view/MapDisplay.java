package auth_environment.view;

import auth_environment.backend.MapDisplayModel;
import game_engine.game_elements.GameElement;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Xander
 *
 * This class is for the main Map Display showing the current Map.
 * The Developer should be able to place Game Elements (as long as they are placeable).
 */

public class MapDisplay extends Pane {
	 
	// TODO: extract these values
	private static double mapWidth = 400;
	private static double mapHeight = 400; 
	private static int numX = 10;
	private static int numY = 10;
	
	private MapDisplayModel myModel;
	private Grid myGrid;
	
	public MapDisplay() {
		this.setPrefSize(this.mapWidth, this.mapHeight);
		myModel = new MapDisplayModel(numX, numY);
		myGrid = new Grid(myModel, mapWidth, mapHeight);
		this.getChildren().add(myGrid.getPane());
	}
	
	
    public void displayElement(GameElement element) {
    	
    }

}
