package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.layout.VBox;

public class ElementCell extends VBox {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private String myName = myNamesBundle.getString("defaultElementName");
	private String myImageName = myURLSBundle.getString("defaultImageName");
	
	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	public ElementCell(String name, String imageName) {
		this.myName = name;
		this.myImageName = imageName;
		this.init();
	}
	
	private void init() {
		this.myNodeFactory.setupVBox(this, 
									 this.myName, 
									 myNodeFactory.defaultFont(), 
									 Double.parseDouble(myDimensionsBundle.getString("elementCellSpacing")), 
									 Double.parseDouble(myDimensionsBundle.getString("elementCellPadding"))
									 );
		this.myNodeFactory.addImageView(this, this.myImageName);
	}
	
}
