package auth_environment.view.tabs;
import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.tabs.AffectorTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ElementCreationTab extends Tab{
	public ElementCreationTab(String name, IAuthModel authModel, ResourceBundle myNamesBundle){
		super(name);
		init(authModel, myNamesBundle);
	}
	
	private void init(IAuthModel authModel, ResourceBundle myNamesBundle){
		TabPane myTabs = new TabPane();
		myTabs.getTabs().addAll(new UnitTab(myNamesBundle.getString("unitTabLabel"), authModel), new AffectorTab(myNamesBundle.getString("affectorTabLabel"), authModel));
		//myTabs.getTabs().addAll(new AffectorTab(myNamesBundle.getString("affectorTabLabel"), authModel));

		this.setContent(myTabs);
	}
	
}