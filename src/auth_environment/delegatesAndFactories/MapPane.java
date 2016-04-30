package auth_environment.delegatesAndFactories;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MapPane extends Pane{
	
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
		uv.setFitWidth(this.getRoot().getHeight()/adjustFactor);
		uv.setFitHeight(this.getRoot().getWidth()/adjustFactor);
	}
	
	public void adjustUnitViewXY(UnitView uv, double x, double y){
		uv.setX(x);
		uv.setY(y - uv.getFitHeight());
	}
	
	public MapEditorTabModel getModel(){
		return myModel;
	}
	
	public void addEverything(){
		myModel.getAllTerrains().stream().forEach(u -> {
			UnitView uv = new UnitView(u, u.toString() + ".png");
			uv.setX(u.getProperties().getPosition().getX());
			uv.setY(u.getProperties().getPosition().getY());
			System.out.println(u.getProperties().getPosition().getX());
			this.addToPane(uv);
		});
	}
	
	public void addToPane(UnitView uv){
		this.getChildren().add(uv);
		System.out.println("Added" + " X: " + uv.getX() + " Y: " + uv.getY());
	}
	
//	public void remove(UnitView uv){
//		this.getChildren().remove(uv);
//	}
	public Pane getRoot(){
		return this;
	}
	
}
