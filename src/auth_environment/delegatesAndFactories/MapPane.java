package auth_environment.delegatesAndFactories;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MapPane extends Pane {
	
	private int adjustFactor;
	private MapEditorTabModel myModel;

	public MapPane(MapEditorTabModel model) {
		// TODO Auto-generated constructor stub
		this.myModel = model;
		this.adjustFactor = 10;
	}

	public MapPane(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}
	
	public void adjustUnitViewScale(UnitView uv){
		uv.setFitWidth(this.getHeight()/adjustFactor);
		uv.setFitHeight(this.getWidth()/adjustFactor);
	}
	
	public void adjustUnitViewXY(UnitView uv, double x, double y){
		uv.setX(x);
		uv.setY(y - uv.getFitHeight());
	}
	
	public MapEditorTabModel getModel(){
		return myModel;
	}
	
	public void addToPane(UnitView uv){
		this.getChildren().add(uv);
	}
	
//	public void remove(UnitView uv){
//		this.getChildren().remove(uv);
//	}
	public Pane getRoot(){
		return this;
	}
	
}