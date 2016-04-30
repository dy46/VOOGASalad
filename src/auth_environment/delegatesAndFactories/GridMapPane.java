package auth_environment.delegatesAndFactories;

import java.util.HashMap;
import java.util.Map;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IMapPane;
import game_engine.properties.Position;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GridMapPane extends GridPane implements IMapPane {
	
	private int numCols;
	private int numRows;
	private MapEditorTabModel myModel;
	private Map<UnitView, Position> myMap;
	public GridMapPane(MapEditorTabModel model) {
		this.myModel = model;
		myMap = new HashMap<UnitView, Position>();
	}
	

	public void adjustUnitViewScale(UnitView uv){
		uv.setFitWidth(this.getHeight()/numCols-3);
		uv.setFitHeight(this.getWidth()/numRows-3);
		System.out.println(uv.getFitHeight());
	}
	

//	public void adjustUnitViewXY(UnitView uv, double x, double y){
//		uv.setX((int)(x/(this.getRoot().getWidth()/gridWidth)));
//		uv.setY((int)((y-uv.getFitHeight())/(this.getRoot().getHeight()/gridWidth)));
//	}
	
	public void setNumColsRows(int cols, int rows){
		this.numCols = cols;
		this.numRows = rows;
	    for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            this.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);         
        }
	}
	

	public MapEditorTabModel getModel(){
		return myModel;
	}
	

	public void addToPane(UnitView uv){
		this.add(uv, convertToColumn(uv.getX()), convertToRow(uv.getY()));
		this.myMap.put(uv, new Position(uv.getX(), uv.getY()));
	}
	
	public Map<UnitView, Position> getMap(){
		return this.myMap;
	}
	private int convertToColumn(double x){
		return ((int)(x/(this.getWidth()/numCols)));
	}
	
	private int convertToRow(double y){
		return ((int)(y/(this.getHeight()/numRows)));
	}


	public GridPane getRoot(){
		return this;
	}
	
	public void setModel(MapEditorTabModel model){
		this.myModel = model;
	}


	public void adjustUnitViewXY(UnitView uv, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
}
