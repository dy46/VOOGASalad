package auth_environment.delegatesAndFactories;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IMapPane;
import javafx.scene.layout.Pane;

public class FreeMapPane extends Pane implements IMapPane{
	
	private int adjustFactor;
	private MapEditorTabModel myModel;

	public FreeMapPane(MapEditorTabModel model) {
		this.myModel = model;
		this.adjustFactor = 10;
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
		System.out.println("Added" + " X: " + uv.getX() + " Y: " + uv.getY());
	}
	
	public Pane getRoot(){
		return this;
	}
	
}