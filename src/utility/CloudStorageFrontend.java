package utility;
import java.io.File;
import java.io.FileNotFoundException;

import com.twilio.sdk.TwilioRestException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CloudStorageFrontend {
	
    private static final String MESSAGE = "new files have been uploaded!";

	private Scene myScene;
	private VBox myRoot;
	private TextField myKeyInput; 
	private String myDevKey = "";

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
				this.buildKeyInput(),
				this.buildFileButton(),
				this.buildAnimation()
				);
		this.myRoot.setStyle("-fx-background-color: #292929;");
		this.myRoot.setPrefSize(800, 800); 
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
		DirectoryChooser chooser = new DirectoryChooser(); 
		try {
			ContextMenu prefWindow = new ContextMenu(); 
			File dir = chooser.showDialog(prefWindow.getOwnerWindow());
			if (dir != null) {
				String path = dir.getPath(); 
				CloudStorage c = new CloudStorage(this.myDevKey);
				c.uploadFolder(path);
				this.printResults(c);
			}
		} catch (FileNotFoundException | TwilioRestException e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private void uploadFile() {
		FileChooser chooser = new FileChooser(); 
		try {
			ContextMenu prefWindow = new ContextMenu();
		    File f = chooser.showOpenDialog(prefWindow.getOwnerWindow());
	        if (f != null) {
		        CloudStorage c = new CloudStorage(this.myDevKey);
		        c.uploadFile(f.getAbsolutePath(), f.getName());
		        this.printResults(c);
	        }
		} catch (FileNotFoundException e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private HBox buildKeyInput() {
		this.myKeyInput = new TextField();
		this.myKeyInput.setPromptText("Enter account key");
		this.myKeyInput.setOnAction(e -> this.myDevKey = this.myKeyInput.getText());
		
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(e -> this.myDevKey = this.myKeyInput.getText());
		
		HBox hb = new HBox(); 
		hb.setPadding(new Insets(10));
		hb.setSpacing(10);
		hb.getChildren().addAll(this.myKeyInput, submitButton);
		hb.setAlignment(Pos.CENTER);
		
		return hb;
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
		ImageView animation = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("drippyDrops.gif")));
		animation.setFitWidth(800);
		return animation;
	}
 	
}
