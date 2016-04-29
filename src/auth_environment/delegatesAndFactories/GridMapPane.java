package auth_environment.delegatesAndFactories;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GridMapPane extends MapPane {
	
	private GridPane myPane;
	private int gridWidth;
	private MapEditorTabModel myModel;
	
	public GridMapPane(MapEditorTabModel model) {
		this.myModel = model;
		myPane = new GridPane();
		gridWidth = 10;
	}

	public GridMapPane(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void adjustUnitViewScale(UnitView uv){
		uv.setFitWidth(this.getRoot().getHeight()/gridWidth-3);
		uv.setFitHeight(this.getRoot().getWidth()/gridWidth-3);
		System.out.println(uv.getFitHeight());
	}
	

//	public void adjustUnitViewXY(UnitView uv, double x, double y){
//		uv.setX((int)(x/(this.getRoot().getWidth()/gridWidth)));
//		uv.setY((int)((y-uv.getFitHeight())/(this.getRoot().getHeight()/gridWidth)));
//	}
	
	public void setGridWidth(int gw){
		this.gridWidth = gw;
	}
	
	@Override
	public MapEditorTabModel getModel(){
		return myModel;
	}
	
	@Override
	public void addToPane(UnitView uv){
		myPane.add(uv, convertToColumn(uv.getX()), convertToRow(uv.getY()));
	}
	
	private int convertToColumn(double x){
		return ((int)(x/(this.getRoot().getWidth()/gridWidth)));
	}
	
	private int convertToRow(double y){
		return ((int)(y/(this.getRoot().getHeight()/gridWidth)));
	}

	@Override
	public GridPane getRoot(){
		return this.myPane;
	}
	
	public void setModel(MapEditorTabModel model){
		this.myModel = model;
	}
	
}
