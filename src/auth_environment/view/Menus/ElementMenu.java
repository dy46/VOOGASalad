package auth_environment.view.Menus;

import java.io.File;

import auth_environment.view.PeriodicTableView;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 */
public class ElementMenu extends Menu {
	
	// TODO: extract these constants
	private static String elementMenuLabel = "Elements";
	private static String createElementLabel = "Create New Element"; 
	private static String periodicTableLabel = "Periodic Table";
	private static int periodicTableWidth = 450;
	private static int periodicTableHeight = 450;
	
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
		stage.setTitle(this.periodicTableLabel);
        stage.setScene(new Scene(new PeriodicTableView(), this.periodicTableWidth, this.periodicTableHeight));
        stage.show();
	}
}
