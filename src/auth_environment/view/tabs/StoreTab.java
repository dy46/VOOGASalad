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
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private BorderPane myRoot;
	private GridPane myGrid;
	private StoreTabModel myStoreTabModel;
	private IStoreTabModel myStoreTabModelInterface;
	private IAuthModel myAuthModel;
	private List<ComboBox<String>> unitList;
	private List<TextField> costList;
	private NodeFactory myNodeFactory;
	private Button done;
	private List<NameAffectorCostSet> nameAffectorCostSets = new ArrayList<>();

	public StoreTab(String name, IAuthModel authModel) {
		super(name);
		myAuthModel = authModel;
		unitList = new ArrayList<ComboBox<String>>();
		costList = new ArrayList<TextField>();
		myNodeFactory = new NodeFactory();
		init();
	}

	private void init() {
		myRoot = new BorderPane();
		myGrid = new GridPane();
		myStoreTabModel = new StoreTabModel(myAuthModel);
		setRefresh();
		int index = 0;
		Button dummyButton = new Button("");
		ComboBox dummyCBox = new ComboBox();
		dummyCBox.setValue("test");
		createProductList(index, myGrid, dummyButton, dummyCBox);
		myRoot.setLeft(myGrid);
		done = new Button(myNamesBundle.getString("storeDoneButton"));
		doneAction();
		myRoot.setBottom(done);
		setContent(myRoot);
	}

	private void setRefresh() {
		this.setOnSelectionChanged(e -> refresh());
	}

	private void refresh() {
		unitList.stream().forEach(s -> {
			s.getItems().clear();
			s.getItems().addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
		});
	}

	private void createProductList(int index, GridPane newTableInfo, Button dButton, ComboBox dCBox) {
		newTableInfo.getChildren().remove(dButton);
		ComboBox<String> newCBox = new ComboBox<String>();
		newCBox.getItems()
				.addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
		newTableInfo.add(createCostBox(newCBox), 2, index);
		index++;
		Button newProductButton = new Button(myNamesBundle.getString("storeAddNewProduct"));
		int num = index;
		newProductButton.setOnAction(e -> createProductList(num, newTableInfo, newProductButton, newCBox));
		newTableInfo.add(newProductButton, 2, index);
		unitList.add(newCBox);

	}

	private Node createCostBox(ComboBox cBox) {
		HBox hbox = new HBox();
		hbox.getChildren().add(cBox);
		TextField input = this.myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("storePromptCost"));
		input.setMaxWidth(65);
		input.setMinHeight(25);
		hbox.setMinWidth(200);
		hbox.getChildren().add(input);
		hbox.getChildren().add(createEditButton(cBox));
		costList.add(input);
		return hbox;
	}

	private Node createEditButton(ComboBox newCBox) {
		Button edit = new Button(myNamesBundle.getString("storeEditAffectors"));
		String name = (String) newCBox.getValue();
		edit.setOnAction(e -> checkContent(newCBox, name));
		return edit;
	}

	private void checkContent(ComboBox<String> newCBox, String name) {
		if (newCBox.getValue() != null) {
			NameAffectorCostSet n = new NameAffectorCostSet();
			n.name = (String) newCBox.getValue();
			nameAffectorCostSets.add(n);
			EditUpgradeWindow eWindow = new EditUpgradeWindow(myAuthModel, n);
		}
	}

	private void doneAction() {
		done.setOnAction(e -> updateBuyables());
	}

	// THIS NEEDS TO BE CHANGED
	private void updateBuyables() {
		/*
		 * List<String> names = new ArrayList<String>(); List<Integer> costs =
		 * new ArrayList<Integer>(); for(int i = 0; i < unitList.size(); i++){
		 * System.out.println("Store test: " + unitList.get(i).getValue());
		 * names.add(unitList.get(i).getValue());
		 * costs.add(Integer.parseInt(costList.get(i).getText())); }
		 * myStoreTabModel.addBuyableUnits(names, costs); System.out.println(
		 * "Values updated to back-end");
		 */

		for (NameAffectorCostSet nacs : nameAffectorCostSets) {
			if (nacs.affectorCost.size() == 0)
				continue;
			
			List<String> nameList = new ArrayList<>();
			for(int i = 0; i < nacs.affectorCost.size(); i++)
				nameList.add(nacs.name);
				
			myStoreTabModel.addBuyableUpgrades(nameList, nacs.affectorNames, nacs.affectorCost);
		}
	}

	public Node getRoot() {
		return myRoot;
	}

	public static class NameAffectorCostSet {
		public String name;
		public List<String> affectorNames = new ArrayList<>();
		public List<Integer> affectorCost = new ArrayList<>();
	}
}
