package auth_environment.delegatesAndFactories;

import javafx.geometry.Insets;
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
	private Font fontLabels = Font.font("Adobe Caslon Pro", FontWeight.BOLD, 14);
	private Font fontLarge = Font.font("Adobe Caslon Pro", FontWeight.BOLD, 20);

	public NodeFactory() {
		
	}
	
	public void setupVBox(VBox vbox, String titleText, Font font, int spacing, int padding) {
		vbox.setSpacing(spacing);
		vbox.setPadding(new Insets(padding));
		Text title = new Text(titleText);
		title.setFont(font);
		vbox.getChildren().add(title);
	}
	
	public Font defaultFont() {
		return this.fontLabels;
	}
	
	public Font titleFont() {
		return this.fontLarge;
	}
}
