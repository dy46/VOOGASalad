package utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import com.twilio.sdk.TwilioRestException;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CloudStorageFrontend {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
    private static final String MESSAGE = "new files have been uploaded!";
	private static final String DEVELOPER_TOKEN = "lr976BMHY8Nm9NGys7J5KYV6Qy6PRNwJ";

	private Scene myScene;
	
	private VBox myRoot;
	
	private NodeFactory myNodeFactory;

	public CloudStorageFrontend () {
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
				this.buildBoxImage(),
				this.buildFileButton(),
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
		Button chooseFile = new Button("Choose a File");
		chooseFile.setOnAction(e -> this.uploadFile());
		
		Button chooseFolder = new Button("Choose a Folder"); 
		chooseFolder.setOnAction(e -> this.uploadFolder());
		
		HBox hb = new HBox(); 
		hb.setSpacing(10);
		hb.setPadding(new Insets(10));
		hb.getChildren().addAll(chooseFile, chooseFolder); 
		
		return this.myNodeFactory.centerNode(hb); 
	}
	
	// TODO: refactor by combining with uploadFile() 
	private void uploadFolder() {
		FileChooserDelegate chooser = new FileChooserDelegate(); 
		try {
			String path = chooser.chooseDirectory("Choose a Folder").getPath(); 
	        CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
			c.uploadFolder(path);
			this.printResults(c);
		} catch (FileNotFoundException | TwilioRestException e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private void uploadFile() {
		FileChooserDelegate chooser = new FileChooserDelegate(); 
		try {
	        File f = chooser.chooseFile("Choose a File");
	        CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
	        c.uploadFile(f.getAbsolutePath(), f.getName());
	        this.printResults(c);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid developer token"); 
		}

	}
	
	private HBox buildBoxImage() {
		return myNodeFactory.centerNode(myNodeFactory.buildImageView("boxLogo.png"));
	}
	
	private void printResults (CloudStorage c) {
        System.out.println(c.getCurrentFiles());
        c.listFolders();
        try {
			c.notify(MESSAGE);
		} catch (TwilioRestException e) {
			System.out.println("Twilio API error");
		}
	}
	
	private ImageView buildAnimation() {
		ImageView animation = myNodeFactory.buildImageView(myNamesBundle.getString("wompAnimation"));
		animation.setFitWidth(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")));
		return animation;
	}
 	
}
