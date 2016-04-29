package auth_environment.Models;

import auth_environment.Models.Interfaces.IUnitView;
import auth_environment.delegatesAndFactories.MapPane;
import auth_environment.delegatesAndFactories.NodeFactory;
import game_engine.game_elements.Unit;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * The Auth Environment's way of displaying a Unit. 
 * Contains both the Unit and an ImageView to be displayed.
 * 
 *  DO NOT write this to XML
 * 
 */

public class UnitView extends ImageView implements IUnitView {
	
	private ContextMenu myContextMenu;
	private NodeFactory myNodeFactory = new NodeFactory(); 
	private Unit myUnit;
	
	public UnitView() {
		super();
	}
	
	public UnitView(Image image) {
		super(image); 
	}
	
	public UnitView(String imageName) {
		this.setImage(this.myNodeFactory.buildImage(imageName));
	}
	
	public UnitView(Unit unit) {
		super();
		this.myUnit = unit;
		this.setImage(this.myNodeFactory.buildImage(unit.toString()));
	}
	
	public UnitView(Unit unit, Image image) {
		super(image); 
		this.myUnit = unit; 
	}
	
	public UnitView(Unit unit, String imageName) {
		this.myUnit = unit;
		this.setImage(this.myNodeFactory.buildImage(imageName));
	}
	
	public UnitView clone(){
		UnitView uv = new UnitView(this.myUnit, this.getImage());
		return uv;
	}
	@Override
	public void setUnit(Unit unit) {
		this.myUnit = unit; 
	}

	@Override
	public Unit getUnit() {
		return this.myUnit;
	}
	
	public void setScale(MapPane target, int gridWidth){
		target.adjustUnitViewScale(this);
		this.setFitHeight(target.getHeight()/gridWidth-3);
		this.setFitWidth(target.getWidth()/gridWidth-3);
	}
	
	public void addContextMenu(MapPane target, UnitView uv){
		this.addEventHandler(MouseEvent.MOUSE_CLICKED,
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            if (e.getButton() == MouseButton.SECONDARY){ 
			            	myContextMenu = buildContextMenu(target);
			            	 myContextMenu.show(uv, e.getScreenX(), e.getScreenY());
			            }
			        }
			});
	}
	
	 private ContextMenu buildContextMenu(MapPane tempPane){
	    	ContextMenu cm = new ContextMenu();
	    	MenuItem cmItem1 = new MenuItem("Delete Image");
	    	cmItem1.setOnAction(e-> {tempPane.getChildren().remove(this);
	    	tempPane.getModel().deleteTerrain(this.getUnit());
	    	});
	    	cm.getItems().add(cmItem1);
	    	return cm;
	    }

}
