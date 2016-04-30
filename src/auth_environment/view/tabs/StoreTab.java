package auth_environment.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class StoreTab extends Tab implements IWorkspace {

	BorderPane mainPane = new BorderPane();
	ComboBox<String> 

	public StoreTab(String name) {
		super(name);
		setContent(mainPane);
	}

	public Node getRoot() {
		return mainPane;
	}

}
