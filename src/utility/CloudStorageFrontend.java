package utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import com.twilio.sdk.TwilioRestException;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CloudStorageFrontend {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
    private static final String MESSAGE = "new files have been uploaded!";
	private static final String DEVELOPER_TOKEN = "lr976BMHY8Nm9NGys7J5KYV6Qy6PRNwJ";

	private Scene myScene;
	
	private VBox myRoot;

	public CloudStorageFrontend () {
		this.init(); 
	}

	private void init() {
		Stage myStage = new Stage(); 
		this.myRoot = new VBox();
		this.myRoot.setSpacing(10);
		this.myRoot.setPadding(new Insets(10));
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
		hb.setAlignment(Pos.CENTER);
		
		return hb;
	}
	
	// TODO: refactor by combining with uploadFile() 
	private void uploadFolder() {
		FileChooserDelegate chooser = new FileChooserDelegate(); 
		try {
			File dir = chooser.chooseDirectory("Choose a Folder"); 
			if (dir == null) {
				System.out.println("Cancel button pressed"); 
			}
			else {
				String path = dir.getPath(); 
				CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
				c.uploadFolder(path);
				this.printResults(c);
			}
		} catch (FileNotFoundException | TwilioRestException e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private void uploadFile() {
		FileChooserDelegate chooser = new FileChooserDelegate(); 
		try {
	        File f = chooser.chooseFile("Choose a File");
	        if (f == null) {
	        	System.out.println("Cancel button pressed");
	        }
	        else {
		        CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
		        c.uploadFile(f.getAbsolutePath(), f.getName());
		        this.printResults(c);
	        }
		} catch (FileNotFoundException e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private HBox buildBoxImage() {
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("boxLogo.png"))));
		return hb; 
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
		ImageView animation = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("catKeyboard.gif")));
//		animation.setFitWidth(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")));
		return animation;
	}
 	
}
