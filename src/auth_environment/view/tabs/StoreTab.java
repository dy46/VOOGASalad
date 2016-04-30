package auth_environment.view.tabs;

import java.util.*;

import auth_environment.Models.StoreTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IStoreTabModel;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class StoreTab extends Tab implements IWorkspace {

	private BorderPane myRoot;
	private StoreTabModel myStoreTabModel;
	private IStoreTabModel myStoreTabModelInterface;
	private IAuthModel myAuthModel;
	private List<String> unitList = new ArrayList<String>();

	public StoreTab(String name, IAuthModel authModel) {
		super(name);
		myAuthModel = authModel;
		init();
	}
	
	private void init(){
		myRoot = new BorderPane();
		
		setContent(myRoot);
	}
	
	private void createProductList(){
		
	}

	public Node getRoot() {
		return myRoot;
	}

}
