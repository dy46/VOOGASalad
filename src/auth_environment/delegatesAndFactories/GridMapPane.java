package auth_environment.delegatesAndFactories;

import auth_environment.Models.UnitView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GridMapPane extends MapPane {
	
	private GridPane myPane;
	private int gridWidth;
	public GridMapPane() {
		myPane = new GridPane();
		myPane.setGridLinesVisible(true);
		gridWidth = 10;
	}

	public GridMapPane(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void adjustUnitViewScale(UnitView uv){
		uv.setFitWidth(this.getHeight()/gridWidth-3);
		uv.setFitHeight(this.getWidth()/gridWidth-3);
	}
	
	@Override
	public void adjustUnitViewXY(UnitView uv, double x, double y){
		uv.setX((int)(x/(this.getWidth()/gridWidth)));
		uv.setY((int)((y-uv.getFitHeight())/(this.getHeight()/gridWidth)));
	}
	
	public void setGridWidth(int gw){
		this.gridWidth = gw;
	}
	
	public void addToPane(UnitView uv){
		myPane.add(uv, (int)uv.getX(), (int)uv.getY());
	}
	
	public GridPane getRoot(){
		return this.myPane;
	}
	
}
