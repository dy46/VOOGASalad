package auth_environment.view.tabs;
import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.tabs.AffectorTab;
import auth_environment.view.tabs.ElementTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ElementCreationTab extends Tab {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	public ElementCreationTab (String name, IAuthModel authModel) {
		super(name); 
		init(authModel);
	}
	
	private void init(IAuthModel authModel){
		TabPane myTabs = new TabPane();
		myTabs.getTabs().addAll(new ElementTab(myNamesBundle.getString("unitTabLabel"), authModel), new AffectorTab(myNamesBundle.getString("affectorTabLabel"), authModel));
		setContent(myTabs);
	}
}

