package auth_environment.view.Menus;

import java.util.ResourceBundle;

import auth_environment.view.PeriodicTableView;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 */
public class ElementMenu extends Menu {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public ElementMenu() {
		this.init();
	}
	
	private void init() {
		this.setText(this.myNamesBundle.getString("elementMenuLabel"));
		MenuItem createItem = new MenuItem(this.myNamesBundle.getString("createElementLabel"));
		createItem.setOnAction(e -> this.createTower());
		MenuItem periodicTableItem = new MenuItem(this.myNamesBundle.getString("periodicTableLabel"));
		periodicTableItem.setOnAction(e -> viewPeriodicTable()); 
		this.getItems().addAll(createItem, periodicTableItem); 
	}
	
	private void createTower() {
		
	}
	
	// TODO: we should pass in an instance of PeriodicTable to anything that reads or modifies it
	
	private void viewPeriodicTable() {
		Stage stage = new Stage(); 
		stage.setTitle(this.myNamesBundle.getString("periodicTableLabel"));
        stage.setScene(new Scene(new PeriodicTableView(), 
        			   Double.parseDouble(this.myDimensionsBundle.getString("periodicTableWidth")),
        			   Double.parseDouble(this.myDimensionsBundle.getString("periodicTableHeight"))
        			   ));
        stage.show();
	}
}
