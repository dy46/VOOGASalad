package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.backend.IElementHolder;
import auth_environment.backend.ISelector;
import auth_environment.delegatesAndFactories.NodeFactory;
import game_engine.game_elements.GameElement;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Team member responsible: Xander
 * Modifications from: Brian
 *
 * This represents the smallest unit of the Map Display. This tile consists of several layers: empty (blank), Terrain
 * and GameElement (stacked with GameElements on top). 
 * 
 * The key action is that on mouse click. 
 */

public class RecTile extends Rectangle implements IElementHolder {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private double x;
	private double y; 
	public boolean hasImage;
	
	private GameElement myElement; 
	
	private ISelector mySelector; 
	
	public RecTile(ISelector selector, double x, double y, double scaledX, double scaledY, double width, double height) {
		super(scaledX, scaledY, width, height);
		this.x = x;
		this.y = y; 
		this.mySelector = selector; 
		this.addListener();
		this.hasImage = false;
	}

	private void addListener() {
		this.setOnMouseClicked(e -> this.recTileAction());
	}
	
	private void recTileAction() {
		this.placeTower();
		mySelector.choosePosition(this.x, this.y);
		mySelector.printPosition();
	}
	
	@Override
	public void updateElement(GameElement element) {
		this.myElement = element; 
		// TODO: eventually call this.setImage(element.getImage()) or something like that
	}

	@Override
	public GameElement getElement() {
		return this.myElement;
	}
	
	public void placeTower() {
		NodeFactory nf = new NodeFactory(); 
		this.setImage(nf.buildImage(myNamesBundle.getString("tower")));
		this.hasImage = true;
		System.out.println("Placing tower...");
	}
	
	public void showTower(){
		NodeFactory nf = new NodeFactory(); 
		this.setImage(nf.buildImage(myNamesBundle.getString("tower")));
		System.out.println("Placing tower...");
	}
	public void setImage(Image image) {
		this.setFill(new ImagePattern(image));
	}
	
	public ImagePattern getImage(Image image){
		return new ImagePattern(image);
	}
	
}
