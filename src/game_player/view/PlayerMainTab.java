package game_player.view;

import game_player.GameDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlayerMainTab implements IPlayerTab{
	private static final int TOP_PADDING = 5;
	private static final int RIGHT_PADDING = 10;
	private static final int BOTTOM_PADDING = 5;
	private static final int LEFT_PADDING = 10;
	private static final String GUI_ELEMENTS = "GUIElements";
	private static final String PACKAGE_NAME = "game_player.view.";
	private static final int PANEL_PADDING = 10;
	private Tab myTab;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	private ResourceBundle elementsResources;
	private GameDataSource gameData;
	private List<IGUIObject> gameElements;
	
	private VBox gameSection;
	private VBox configurationPanel;
	private VBox gameMenu;
	private HBox gamePanel;
	
	public PlayerMainTab(ResourceBundle r, GameDataSource gameData) {
		this.myResources = r;
		this.gameData = gameData;
		this.gameElements = new ArrayList<>();
		this.elementsResources = ResourceBundle.getBundle(GUI_ELEMENTS);
	}
	
	@Override
	public Tab getTab() {
		initializeTab();
		myTab = new Tab();
		myRoot = new BorderPane();
		
		createUISections();
		initializeElements();
		placeUISections();
		
		myTab.setContent(myRoot);
		return myTab;
	}
	
	private void initializeTab() {
	}
	
	//TODO: Possibly use reflection/other techniques to initialize elements.
	private void initializeElements() {
		IGUIObject newElement = null;
		Enumeration<String> resourceKeys = elementsResources.getKeys();
		while (resourceKeys.hasMoreElements()) {
			String currentKey = resourceKeys.nextElement();
			String[] keyAndPosition = elementsResources.getObject(currentKey).toString().split(",");
			try {
				newElement = (IGUIObject) Class.forName(PACKAGE_NAME + keyAndPosition[0].trim())
						.getConstructor(ResourceBundle.class, GameDataSource.class)
						.newInstance(myResources, gameData);
				gameElements.add(newElement);

				placeElement(newElement, keyAndPosition[1].trim());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void placeElement(IGUIObject element, String position) {
		try{
			getClass().getDeclaredMethod(position, IGUIObject.class).invoke(this, element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createUISections() {
		gameSection = new VBox(PANEL_PADDING);
		configurationPanel = new VBox(PANEL_PADDING);
		gameMenu = new VBox(PANEL_PADDING);
		gamePanel = new HBox(PANEL_PADDING);
		this.configurePanels();
	}
	
	private void placeUISections() {
		myRoot.setCenter(gameSection);
		myRoot.setRight(configurationPanel);
		myRoot.setTop(gameMenu);
		myRoot.setBottom(gamePanel);
	}
	
	private void configurePanels() {
		Label configurationLabel = new Label(myResources.getString("Configuration"));
		configurationLabel.setFont(new Font("Arial", 20));
		configurationPanel.setAlignment(Pos.TOP_CENTER);
		configurationPanel.getChildren().add(configurationLabel);
		configurationPanel.setPadding(new Insets(TOP_PADDING, RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
		gamePanel.setPadding(new Insets(TOP_PADDING, RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
	}
	
	private void updateGameElements() {
		for (IGUIObject object: gameElements) {
			object.updateNode();
		}
	}
	
	private void addToTop(IGUIObject element) {
		gameMenu.getChildren().add(element.createNode());
	}
	
	private void addToGameCanvas(IGUIObject element) {
		gameSection.getChildren().add(element.createNode());
	}
	
	private void addToConfigurationPanel(IGUIObject element) {
		configurationPanel.getChildren().add(element.createNode());
	}
	
	private void addToGamePanel(IGUIObject element) {
		gamePanel.getChildren().add(element.createNode());
	}
}
