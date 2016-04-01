package auth_environment.view;

import auth_environment.backend.NodeFactory;
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
	
	// TODO: extract these Strings
	private static String periodicTableLabel = "Periodic Table of (Game) Elements";
	private static int periodicTableSpacing = 10;
	private static int periodicTablePadding = 10; 
	
	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	public PeriodicTableView() {
		this.myNodeFactory.setupVBox(this, 
									 this.periodicTableLabel, 
									 myNodeFactory.titleFont(), 
									 this.periodicTableSpacing, 
									 this.periodicTablePadding);
	}
	
	private void clearChildren() {
		this.getChildren().clear();
	}
}
