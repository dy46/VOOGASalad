package game_player;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SwitchWindow {
	
	private ResourceBundle myResources;
	private GridPane windowRoot;
	private String[] gameNames;
	private ImageView[] gameImages;
	private String[] gameDescriptions;
	
	public SwitchWindow(ResourceBundle r) {
		myResources = r;
	}
	
	public GridPane createWindow() {
		windowRoot = new GridPane();
		
		this.addGameElements();
		
		return windowRoot;
	}
	
	public void addGameElements() {
		gameNames = myResources.getString("GameNameList").split(";");
		this.addImages();
		gameDescriptions = myResources.getString("GameDescriptionList").split(";");
		for (int i = 0; i < gameNames.length; i++) {
			windowRoot.add(new Label(gameNames[i].trim()), i, 0);
			windowRoot.add(gameImages[i], i, 1);
			windowRoot.add(new Label(gameDescriptions[i].trim()), i, 2);
		}
	}
	
	public void addImages() {
		String[] imageNames = myResources.getString("GameImageList").split(";");
		gameImages = new ImageView[imageNames.length];
		for (int i = 0; i < imageNames.length; i++) {
			gameImages[i] = new ImageView(
					new Image(getClass().getClassLoader().getResourceAsStream(imageNames[i].trim())));
			gameImages[i].setFitWidth(150);
			gameImages[i].setFitHeight(100);
		}
	}
}
