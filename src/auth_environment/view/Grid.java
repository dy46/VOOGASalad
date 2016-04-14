package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import auth_environment.backend.ISelector;
import auth_environment.backend.MapDisplayModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import game_engine.properties.Position;
import javafx.scene.input.KeyCode;
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
	List<RecTile> myTiles= new ArrayList<RecTile>();
	List<Position> temp = new ArrayList<Position>();
	int count;
	
	public Grid(MapDisplayModel model, double mapWidth, double mapHeight) {
		this.myModel = model;
		this.myPane = new Pane();
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.numX = model.getXmax();
		this.numY = model.getYmax();
		this.generateGrid();
		count = 1;
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
											 recHeight,
											 i,
											 j);
				myTile.getShape().setStroke(Color.BLACK);
				myTile.getShape().setFill(Color.WHITE);
				myTile.getShape().setOnMouseClicked(e->
				{recTileAction(myTile);
				myTile.setNum(count);
				count++;
				temp.add(new Position(myTile.x,myTile.y));
				}
				);
				myModel.addElement(myTile, i, j);
				DragDelegate drag = new DragDelegate(); 
				drag.setupTarget(myTile);
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
	public List<Position> clickedList2(){
		List<Position> ans = new ArrayList<Position>();
//		for(Map.Entry<Position, Tile> entry : myModel.myMap.entrySet()){
//			if(((RecTile)(entry.getValue())).isPath){
//				ans.add(entry.getKey());
//			}
//		}
		ans = myModel.myMap.entrySet().stream().filter(p->((RecTile)(p.getValue())).isPath).map(p -> p.getKey())
				.collect(Collectors.toList());
		return ans;
	}
	
	public List<Position> clickedList(){
		return temp;
	}
	
	private void recTileAction(RecTile tempTile) {
		tempTile.getShape().setFill(Color.GREEN);
		tempTile.isPath = true;
	}
}
