package auth_environment.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Created by BrianLin on 4/6/16.
 * 
 * Team member responsible: Brian
 * 
 * First (interactive) screen displayed to the Developer. Asks for Game name. 
 */

public class Welcome {
	
	private BorderPane myRoot = new BorderPane(); 
	
	public Welcome() {
		
	}
	
	private VBox buildCenter() {
		
	}
	
	public Node getRoot() {
		return this.myRoot;
	}

}
