package utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import com.twilio.sdk.TwilioRestException;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SampleFrontend {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private Scene myScene;
	
	private VBox myRoot;
	
	private NodeFactory myNodeFactory;

	public SampleFrontend () {
		this.myNodeFactory = new NodeFactory(); 
		this.init(); 
	}

	private void init() {
		Stage myStage = new Stage(); 
		this.myRoot = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding"))
				);
		this.myScene = new Scene(this.myRoot);
		this.myRoot.getChildren().addAll( 
				this.buildFileButton(),
				this.buildFolderButton(),
				this.buildAnimation()
				);
		this.myRoot.setStyle("-fx-background-color: #292929;");
		this.myRoot.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet"));
		myStage.setScene(this.myScene);
		myStage.show();
		myStage.toFront();
	}
	
	private Node buildFileButton() {
		Button chooseFile = this.myNodeFactory.buildButton("Choose a File"); 
		chooseFile.setOnAction(e -> this.uploadFile());
		return chooseFile; 
	}
	
	private Node buildFolderButton() {
		Button chooseFolder = this.myNodeFactory.buildButton("Choose a Folder");
		chooseFolder.setOnAction(e -> this.uploadFolder());
		return chooseFolder; 
	}
	
	// TODO: refactor by combining with uploadFile() 
	private void uploadFolder() {
		FileChooserDelegate chooser = new FileChooserDelegate(); 
        CloudStorage c;
		try {
			c = new CloudStorage("sea7P0kYz0PcuVJ4pcEoYWQRRNrNjkoA");
			String path = chooser.chooseFile("Choose a File within the Desired Folder").getParent(); 
			c.uploadFolder(path);
		} catch (FileNotFoundException | TwilioRestException e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private void uploadFile() {
		FileChooserDelegate chooser = new FileChooserDelegate(); 
        CloudStorage c;
		try {
			c = new CloudStorage("sea7P0kYz0PcuVJ4pcEoYWQRRNrNjkoA");
	        File f = chooser.chooseFile("Choose a File");
	        c.uploadFile(f.getAbsolutePath(), f.getName());
		} catch (FileNotFoundException e) {
			System.out.println("Invalid developer token"); 
		}

	}
	
	private ImageView buildAnimation() {
		ImageView animation = myNodeFactory.buildImageView(myNamesBundle.getString("wompAnimation"));
		animation.setFitWidth(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")));
		return animation;
	}
 	
}
