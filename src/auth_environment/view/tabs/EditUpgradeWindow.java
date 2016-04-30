package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.delegatesAndFactories.NodeFactory;
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
	private BorderPane mainPane = new BorderPane();
	private GridPane upgradePane = new GridPane();
	private IAuthModel authModel;
	private String unitName;
	private NodeFactory nodeFactory = new NodeFactory();
	private int rowIndex = 0;
	private List<String> affectorNames;
	private List<Integer> affectorCosts;

	public EditUpgradeWindow(String unitName, IAuthModel iAuthModel, List<String> affectorNames, List<Integer> affectorCosts) {
		authModel = iAuthModel;
		this.unitName = unitName;
		this.affectorCosts = affectorCosts;
		this.affectorNames = affectorNames;
		Stage stage = new Stage();
		Group root = new Group();
		mainPane.setCenter(upgradePane);
		root.getChildren().add(mainPane);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Upgrades");
		stage.show();

		Button addNew = new Button("Add new upgrade");
		Button ok = new Button("Ok");
		ok.setOnAction(e -> saveAndClose());
		addNew.setOnAction(e -> addUpgradeSlot());
		
		BorderPane buttons = new BorderPane();
		buttons.setTop(addNew);
		buttons.setBottom(ok);
		mainPane.setBottom(buttons);

		addUpgradeSlot();
	}

	private void addUpgradeSlot() {
		ComboBox<String> combo = new ComboBox<>();
		List<String> affectorNames = new ArrayList<String>();
		for (Affector a : authModel.getIAuthEnvironment().getAffectors())
			affectorNames.add(a.getClass().toString());

		combo.getItems().addAll(affectorNames);
		TextField cost = nodeFactory.buildTextFieldWithPrompt("Upgrade cost");

		upgradePane.addRow(rowIndex++, combo, cost);
	}

	private void saveAndClose() {
		// addUpgradeSlot();// this isn't where this should go

	}
}
