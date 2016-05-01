package auth_environment.view.tabs;

import java.util.*;

import auth_environment.Models.StoreTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IStoreTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.geometry.Insets;
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
		// ComboBox dummyCBox = new ComboBox();
		// dummyCBox.setValue("test");
		createProductList(index, myGrid, dummyButton);
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
			s.getItems()
					.addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
		});
	}

	private void createProductList(int index, GridPane newTableInfo, Button dButton) {
		newTableInfo.getChildren().remove(dButton);

		NameAffectorCostSet n = new NameAffectorCostSet();

		ComboBox<String> newCBox = n.getComboBox();
		newCBox.getItems()
				.addAll(this.myAuthModel.getIAuthEnvironment().getUnitFactory().getUnitLibrary().getUnitNames());
		newTableInfo.add(createCostBox(n), 2, index);
		index++;
		Button newProductButton = new Button(myNamesBundle.getString("storeAddNewProduct"));
		int num = index;
		newProductButton.setOnAction(e -> createProductList(num, newTableInfo, newProductButton));
		newTableInfo.add(newProductButton, 2, index);
		unitList.add(newCBox);
		nameAffectorCostSets.add(n);
	}

	private Node createCostBox(NameAffectorCostSet n) {
		ComboBox<String> cBox = n.getComboBox();
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);
		cBox.setMinWidth(150);
		hbox.getChildren().add(cBox);
		TextField input = this.myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("storePromptCost"));
		n.setTextFieldCost(input);
		input.setMaxWidth(65);
		input.setMinHeight(23);
		hbox.setMinWidth(200);
		hbox.getChildren().add(input);
		hbox.getChildren().add(createEditButton(n));
		costList.add(input);
		return hbox;
	}

	private Node createEditButton(NameAffectorCostSet n) {
		Button edit = new Button(myNamesBundle.getString("storeEditAffectors"));
		edit.setOnAction(e -> checkContent(n));
		return edit;
	}

	private void checkContent(NameAffectorCostSet n) {
		if (n.getName() != null) {
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
			if (nacs.getName() == null)
				continue;

			List<String> nameList = new ArrayList<>();
			for (int i = 0; i < nacs.affectorCost.size(); i++)
				nameList.add(nacs.getName());

			myStoreTabModel.addBuyableUnit(nacs.getName(), nacs.getCost());
			myStoreTabModel.addBuyableUpgrades(nameList, nacs.affectorNames, nacs.affectorCost);
		}
	}

	public Node getRoot() {
		return myRoot;
	}

	public static class NameAffectorCostSet {
		// public String name;
		private ComboBox<String> comboBox = new ComboBox<>();
		private TextField cost;
		public List<String> affectorNames = new ArrayList<>();
		public List<Integer> affectorCost = new ArrayList<>();

		public void setTextFieldCost(TextField c) {
			cost = c;
		}

		public TextField getTextFieldCost() {
			return cost;
		}

		public ComboBox<String> getComboBox() {
			return comboBox;
		}

		public String getName() {
			return comboBox.getValue();
		}

		public int getCost() {
			try {
				return Integer.parseInt(cost.getText());
			} catch (Exception e) {
				return 0;
			}
		}
	}
}
