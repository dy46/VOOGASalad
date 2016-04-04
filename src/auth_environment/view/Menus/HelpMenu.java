package auth_environment.view.Menus;

import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.BrowserWindowDelegate;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 * 
 * Team member responsible: Brian
 * 
 * This menu diplays Help.
 */

public class HelpMenu extends Menu {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private BrowserWindowDelegate myBrowser;
	
	public HelpMenu() {
		this.myBrowser  = new BrowserWindowDelegate(); 
		this.setText(myNamesBundle.getString("helpMenuLabel"));
		this.init();
	}
	
	private void init() {
		MenuItem openHelpItem = new MenuItem(myNamesBundle.getString("openHelpItem"));
		openHelpItem.setOnAction(e -> myBrowser.openWindow(myNamesBundle.getString("helpMenuLabel"),
														   myURLSBundle.getString("helpURL"),
														   Double.parseDouble(myDimensionsBundle.getString("helpWidth")),
														   Double.parseDouble(myDimensionsBundle.getString("helpHeight"))
														   ));
		this.getItems().add(openHelpItem);
	}
}