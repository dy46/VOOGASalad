// This entire file is part of my masterpiece.
// Patrick Grady

/* 
 * This file is my masterpiece for Voogasalad, team Womp
 * 
 * This class now fully supports the Model View Controller pattern. When it was initially written,
 * this class only wrote data, but never read data from the model. This caused problems, because the
 * author could never see what they had written before in old XML files. Now, it reads data from the model
 * when it is loaded, and then when closed, it clears the old data in the model and writes the data stored
 * in it's GUI. This is just a simple data binding, but it demonstrates what I've learned in this course.
 * Always just keep a single copy of the data, and have everything connect to that.
 */

package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.tabs.StoreTab.NameAffectorCostSet;
import game_engine.affectors.Affector;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditUpgradeWindow extends Observable {
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	// JavaFX elements
	private BorderPane mainPane = new BorderPane();
	private GridPane upgradePane = new GridPane();
	private Stage stage;
	private List<ComboBox<String>> comboBoxes = new ArrayList<>();
	private List<TextField> textFields = new ArrayList<>();

	// Data elements
	private IAuthModel authModel;
	private NodeFactory nodeFactory = new NodeFactory();
	private NameAffectorCostSet nameAffectorCostSet;

	private int rowIndex = 0;

	public EditUpgradeWindow(IAuthModel iAuthModel, StoreTab.NameAffectorCostSet nameAffectorCostSet) {
		this.nameAffectorCostSet = nameAffectorCostSet;
		authModel = iAuthModel;

		setupGUI();

		for (int i = 0; i < nameAffectorCostSet.getAffectorNames().size(); i++) {
			addUpgradeSlot(nameAffectorCostSet.getAffectorNames().get(i), nameAffectorCostSet.getAffectorCost().get(i));
		}

		addUpgradeSlot("", 0);
	}

	private void addUpgradeSlot(String selectedAffector, int selectedCost) {
		ComboBox<String> combo = new ComboBox<>();
		comboBoxes.add(combo);

		List<String> affectorNames = new ArrayList<>();
		for (Affector a : authModel.getIAuthEnvironment().getAffectorFactory().getAffectorLibrary().getAffectors())
			affectorNames.add(a.getName());
		combo.getItems().addAll(affectorNames);
		combo.setValue(selectedAffector);

		TextField cost = nodeFactory.buildTextFieldWithPrompt("Upgrade cost");
		cost.setText(Integer.toString(selectedCost));
		textFields.add(cost);

		upgradePane.addRow(rowIndex++, combo, cost);
	}

	private void saveAndClose() {
		nameAffectorCostSet.getAffectorCost().clear();
		nameAffectorCostSet.getAffectorNames().clear();

		for (int i = 0; i < comboBoxes.size(); i++) {
			String selectedAffector = comboBoxes.get(i).getValue();
			if (selectedAffector.trim().length() == 0)
				continue;

			String affectorName = comboBoxes.get(i).getValue();
			int upgradeCost = Integer.parseInt(textFields.get(i).getText());

			nameAffectorCostSet.getAffectorCost().add(upgradeCost);
			nameAffectorCostSet.getAffectorNames().add(affectorName);
		}
		stage.close();
	}

	private void setupGUI() {
		stage = new Stage();
		Group root = new Group();
		mainPane.setCenter(upgradePane);
		root.getChildren().add(mainPane);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(myNamesBundle.getString("storeEditUpgrade"));
		stage.show();

		Button addNew = new Button(myNamesBundle.getString("storeUpgradeButton"));
		Button ok = new Button("Ok");
		ok.setOnAction(e -> saveAndClose());
		addNew.setOnAction(e -> addUpgradeSlot("", 0));

		BorderPane buttons = new BorderPane();
		buttons.setTop(addNew);
		buttons.setBottom(ok);
		mainPane.setBottom(buttons);
	}

}
