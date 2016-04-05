package auth_environment.delegatesAndFactories;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class ScrollingHBoxDelegate extends BorderPane {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private NodeFactory myNodeFactory = new NodeFactory(); 
	private HBox hbox = new HBox();
	
	public ScrollingHBoxDelegate(String name) {

//	    Button b = new Button("add");
//	    b.setOnAction(ev -> hbox.getChildren().add(new Label("Test")));
		
		this.addItem(myNodeFactory.makeLabel(name, myNodeFactory.defaultFont()));
		
	    ScrollPane scrollPane = new ScrollPane(hbox);
	    scrollPane.setFitToHeight(true);

	    this.getChildren().add(scrollPane);
	    this.setPadding(new Insets(Double.parseDouble(myDimensionsBundle.getString("scrollingHBoxInsets"))));
//	    this.setTop(b);
	   	}
	
	public void addItem(Node node) {
		this.hbox.getChildren().add(node); 
	}
	
	
}
