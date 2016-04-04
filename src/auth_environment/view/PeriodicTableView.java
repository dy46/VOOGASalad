package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.layout.VBox;

/**
 * Created by BrianLin on 4/1/16.
 * Team member responsible: Brian
 *
 * This class displays the Game Elements held in the PeriodicTable. 
 * We'll try out Composition and have this hold an instance of the PeriodicTable. 
 * 
 * This will be a VBox with several rows (HBoxes). Each row should be scroll-able to reveal more elements. 
 */

public class PeriodicTableView extends VBox {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	public PeriodicTableView() {
		this.myNodeFactory.setupVBox(this, 
									 myNamesBundle.getString("periodicTableTitle"), 
									 myNodeFactory.titleFont(), 
									 Double.parseDouble(myDimensionsBundle.getString("periodicTableSpacing")), 
									 Double.parseDouble(myDimensionsBundle.getString("periodicTablePadding"))
									 );
	}
	
	private void clearChildren() {
		this.getChildren().clear();
	}
}
