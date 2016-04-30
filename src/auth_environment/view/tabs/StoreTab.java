package auth_environment.view.tabs;

import java.util.*;

import auth_environment.Models.StoreTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IStoreTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StoreTab extends Tab implements IWorkspace {

	private BorderPane myRoot;
	private GridPane myGrid;
	private StoreTabModel myStoreTabModel;
	private IStoreTabModel myStoreTabModelInterface;
	private IAuthModel myAuthModel;
	private List<ComboBox> unitList;
	private List<TextField> costList;
	private NodeFactory myNodeFactory;

	public StoreTab(String name, IAuthModel authModel) {
		super(name);
		myAuthModel = authModel;
		unitList = new ArrayList<ComboBox>();
		costList = new ArrayList<TextField>();
		myNodeFactory = new NodeFactory();
		init();
	}
	
	private void init(){
		myRoot = new BorderPane();
		myGrid = new GridPane();
		int index = 0;
		Button dummyButton = new Button("Lol why do i exist");
		ComboBox dummyCBox = new ComboBox();
		dummyCBox.setValue("test");	
		createProductList(index, myGrid, dummyButton, dummyCBox);
		myRoot.setLeft(myGrid);
		Button done = new Button("Done");
		myRoot.setBottom(done);
		setContent(myRoot);
	}
	
	private void createProductList(int index, GridPane newTableInfo, Button dButton, ComboBox dCBox){
		if (dCBox.getValue() != null) {
			newTableInfo.getChildren().remove(dButton);
			ComboBox<String> newCBox = new ComboBox<String>();
			newCBox.getItems().addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
			newTableInfo.add(createCostBox(newCBox), 2, index);
			index++;
			Button newAffectorButton = new Button("+ Add New Product");
			int num = index;
			newAffectorButton
			.setOnAction(e -> createProductList(num, newTableInfo, newAffectorButton,
					newCBox));
			newTableInfo.add(newAffectorButton, 2, index);
			unitList.add(newCBox);
		}
	}
	
	private Node createCostBox(ComboBox cBox){
		HBox hbox = new HBox();
		hbox.getChildren().add(cBox);
		TextField input = this.myNodeFactory.buildTextFieldWithPrompt("Cost");
		input.setMaxWidth(65);
		input.setMinHeight(25);
		hbox.setMinWidth(200);
		hbox.getChildren().add(input);
		hbox.getChildren().add(createEditButton(cBox));
		costList.add(input);
		return hbox;
	}
	
	private Node createEditButton(ComboBox newCBox){
		Button edit = new Button("Edit Affectors");
		if(newCBox.getValue() != null){
			String name = (String) newCBox.getValue();
			edit.setOnAction(e -> new EditUpgradeWindow(name, myAuthModel));
		}
		return edit;
	}
	

	public Node getRoot() {
		return myRoot;
	}
	
	

}
