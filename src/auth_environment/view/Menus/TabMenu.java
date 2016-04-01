package auth_environment.view.Menus;

import auth_environment.view.Workspace;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 3/31/16.
 */
public class TabMenu extends Menu {

	// TODO: ask team where to extract these
	private static String tabMenuLabel = "Progression"; 
	private static String newWaveLabel = "New Wave"; 
	
	private TabPane myTabs;
	
	public TabMenu(TabPane tabs){
		this.myTabs = tabs;
		this.setText(this.tabMenuLabel);
		this.init();
	}
	
	private void init() {
		MenuItem newTabItem = new MenuItem(this.newWaveLabel); 
		newTabItem.setOnAction(e -> createNewTab());
		this.getItems().add(newTabItem);
	}
	
	// TODO: pop up an alert asking the Developer to name the new Wave (or auto-generate a Wave name) 
	private void createNewTab(){
		Workspace newWorkspace = new Workspace(new Stage(), myTabs);
		myTabs.getTabs().add(new Tab(this.newWaveLabel, newWorkspace.getRoot()));
	}
}
