package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.backend.IElementHolder;
import auth_environment.backend.ISelector;
import auth_environment.delegatesAndFactories.NodeFactory;
import game_engine.factories.TerrainFactory;
import game_engine.game_elements.GameElement;
import game_engine.properties.Position;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Team member responsible: Xander
 * Modifications from: Brian
 *
 * This represents the smallest unit of the GUI. Each Tile holds a single Game Element (or none at all).
 * 
 * Dragging a Game Element onto this Tile will update the Game Element of this tile.
 * 
 * If this is a Map tile, this can be clicked on to set Path (or some other feature). 
 */

public abstract class Tile implements IElementHolder {
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private boolean hasElement; 
	private GameElement myElement; 
	private String name;

	public Tile() {
		hasElement = false;
	}
	
	public abstract Shape getShape();
	
	protected abstract void addListener();
	
	@Override
	public void updateElement(GameElement element) {
		this.hasElement = true;
		this.myElement = element; 
		this.setName(element.toString());
		//System.out.println(element.toString());
		showCurrentElement();
	}

	@Override
	public GameElement getElement() {
		return this.myElement;
	}
	
	public void showCurrentElement() {
		NodeFactory nf = new NodeFactory(); 
//		this.setImage(nf.buildImage(this.myElement.toString()));
		this.setImage(nf.buildImage(this.name));
//		this.setImage(nf.buildImage(myNamesBundle.getString("tower")));
	}
	
	public void placeCurrentElement() {
		this.hasElement = true;
		this.showCurrentElement();
	}
	
	protected abstract void setImage(Image image); 
	
	public void clear() {
		this.getShape().setFill(Color.WHITE);
		this.myElement = null;
		this.hasElement = false; 
	}
	
	public boolean hasElement() {
		return this.hasElement; 
	}
	
	public void chooseAndPrint(){
		this.getShape().setFill(Color.GREEN);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
