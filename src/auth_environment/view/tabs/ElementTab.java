package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.ElementTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IElementTabModel;
import auth_environment.view.UnitPicker;
import game_engine.affectors.Affector;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ElementTab extends Tab{
	 
	private Map<String, TextField> strTextMap;
	private List<ComboBox<String>> affectorsToUnit;
	private List<ComboBox<String>> affectorsToApply;
	private List<ComboBox<String>> Projectiles;
	private List<String> affectorNames;
	private List<String> unitNames;
	private int index;
	
	private BorderPane myPane;
	//private IAuthModel myAuthModel;
	//private IAuthEnvironment myInterface;
	private IElementTabModel myElementTabModel;
	
	private static final String LABEL_PACKAGE = "auth_environment/properties/creation_tab_labels";
	private static final ResourceBundle myLabelsBundle = ResourceBundle.getBundle(LABEL_PACKAGE);
	
	private static final String UNIT_PACKAGE = "auth_environment/properties/unit_labels";
	private static final ResourceBundle myUnitBundle = ResourceBundle.getBundle(UNIT_PACKAGE);
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/creation_tab_dimensions";
	private static final ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	public ElementTab(String name, IAuthModel authModel){
		super(name);
		strTextMap = new HashMap<String, TextField>();
		affectorsToUnit = new ArrayList<ComboBox<String>>();
		affectorsToApply = new ArrayList<ComboBox<String>>();
		//this.myInterface = authModel.getIAuthEnvironment();
		Projectiles = new ArrayList<ComboBox<String>>();
		//this.myAuthModel = authModel; 
		this.myElementTabModel = new ElementTabModel(authModel.getIAuthEnvironment()); 
		this.myPane = new BorderPane(); 
		addRefresh();
		init();
	}
	
	private void addRefresh(){
		this.setOnSelectionChanged(l -> init());
	}
	
	private void init(){
		refresh();
		index = 1;
		TitledPane newPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		BorderPane newBorderPane = new BorderPane();
			
		myPane.setLeft(newPane);
		this.setClosable(false);
		
	    UnitPicker up = new UnitPicker(myLabelsBundle.getString("editLabel"), this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnits());
	    up.setClickable(this);
	    
	    myPane.setRight(up.getRoot());
		newPane.setText(myLabelsBundle.getString("newLabel"));
		newPane.setContent(newScrollPane);
		newPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("newPaneWidth")), Double.parseDouble(myDimensionsBundle.getString("newPaneHeight")));
		newPane.setCollapsible(false);
		newScrollPane.setContent(newBorderPane);
		
		AnimationPane newAnimationInfo = new AnimationPane();
		newBorderPane.setTop(newAnimationInfo.getRoot());
		
        GridPane newTableInfo = new GridPane();
        newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("colConstraintSize"))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("colConstraintSize2"))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("colConstraintSize3"))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
        newTableInfo.getRowConstraints().addAll(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
        newTableInfo.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("newTableWidth")), Double.parseDouble(myDimensionsBundle.getString("newTableHeight")));
		newBorderPane.setLeft(newTableInfo);
		GridPane bottomInfo = new GridPane();
		bottomInfo.getColumnConstraints().addAll(new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("bottomInfoCol"))), new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("bottomInfoCol2"))), new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString("bottomInfoCol3"))));
		Button ok = new Button("OK");
		ComboBox<String> unitType = addTextFields(newTableInfo);
		ok.setOnAction(e -> createNewUnit(unitType, newAnimationInfo));
		bottomInfo.add(ok, 2, 0);
		newBorderPane.setBottom(bottomInfo);
        
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        
		newTableInfo.getRowConstraints().add(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
		String wweorpawt  = "Projectiles";
		newTableInfo.add(new Text(wweorpawt), 1, index);
		ComboBox<String> cbox = new ComboBox<String>();
		cbox.getItems().addAll(unitNames);
		newTableInfo.add(cbox, 2, index);
		Projectiles.add(cbox);
		int currentInt = index;
		index++;
		
		Button projectileButton = new Button(myLabelsBundle.getString("addProjectileText"));
		projectileButton.setOnAction(e-> addNewComboBox(currentInt, newTableInfo, projectileButton, cbox, 2, Projectiles, unitNames, new VBox()));
		newTableInfo.add(projectileButton, 2, index);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
		String affectors = "Affector(s) For Unit";
		newTableInfo.add(new Text(affectors), 1, index);
		VBox vbox1 = new VBox();
		ComboBox<String> cbox1 = new ComboBox<String>();
		vbox1.getChildren().add(cbox1);
		
		cbox1.getItems().addAll(affectorNames);
		newTableInfo.add(vbox1, 2, index);
		affectorsToUnit.add(cbox1);
		int currentInt1 = index;
		index++;
		
		Button newAffectorButton = new Button(myLabelsBundle.getString("addAffectorText"));
		newAffectorButton.setOnAction(e-> addNewComboBox(currentInt1, newTableInfo, newAffectorButton, cbox1, 2, affectorsToUnit, affectorNames, vbox1));
		newTableInfo.add(newAffectorButton, 2, index);
		index++;

		newTableInfo.getRowConstraints().add(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
		affectors = "Affector(s) to Apply";
		newTableInfo.add(new Text(affectors), 1, index);
		ComboBox<String> cbox2 = new ComboBox<String>();
		cbox2.getItems().addAll(affectorNames);
		newTableInfo.add(cbox2, 2, index);
		affectorsToApply.add(cbox2);
		int currentInt2 = index;
		index++;
		
		//labels stuff
		
		Button newApplyAffectorButton = new Button(myLabelsBundle.getString("addApplyText"));
		newApplyAffectorButton.setOnAction(e-> addNewComboBox(currentInt2, newTableInfo, newApplyAffectorButton, cbox2, 2, affectorsToApply, affectorNames, vbox1));
		newTableInfo.add(newApplyAffectorButton, 2, index);
		index++;
       
		this.setContent(myPane);
	}

	private void addNewComboBox(int row, GridPane newTableInfo, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list, List<String> names, VBox vbox) {
		if(cbox.getValue() != null){
			int newcol = col + 1;		
			newTableInfo.getRowConstraints().add(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
			newTableInfo.getColumnConstraints().add(new ColumnConstraints(150));
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(names);
//			vbox.getChildren().add(newcbox);
			newTableInfo.add(newcbox, newcol, row);
			list.add(newcbox);
			button.setOnAction(e -> addNewComboBox(row, newTableInfo, button, newcbox, newcol, list, names, vbox));
		}
	}
	
	private void refresh(){
		affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
		unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();	
	}
	
	private ComboBox<String> addTextFields(GridPane newTableInfo) {
		ComboBox<String> cb = new ComboBox<String>();;
		for(String s: myUnitBundle.keySet()){
			newTableInfo.getRowConstraints().add(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
			newTableInfo.add(new Text(myUnitBundle.getString(s)), 1, index);
			if(s.equals("unitText")){
				cb.getItems().addAll("Tower", "Terrain", "Projectile", "Enemy");
				newTableInfo.add(cb, 2, index);
			}
			else{
				TextField myTextField = new TextField();
				newTableInfo.add(myTextField, 2, index);
				strTextMap.put(myUnitBundle.getString(s), myTextField);
			}
			index++;
		}
		
		return cb;
	}

	private void createNewUnit(ComboBox<String> unitType, AnimationPane newAnimationInfo) {
		Map<String, List<Double>> strToDoubleMap = new HashMap<String, List<Double>>();
		
	String name = strTextMap.get("Type").getText();
	strTextMap.remove("Type");
    	for(String str: strTextMap.keySet()){
    		List<Double> val = new ArrayList<Double>();  		
    		String[] strings = strTextMap.get(str).getText().trim().split("\\s+");
    		for(String s: strings){
    		    System.out.println(s);
    			val.add(Double.parseDouble(s));
    		}
    		strToDoubleMap.put(str, val);
    	}
    	
    	List<String> ata = new ArrayList<String>();
    	List<String> atu = new ArrayList<String>();
    	List<String> proj = new ArrayList<String>();
    	
    	for(ComboBox<String> cb: affectorsToApply){
    		ata.add(cb.getValue());
    	}
    	for(ComboBox<String> cb: affectorsToUnit){
    		atu.add(cb.getValue());
    	}
    	for(ComboBox<String> cb: Projectiles){
    		proj.add(cb.getValue());
    	}
   
		UnitFactory myUnitFactory = this.myElementTabModel.getUnitFactory();
	        Unit unit = myUnitFactory.createUnit(name, unitType.getValue(),strToDoubleMap, proj, atu, ata);
    	
    	newAnimationInfo.getAnimationLoaderTab().setUnit(unit);
        myUnitFactory.getUnitLibrary().addUnit(unit);
    	
        myPane.getChildren().clear();
    	init();
	}

	public void updateMenu(Unit unit) {
		//refactor this part
		//strTextMap.get("Unit Type").setText(unit.getName());
		strTextMap.get("Type").setText(unit.getType());
		strTextMap.get("Death Delay").setText(unit.getDeathDelay() + "");
		strTextMap.get("NumFrames").setText(unit.getNumFrames()+"");
		strTextMap.get("Speed").setText(unit.getProperties().getVelocity().getSpeed() +"");//check these 3
		strTextMap.get("Direction").setText(unit.getProperties().getVelocity().getDirection() + ""); //
		strTextMap.get("Price").setText(unit.getProperties().getPrice().getValue()+""); //
		strTextMap.get("State").setText(unit.getProperties().getState().getValue()+"");
		strTextMap.get("Health").setText(unit.getProperties().getHealth().getValue()+"");
		List<Affector> affectors = unit.getAffectors();
		List<Affector> ata = unit.getAffectorsToApply();
		List<Unit> children = unit.getChildren();
		
//		affectorToUnit.clear();
//		for(int a = 0; a < affectors.size(); a++){
//			affectorsToUnit
//		affectorsToUnit.get(0).setValue(affectors.get(0).getName());
//		for(int i = 1; i < affectors.size(); i++){
//			addNewComboBox()
//		}
		
//		private List<ComboBox<String>> affectorsToUnit;
//		private List<ComboBox<String>> affectorsToApply;
//		private List<ComboBox<String>> Projectiles;
		
	}
	

	
}
