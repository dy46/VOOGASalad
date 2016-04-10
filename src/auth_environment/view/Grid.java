package auth_environment.view;

import java.util.ArrayList;
import java.util.List;

import auth_environment.backend.ISelector;
import auth_environment.backend.MapDisplayModel;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grid{
	
	private ISelector mySelector; 
	private MapDisplayModel myModel;
	int numX;
	int numY;
	double mapWidth;
	double mapHeight;
	private Pane myPane;
	List<RecTile> myTiles= new ArrayList<RecTile>();
	
	public Grid(MapDisplayModel model, double mapWidth, double mapHeight) {
		this.myModel = model;
		this.mySelector = myModel.getSelector();
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
				RecTile myTile = new RecTile(this.mySelector,
											 i,
											 j,
											 i*recWidth, 
											 j*recHeight, 
											 recWidth, 
											 recHeight);
				myTile.setStroke(Color.BLACK);
				myTile.setFill(Color.WHITE);
				myPane.getChildren().add(myTile);
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
