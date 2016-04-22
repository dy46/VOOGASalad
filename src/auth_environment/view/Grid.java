package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.backend.MapDisplayModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.paths.PathGraphFactory;
import game_engine.properties.Position;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grid{
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	private MapDisplayModel myModel;
	int numX;
	int numY;
	double mapWidth;
	double mapHeight;
	private Pane myPane;
	private List<Tile> myTiles= new ArrayList<Tile>();
	private PathGraphFactory myPathGraphFactory;
	
	public Grid(MapDisplayModel model, double mapWidth, double mapHeight) {
		myPathGraphFactory = new PathGraphFactory();
		this.myModel = model;
		this.myPane = new Pane();
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.numX = model.getXmax();
		this.numY = model.getYmax();
		this.generateGrid();
	}
	
	public PathGraphFactory getPathGraphFactory(){
		return myPathGraphFactory;
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
				myModel.addElement(myTile, i, j);
				DragDelegate drag = new DragDelegate(); 
//				drag.setupTarget(myTile);
				myTiles.add(myTile);
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
	
	public List<Position> clickedList(){
		ArrayList<Position> ans = new ArrayList<Position>();
		for(Map.Entry<Position, Tile> entry : myModel.myMap.entrySet()){
			if(((RecTile)(entry.getValue())).isPath){
				ans.add(entry.getKey());
			}
		}
		return ans;
	}
	
	public List<Tile> getTiles() {
		return this.myTiles;
	}
	
}
