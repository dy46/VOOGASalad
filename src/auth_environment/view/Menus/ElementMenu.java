package auth_environment.view.Menus;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Created by BrianLin on 4/1/16.
 */
public class ElementMenu extends Menu {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public ElementMenu() {
		this.init();
	}
	
	private void init() {
		this.setText(this.myNamesBundle.getString("elementMenuLabel"));
		MenuItem towerItem = new MenuItem(this.myNamesBundle.getString("towerItemLabel"));
		towerItem.setOnAction(e -> this.createNewTower());
		this.getItems().addAll(towerItem); 
	}
	
    public void createNewTower(){		//va will refactor this later 
    	
 	   GridPane pane1;

        Label label1=new Label("Name:  ");
        TextField txtfld1 = new TextField();
        Label label2 = new Label("Attack:  ");
        TextField txtfld2 = new TextField();
 	   //pane1=new FlowPane();
 	   pane1 = new GridPane();
 	   pane1.add(label1, 0, 0);
 	   pane1.add(txtfld1, 1, 0);
 	   pane1.add(label2, 0, 1);
 	   pane1.add(txtfld2, 1, 1);
 	   pane1.add(new Label("Health: "), 0, 2);
 	   pane1.add(new TextField(), 1, 2);
 	   
 	   Tooltip t = new Tooltip();
 	   ImageView image = new ImageView();
 	   image.setImage(new Image("pusheenNoodles.gif"));
 	   t.setGraphic(image);

 	   Button uploadImage = new Button("Upload Image");
 	   uploadImage.setOnAction(e -> selectImage(t));
 	   uploadImage.setPrefWidth(150.0);
 	   uploadImage.setTooltip(t);
 	   pane1.add(uploadImage, 1, 5);
 	   
 	   
 	   
 	   
 	   pane1.getColumnConstraints().add(new ColumnConstraints(70));
 	   pane1.getColumnConstraints().add(new ColumnConstraints(200));
 	   pane1.getColumnConstraints().add(new ColumnConstraints(70));
 	   pane1.getRowConstraints().add(new RowConstraints(30));
 	   pane1.getRowConstraints().add(new RowConstraints(30));
 	   pane1.getRowConstraints().add(new RowConstraints(30));
 	   pane1.getRowConstraints().add(new RowConstraints(30));
 	   pane1.getRowConstraints().add(new RowConstraints(30));
 	   
 	   
 	   
 	   
        //pane1.getChildren().addAll(label1, txtfld1, label2, txtfld2);
       // pane1.setSpacing(20.0);
 	   Button ok = new Button("OK");
 	   ok.setOnAction(e -> makeElement(txtfld1, txtfld2));
 	   pane1.add(ok, 3, 5);
 	   pane1.setStyle("-fx-background-color:teal;-fx-padding:10px;");
 	   Scene scene1 = new Scene(pane1, 200, 100);
 	   
 	   
 	   //Button newBut = new Button("WOOO");
 	   //newBut.setOnAction(e -> doNothing());
 	   //pane1.getChildren().add(newBut);
 	   Stage newStage = new Stage();
 	   newStage.setScene(scene1);
 	   newStage.setMinWidth(600.0);
 	   newStage.setMinHeight(600.0);
 	  // newStage.setMaxWidth(600.0);
 	   //newStage.initModality(Modality.APPLICATION_MODAL);
 	   newStage.setTitle("Create New Tower");
 	   newStage.show();
 }

    private void selectImage(Tooltip t){
    	System.out.println("WERWERWER");
    	FileChooser imageChoice = new FileChooser();
 	   imageChoice.setTitle("ermage");
        imageChoice.getExtensionFilters()
                .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        ContextMenu prefWindow = new ContextMenu();
        File file = imageChoice.showOpenDialog(prefWindow.getOwnerWindow());
        if (file != null) {
            try {
                String fileName = file.toURI().toURL().toString();
                ImageView image = new ImageView();
                image.setImage(new Image(fileName));
                image.setFitHeight(50.0);
                image.setFitWidth(50.0);
                t.setGraphic(image);
            }
            catch (MalformedURLException e) {
                System.out.println("womp");
            }

        }
    	
    }
    private void makeElement(TextField txt1, TextField txt2){	//prolly make this into a loop
    	System.out.println(txt1.getText());
    	System.out.println(txt2.getText());
    }
    
	
}
