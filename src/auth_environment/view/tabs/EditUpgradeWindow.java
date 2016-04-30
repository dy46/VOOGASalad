package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.List;

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

public class EditUpgradeWindow {
	private BorderPane mainPane = new BorderPane();
	private GridPane upgradePane = new GridPane();
	private IAuthModel authModel;
	private NodeFactory nodeFactory = new NodeFactory();

	public EditUpgradeWindow(IAuthModel iAuthModel) {
		authModel = iAuthModel;

		Stage stage = new Stage();
		Group root = new Group();
		mainPane.setCenter(upgradePane);
		root.getChildren().add(mainPane);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Upgrades");

		Button ok = new Button("Ok");
		ok.setOnAction(e -> saveAndClose());
		mainPane.setBottom(ok);
	}

	private void addUpgradeSlot() {
		ComboBox<String> combo = new ComboBox<>();
		List<String> affectorNames = new ArrayList<String>();
		for (Affector a : authModel.getIAuthEnvironment().getAffectors())
			affectorNames.add(a.getName());

		combo.getItems().addAll(affectorNames);
		TextField cost = nodeFactory.buildTextFieldWithPrompt("Cost");
		TextField maxCount = nodeFactory.buildTextFieldWithPrompt("Max count");

		upgradePane.addRow(1, combo, cost, maxCount);
	}

	private void saveAndClose() {
		addUpgradeSlot();// this isn't where this should go
	}
}
