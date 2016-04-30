package auth_environment.view.tabs;

import java.util.*;

import auth_environment.Models.StoreTabModel;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class StoreTab extends Tab implements IWorkspace {

	private BorderPane mainPane = new BorderPane();
	private StoreTabModel myStoreTabModel;
//	private I
	private List<String> unitList = new ArrayList<String>();

	public StoreTab(String name) {
		super(name);
		setContent(mainPane);
	}

	public Node getRoot() {
		return mainPane;
	}

}
