package auth_environment.delegatesAndFactories;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
		vbox = this.buildVBox(titleText, font, spacing, padding);
	}
	
	public VBox buildVBox(double spacing, double padding) {
		VBox vbox = new VBox(); 
		vbox.setSpacing(spacing);
		vbox.setPadding(new Insets(padding));
		return vbox; 
	}
	
	public VBox buildVBox(String titleText, Font font, double spacing, double padding) {
		VBox vbox = this.buildVBox(spacing, padding);
		Text title = new Text(titleText);
		title.setFont(font);
		vbox.getChildren().add(title);
		return vbox; 
	}
	
	public Button buildButton(String text) {
		return new Button(text); 
	}
	
	public HBox buildTextFieldWithLabel(String text, double spacing) {
		Label label = this.buildLabel(text); 
		TextField textField = new TextField(); 
		HBox hBox = new HBox(); 
		hBox.getChildren().addAll(label, textField);
		hBox.setSpacing(spacing);
		return hBox; 
	}
	
	public TextField buildTextFieldWithPrompt(String text) {
		TextField textField = new TextField();
		textField.setPromptText(text);
		return textField;
	}
	
	public void addImageView(Pane pane, String imageName) {
		pane.getChildren().add(makeImageView(imageName));
	}
	
	public ImageView makeImageView(String imageName) {
		return new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName)));
	}
	
	public void addLabelToPane(Pane pane, String label, Font font) {
		pane.getChildren().add(this.buildLabel(label, font));
	}
	
	public Label buildLabel(String text) {
		return new Label(text);
	}
	
	public Label buildLabel(String text, Font font) {
		Label label = this.buildLabel(text);
		label.setFont(font);
		return label; 
	}
	
	public Font defaultFont() {
		return this.fontLabels;
	}
	
	public Font titleFont() {
		return this.fontLarge;
	}
}
