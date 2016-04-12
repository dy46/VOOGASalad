package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.backend.ISelector;
import auth_environment.backend.MapDisplayModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grid{
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private ISelector mySelector; 
	private MapDisplayModel myModel;
	int numX;
	int numY;
	double mapWidth;
	double mapHeight;
	private Pane myPane;
	List<Tile> myTiles= new ArrayList<Tile>();
	
	public Grid(MapDisplayModel model, double mapWidth, double mapHeight) {
		this.myModel = model;
		this.myPane = new Pane();
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.numX = model.getXmax();
		this.numY = model.getYmax();
		this.generateGrid();
	}
	
	public void generateGrid(){
		double recWidth = calcRecWidth();
		double recHeight = calcRecHeight();
		myPane.setPrefSize(mapWidth, mapHeight);
		for(int i=0; i<myModel.getXmax(); i++){
			for(int j=0; j<myModel.getYmax(); j++){
				RecTile myTile = new RecTile(i*recWidth, 
											 j*recHeight, 
											 recWidth, 
											 recHeight);
				myTile.getShape().setStroke(Color.BLACK);
				myTile.getShape().setFill(Color.WHITE);
				DragDelegate drag = new DragDelegate(); 
				drag.setupTarget(myTile);
				myPane.getChildren().add(myTile.getShape());
			}
		}
	}
	
	public Pane getRoot(){
		return myPane;
	}
	public double calcRecWidth(){
		return (mapWidth/numX);
	}
	 
	public double calcRecHeight(){
		return (mapHeight/numY);
	}
}
