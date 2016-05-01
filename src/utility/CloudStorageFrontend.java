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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/*
 * Example code demonstrating how to integrate cloud storage with front-end
 */
public class CloudStorageFrontend {
	
    private static final String MESSAGE = "new files have been uploaded!";
    private static final String RESOURCE_PATH = "utility/recvlist";
    private static final String MY_NUMBER = "+12056600267";
    private static final String ACCOUNT_SID = "AC7fc35c31998ec586534822716579d284";
    private static final String AUTH_TOKEN = "09a2ae71719093f892ee57a320eba40f";
	private Scene myScene;
	private VBox myRoot;
	private TextField myKeyInput; 
	private ListView myBoxContents; 
	private String myDevKey = "";
	private CloudStorage myCloudStorage; 


	public CloudStorageFrontend () {
		init(); 
	}

	private void init() {
		Stage myStage = new Stage(); 
		myRoot = new VBox();
		myRoot.setSpacing(10);
		myRoot.setPadding(new Insets(10));
		myScene = new Scene(myRoot);
		myRoot.getChildren().addAll( 
				buildBoxImage(),
				buildKeyInput(),
				buildFileButton(),
				buildAnimation()
				);
		myRoot.setStyle("-fx-background-color: #292929;");
		myRoot.setPrefSize(600, 600); 
		myStage.setScene(myScene);
		myStage.show();
		myStage.toFront();
	}
	
	private Node buildFileButton() {
		Button chooseFile = new Button("Choose a File");
		chooseFile.setOnAction(e -> uploadFile());
		
		Button chooseFolder = new Button("Choose a Folder"); 
		chooseFolder.setOnAction(e -> uploadFolder());
		
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
				myCloudStorage.uploadFolder(path);
				printResults(myCloudStorage);
//				c.notify(MESSAGE);
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
		        myCloudStorage.uploadFile(f.getAbsolutePath(), f.getName());
		        printResults(myCloudStorage);
//		        c.notify(MESSAGE);
	        }
		} catch (Exception e) {
			System.out.println("Invalid developer token"); 
		}
	}
	
	private HBox buildKeyInput() {
		myKeyInput = new TextField();
		myKeyInput.setPromptText("Enter account key");
		myKeyInput.setOnAction(e -> {
			myDevKey = myKeyInput.getText();
			try {
				myCloudStorage = new CloudStorage(myDevKey);
				myCloudStorage.setUpNotifications(RESOURCE_PATH, ACCOUNT_SID, AUTH_TOKEN, MY_NUMBER);
			} catch (Exception e1) {
				System.out.println("Invalid developer token"); 
			}
		});
		
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(e -> {
			myDevKey = myKeyInput.getText();
			try {
				myCloudStorage = new CloudStorage(myDevKey);
				myCloudStorage.setUpNotifications(RESOURCE_PATH, ACCOUNT_SID, AUTH_TOKEN, MY_NUMBER);

			} catch (Exception e1) {
				System.out.println("Invalid developer token"); 
			}
		});
		
		HBox hb = new HBox(); 
		hb.setPadding(new Insets(10));
		hb.setSpacing(10);
		hb.getChildren().addAll(myKeyInput, submitButton);
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

	private Node buildViewContents() {
		myBoxContents = new ListView();
//		myBoxContents.
		return null;
	}
 	
}