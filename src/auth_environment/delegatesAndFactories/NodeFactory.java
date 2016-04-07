package auth_environment.delegatesAndFactories;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Shared
 *
 * This Factory class is used to build common GUI elements for convenience. 
 */

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NodeFactory {
	
	// TODO: do these properties need to be extracted? 
	private Font fontLabels = Font.font("Adobe Caslon Pro", FontWeight.BOLD, 12);
	private Font fontLarge = Font.font("Adobe Caslon Pro", FontWeight.BOLD, 20);

	public NodeFactory() {
		
	}
	
	public void setupVBox(VBox vbox, String titleText, Font font, double spacing, double padding) {
		vbox.setSpacing(spacing);
		vbox.setPadding(new Insets(padding));
		Text title = new Text(titleText);
		title.setFont(font);
		vbox.getChildren().add(title);
	}
	
	public void addImageView(Pane pane, String imageName) {
		pane.getChildren().add(makeImageView(imageName));
	}
	
	public ImageView makeImageView(String imageName) {
		return new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName)));
	}
	
	public void addLabelToPane(Pane pane, String label, Font font) {
		pane.getChildren().add(this.makeLabel(label, font));
	}
	
	public Text makeLabel(String label, Font font) {
		Text myLabel = new Text(label);
		myLabel.setFont(font);
		return myLabel;
	}
	
	public Font defaultFont() {
		return this.fontLabels;
	}
	
	public Font titleFont() {
		return this.fontLarge;
	}
}
