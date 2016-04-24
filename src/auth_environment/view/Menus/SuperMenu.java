//
//package auth_environment.view.Menus;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.util.*;
//
//import auth_environment.backend.FactoryController;
//import auth_environment.buildingBlocks.BuildingBlock;
//import auth_environment.view.ElementPicker;
//import game_engine.factories.*;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.stage.*;
//import javafx.stage.FileChooser.ExtensionFilter;
//
//public abstract class SuperMenu extends Menu{
//	private GridPane myGridPane;
//	private ElementPicker myPicker;
//	private int index = 0;
//	private FunctionFactory myFunctionFactory;
//	private AffectorFactory myAffectorFactory;
//	private EnemyFactory myEnemyFactory;
//	private TowerFactory myTowerFactory;
//	private TerrainFactory myTerrainFactory;
//	private Map<String, List<ComboBox<String>>> meeep;
//
//	private FactoryController myFactoryController;
//
//	public SuperMenu(ElementPicker myPicker) {
//		this.myPicker = myPicker;
//	}
//	public SuperMenu(ElementPicker myPicker, FactoryController factoryController){
//		this.myPicker = myPicker;
//		myFactoryController = factoryController;
//	}
//
//	public abstract void createNewElement();
//
//	private void addLabels(ResourceBundle myLabelsBundle, Map<String, TextField> StrToTextMap){
//		myGridPane.getColumnConstraints().add(new ColumnConstraints(90));
//		myGridPane.getColumnConstraints().add(new ColumnConstraints(200));
//		Enumeration<String> myKeys = myLabelsBundle.getKeys();	//prolly should split this up into Strings and ints
//		StrToTextMap.clear();
//
//		while(myKeys.hasMoreElements()){
//			myGridPane.getRowConstraints().add(new RowConstraints(30));
//			String name = myKeys.nextElement();
//			myGridPane.add(new Label(myLabelsBundle.getString(name) + ": "), 0, index);
//			TextField myTextField = new TextField();
//			myGridPane.add(myTextField, 1, index);
//			index++;
//			StrToTextMap.put(myLabelsBundle.getString(name),myTextField);
//		}
//	}
//
//	private void addButtons(BuildingBlock block){
//	 	   Tooltip t = new Tooltip();
//	 	   ImageView image = new ImageView();
//	 	   image.setImage(new Image("pusheenNoodles.gif"));	//remember to change this later
//	 	   t.setGraphic(image);
//	 	   Button uploadImage = new Button("Upload Image");
//	 	   uploadImage.setOnAction(e -> selectImage(t));
//	 	   uploadImage.setPrefWidth(150.0);
//	 	   uploadImage.setTooltip(t);
//	 	   myGridPane.add(uploadImage, 1, index+2);
//	 	   
//	 	   Button newAffect = new Button("Add New Affector");
//	 	   Button ok = new Button("OK");
//	 	   ok.setOnAction(e -> makeElement(t, block));
//	 	   newAffect.setOnAction(e-> addNewAffectorSpace(newAffect, ok, uploadImage));
//	 	   myGridPane.add(newAffect, 1, index+1);
//	 	   myGridPane.add(ok, 2, index+2);
//	}
//	
//	private void addNewAffectorSpace(Button newAffect, Button ok, Button uploadImage){
//		ComboBox<String> cbox = new ComboBox<String>();
//        cbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
//        myGridPane.add(cbox, 1, index);
//        index++;
//        
//        myGridPane.getChildren().remove(newAffect);
//        myGridPane.getChildren().remove(ok);
//        myGridPane.getChildren().remove(uploadImage);
//        myGridPane.add(newAffect, 1, index++);
//        myGridPane.add(ok, 2, index+2);
//        myGridPane.add(uploadImage, 1, index+2);
//        
//        
//		List<ComboBox<String>> me = meeep.get("Affector");
//		me.add(cbox);
//		meeep.put("Affector", me);
//        
//	}
//	
//
//	public abstract void makeElement( Tooltip t, BuildingBlock block);
//
//
//    private void selectImage(Tooltip t){
//    	FileChooser imageChoice = new FileChooser();
//        imageChoice.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//        ContextMenu prefWindow = new ContextMenu();
//        File file = imageChoice.showOpenDialog(prefWindow.getOwnerWindow());
//        if (file != null) {
//            try {
//                String fileName = file.toURI().toURL().toString();
//                ImageView image = new ImageView();
//                image.setImage(new Image(fileName));
//                image.setFitHeight(50.0);
//                image.setFitWidth(50.0);
//                t.setGraphic(image);
//            }
//            catch (MalformedURLException e) {
//                System.out.println("womp");
//            }
//
//        }
//    	
//    }
//    
//    public ElementPicker getPicker(){
//    	return myPicker;
//    }
//    
//    public void createElement(BuildingBlock block, Map<String, TextField> intTextMap, Map<String, TextField> strTextMap, Map<String, List<ComboBox<String>>> strDragMap, ResourceBundle myLabelsBundle, ResourceBundle myStringBundle, ResourceBundle myDragBundle){		//va will refactor this later 
// 	   index = 0;
// 	   myGridPane = new GridPane();
// 
// 	   addLabels(myLabelsBundle, intTextMap);
//       addLabels(myStringBundle, strTextMap);
//       addDropLabels(myDragBundle, strDragMap);
// 	   addButtons(block);
//
//  	   myGridPane.setStyle("-fx-background-color:teal;-fx-padding:10px;");
//  	   Scene scene1 = new Scene(myGridPane, 200, 100);
//  	   
//  	   Stage newStage = new Stage();
//  	   newStage.setScene(scene1);
//  	   newStage.setMinWidth(350.0);
//  	   newStage.setMinHeight(350.0);
//  	   newStage.setTitle("Elemental Creator");
//  	   newStage.show();
//  }
//    
//    public void addDropLabels(ResourceBundle myDragBundle, Map<String, List<ComboBox<String>>> strDragMap){
//    	meeep = strDragMap;
//	    myGridPane.getColumnConstraints().add(new ColumnConstraints(90));
//	 	myGridPane.getColumnConstraints().add(new ColumnConstraints(200));
//		Enumeration<String> myKeys = myDragBundle.getKeys();	//prolly should split this up into Strings and ints
//		strDragMap.clear();
//		
//		while(myKeys.hasMoreElements()){
//		 	myGridPane.getRowConstraints().add(new RowConstraints(30));
//			String name = myKeys.nextElement();
//            myGridPane.add(new Label(myDragBundle.getString(name) + "(s): "), 0, index);
//            ComboBox<String> cbox = new ComboBox<String>();
//            cbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
//            myGridPane.add(cbox, 1, index);
//            index++;
//            ArrayList<ComboBox<String>> yerp = new ArrayList<ComboBox<String>>();
//            yerp.add(cbox);
//			strDragMap.put(myDragBundle.getString(name),yerp);
//		}
//    	
//    }
//    
//    public FunctionFactory getFunctionFactory(){
//    	return myFunctionFactory;
//    }
//    
//    public TerrainFactory getTerrainFactory(){
//    	return myTerrainFactory;
//    }
//    
//    public AffectorFactory getAffectorFactory(){
//    	return myAffectorFactory;
//    }
//    
//    public EnemyFactory getEnemyFactory(){
//    	return myEnemyFactory;
//    }
//    
//    public TowerFactory getTowerFactory(){
//    	return myTowerFactory;
//    }
//	public FactoryController getFactoryController() {
//		// TODO Auto-generated method stub
//		return myFactoryController;
//	}
//    
////    private String formatType(String type){
////    	type = type.toLowerCase();
////    	return type.substring(0,1).toUpperCase() + type.substring(1);
////    }
//	
////    public List<Unit> getUnitsByType(String type){
////    	type = formatType(type);
////		String instanceVarName = "my" + type + "Factory";
////		Field f = null;
////		try {
////			f = getClass().getDeclaredField(instanceVarName);
////		}
////		catch (NoSuchFieldException | SecurityException e1) {
////			// TODO: womp exception
////			e1.printStackTrace();
////		}
////		f.setAccessible(true);
////		List<Unit> listInstanceVar = null;
////		try {
////			listInstanceVar = (List<Unit>) f.get(this);
////		}
////		catch (IllegalArgumentException | IllegalAccessException e) {
////			// TODO: womp exception
////			e.printStackTrace();
////		}
////		return listInstanceVar;
////	}
//
//}
