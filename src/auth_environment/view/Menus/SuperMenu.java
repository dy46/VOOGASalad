package auth_environment.view.Menus;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.view.ElementPicker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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

public abstract class SuperMenu extends Menu{
	private GridPane myGridPane;
	private static final String STRING_LABELS_PACKAGE = "auth_environment/properties/string_labels";
	private ResourceBundle myStringBundle = ResourceBundle.getBundle(STRING_LABELS_PACKAGE);
	private ElementPicker myPicker;
	private int index = 0;
	
	public SuperMenu(ElementPicker myPicker){
		this.myPicker = myPicker;
	}
	
	public abstract void createNewElement();
	
	private void addLabels(ResourceBundle myLabelsBundle, Map<String, TextField> StrToTextMap){
	    myGridPane.getColumnConstraints().add(new ColumnConstraints(90));
	 	myGridPane.getColumnConstraints().add(new ColumnConstraints(200));
		Enumeration<String> myKeys = myLabelsBundle.getKeys();	//prolly should split this up into Strings and ints
		StrToTextMap.clear();
		
		while(myKeys.hasMoreElements()){
		 	myGridPane.getRowConstraints().add(new RowConstraints(30));
			String name = myKeys.nextElement();
            myGridPane.add(new Label(myLabelsBundle.getString(name) + ": "), 0, index);
            TextField myTextField = new TextField();
            myGridPane.add(myTextField, 1, index);
            index++;
			StrToTextMap.put(myLabelsBundle.getString(name),myTextField);
		}
	}
	
	private void addButtons(BuildingBlock block){
	 	   Tooltip t = new Tooltip();
	 	   ImageView image = new ImageView();
	 	   image.setImage(new Image("pusheenNoodles.gif"));	//remember to change this later
	 	   t.setGraphic(image);
	 	   Button uploadImage = new Button("Upload Image");
	 	   uploadImage.setOnAction(e -> selectImage(t));
	 	   uploadImage.setPrefWidth(150.0);
	 	   uploadImage.setTooltip(t);
	 	   myGridPane.add(uploadImage, 1, index+2);
	 	   
	 	   Button ok = new Button("OK");
	 	   ok.setOnAction(e -> makeElement(t, block));
	 	   myGridPane.add(ok, 2, index+2);
	}
	
	public abstract void makeElement( Tooltip t, BuildingBlock block);
	
    private void selectImage(Tooltip t){
    	FileChooser imageChoice = new FileChooser();
        imageChoice.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
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
    
    public ElementPicker getPicker(){
    	return myPicker;
    }
    
    public void createElement(ResourceBundle myLabelsBundle, BuildingBlock block, Map<String, TextField> intTextMap, Map<String, TextField> strTextMap){		//va will refactor this later 
 	   index = 0;
 	   myGridPane = new GridPane();
 	    
        addLabels(myLabelsBundle, intTextMap);
        addLabels(myStringBundle, strTextMap);
 	   addButtons(block);

  	   myGridPane.setStyle("-fx-background-color:teal;-fx-padding:10px;");
  	   Scene scene1 = new Scene(myGridPane, 200, 100);
  	   
  	   Stage newStage = new Stage();
  	   newStage.setScene(scene1);
  	   newStage.setMinWidth(350.0);
  	   newStage.setMinHeight(350.0);
  	   newStage.setTitle("Elemental Creator");
  	   newStage.show();
  }
	

}
