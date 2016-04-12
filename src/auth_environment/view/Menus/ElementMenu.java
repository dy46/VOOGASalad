package auth_environment.view.Menus;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.view.TowerView;
import game_engine.properties.Bounds;
import game_engine.properties.Damage;
import game_engine.properties.Health;
import game_engine.properties.Position;
import game_engine.properties.Price;
import game_engine.properties.Team;
import game_engine.properties.Velocity;
import java.util.*;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.EnemyBuildingBlock;
import auth_environment.buildingBlocks.TerrainBuildingBlock;
import auth_environment.buildingBlocks.TowerBuildingBlock;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Tower;
import game_engine.libraries.AffectorLibrary;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
	
	private static final String ELEMENT_LABELS_PACKAGE = "auth_environment/properties/labels";
	private static final String TERRAIN_LABELS_PACKAGE = "auth_environment/properties/terrain_labels";
	private static final String ENEMY_LABELS_PACKAGE = "auth_environment/properties/enemy_labels";
	private static final String STRING_LABELS_PACKAGE = "auth_environment/properties/string_labels";
	private ResourceBundle myStringBundle = ResourceBundle.getBundle(STRING_LABELS_PACKAGE);
	
	//private List<TextField> myTextFieldList = new ArrayList<TextField>();
	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
	private GridPane myGridPane;
	private int index;
	private List<Tower> myTowerList = new ArrayList<Tower>();
	
	
	public ElementMenu() {
		this.init();
	}
	
	private void init() {
		this.setText(this.myNamesBundle.getString("elementMenuLabel"));
		MenuItem towerItem = new MenuItem(this.myNamesBundle.getString("towerItemLabel"));
		MenuItem terrainItem = new MenuItem(this.myNamesBundle.getString("terrainItemLabel"));
//		MenuItem enemyItem = new MenuItem(this.myNamesBundle.getString("enemyItemLabel"));
//		towerItem.setOnAction(e -> createNewTower());
//		terrainItem.setOnAction(e -> createNewTerrain());
//		enemyItem.setOnAction(e -> createNewEnemy());
//		this.getItems().addAll(towerItem, terrainItem, enemyItem); 
		MenuItem viewTowerItem = new MenuItem(this.myNamesBundle.getString("viewTowerLabel")); 
		viewTowerItem.setOnAction(e -> viewTowers());
		towerItem.setOnAction(e -> createNewTower());
		terrainItem.setOnAction(e -> createNewTerrain());
		this.getItems().addAll(towerItem, terrainItem, viewTowerItem); 
	}
	
	private void viewTowers() {
		TowerView t = new TowerView(); 
		t.show();
	}
	
	private void createNewEnemy() {
		//make the resource bundles
		ResourceBundle myEnemiesBundle = ResourceBundle.getBundle(ENEMY_LABELS_PACKAGE);
		createNewElement(myEnemiesBundle, new EnemyBuildingBlock());
	}

	public void createNewTower(){
		ResourceBundle myElementLabelsBundle = ResourceBundle.getBundle(ELEMENT_LABELS_PACKAGE);
		createNewElement(myElementLabelsBundle, new TowerBuildingBlock());
	}
	
	public void createNewTerrain(){
		ResourceBundle myTerrainLabelsBundle = ResourceBundle.getBundle(TERRAIN_LABELS_PACKAGE);
		createNewElement(myTerrainLabelsBundle, new TerrainBuildingBlock());
	}
	
	
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
	
	
   private void createNewElement(ResourceBundle myLabelsBundle, BuildingBlock block){		//va will refactor this later 
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
    
    private void makeElement(Tooltip t, BuildingBlock block){
    	Class<?> c = block.getClass();
    	for(String str: strTextMap.keySet()){
    		block.setMyName(strTextMap.get(str).getText());
    	}
    	for(String str: intTextMap.keySet()){
    		System.out.println(str + " " + intTextMap.get(str).getText());
    		Method[] allMethods = c.getMethods();
    		
    		for(Method m: allMethods){;
//    			String[] mString = m.getName().split(".");
//    			System.out.println(m.getName());
//    			System.out.println("setMy" + str);
    			if(m.getName().startsWith("setMy" + str)){
    				try {
						m.invoke(block,Double.parseDouble(intTextMap.get(str).getText()));
						System.out.println("check");
						break;

					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println(str);
					}
    			}
    		}	
    	}
    	
    	block.setMyImage((ImageView)t.getGraphic());
    	
    	TowerFactory towerFac = new TowerFactory(new AffectorLibrary());
    	Tower tower = towerFac.defineTowerModel(block);
    	
    	myTowerList.add(tower);
    	//call Cody's method
    	//and add to list
    	//and then update my Picker
    	
    }
}
