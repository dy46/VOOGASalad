package game_player.view;

import java.util.List;
import java.util.ResourceBundle;

import game_player.GameDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GameColorTheme implements IGUIObject {
	
	private static final int COMBOBOX_WIDTH = 190;
	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int HBOX_PADDING = 10;
	private ResourceBundle myResources;
	private ComboBox<String> comboBox;
	private Button comboBoxButton;
	private ObservableList<String> options;
	
	public GameColorTheme(ResourceBundle r, GameDataSource gameData, PlayerMainTab myTab) {
		myResources = r;
	}

	@Override
	public Node createNode() {
		HBox colorThemeBox = new HBox(HBOX_PADDING);
		options = FXCollections.observableArrayList();
		populateOptions();
		comboBox = new ComboBox<>(options);
		configureComboBox();
		comboBoxButton = new Button(myResources.getString("GoText"));
		comboBoxButton.setOnAction(event -> performAction());
		colorThemeBox.getChildren().addAll(comboBox, comboBoxButton);
		return colorThemeBox;
	}
	
	public void configureComboBox() {
		comboBox.setVisibleRowCount(VISIBLE_ROW_COUNT);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(myResources.getString("ThemeLabel"));
	}
	
	public void populateOptions() {
		String[] themeOptions = myResources.getString("ThemeOptions").split("/");
		for (String s: themeOptions) {
			options.add(s);
		}
	}
	
	public void performAction() {
		//change color theme
	}
	
	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}

}
