package auth_environment.view.Menus;

import java.io.File;

import auth_environment.view.PeriodicTableView;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 */
public class ElementMenu extends Menu {
	
	// TODO: extract these constants
	private static String elementMenuLabel = "Elements";
	private static String createElementLabel = "Create New Element"; 
	private static String periodicTableLabel = "Periodic Table";
	
	public ElementMenu() {
		this.init();
	}
	
	private void init() {
		this.setText(this.elementMenuLabel);
		MenuItem createItem = new MenuItem(this.createElementLabel);
		createItem.setOnAction(e -> this.createTower());
		MenuItem periodicTableItem = new MenuItem(this.periodicTableLabel);
		periodicTableItem.setOnAction(e -> viewPeriodicTable()); 
		this.getItems().addAll(createItem, periodicTableItem); 
	}
	
	private void createTower() {
		
	}
	
	private void viewPeriodicTable() {
		Stage stage = new Stage(); 
		stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(new PeriodicTableView(), 450, 450));
        stage.show();

	}
}
