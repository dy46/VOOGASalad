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
		myModel = model;
		myPane = new Pane();
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		numX = model.getXmax();
		numY = model.getYmax();
		generateGrid();
	}
	
	public void generateGrid(){
		double recWidth = calcRecWidth();
		double recHeight = calcRecHeight();
		myPane.setPrefSize(mapWidth, mapHeight);
		for(int i=0; i<myModel.getXmax(); i++){
			for(int j=0; j<myModel.getYmax(); j++){
				RecTile myTile = new RecTile(i*recWidth, j*recHeight, recWidth, recHeight);
				myTile.setStroke(Color.BLACK);
				myTile.setFill(Color.WHITE);
				myPane.getChildren().add(myTile);
			}
		}
		
	}
	
	public Pane getPane(){
		return myPane;
	}
	public double calcRecWidth(){
		return (mapWidth/numX);
	}
	 
	public double calcRecHeight(){
		return (mapHeight/numY);
	}
	

}
