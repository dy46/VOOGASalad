package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.ElementTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IElementTabModel;
import auth_environment.view.UnitPicker;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ElementTab extends Tab{
	 
	private Map<String, TextField> strTextMap;
	private List<ComboBox<String>> affectorsToUnit;
	private List<ComboBox<String>> affectorsToApply;
	private List<ComboBox<String>> Projectiles;
	private List<String> affectorNames;
	private List<String> unitNames;
	private int index;
	
	private BorderPane myPane;
	
	private IAuthModel myAuthModel;
	private IAuthEnvironment myInterface;
	private IElementTabModel myElementTabModel;
	
	public ElementTab(String name, IAuthModel authModel){
		super(name);
		strTextMap = new HashMap<String, TextField>();
		affectorsToUnit = new ArrayList<ComboBox<String>>();
		affectorsToApply = new ArrayList<ComboBox<String>>();
		this.myInterface = authModel.getIAuthEnvironment();
		Projectiles = new ArrayList<ComboBox<String>>();
		this.myAuthModel = authModel; 
		this.myElementTabModel = new ElementTabModel(authModel.getIAuthEnvironment()); 
		this.myPane = new BorderPane();
		//this.addRefresh();
		affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
		unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();
		this.setOnSelectionChanged(l -> refresh());
		this.myPane = new BorderPane(); 
		init();
	}
	
//	private void addRefresh() {
//		this.myPane.setOnMouseEntered(e -> this.init());
//	}
	
	private void init(){
		refresh();
		index = 1;
//		this.addRefresh();
		TitledPane newPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		BorderPane newBorderPane = new BorderPane();
		GridPane newAnimationInfo = new GridPane();					
		myPane.setLeft(newPane);
		this.setClosable(false);
		
	    UnitPicker up = new UnitPicker("Edit", this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnits());
	    myPane.setRight(up.getRoot());
		newPane.setText("New");
		newPane.setContent(newScrollPane);
		newPane.setPrefSize(700.0, 800.0);
		newPane.setCollapsible(false);
		newScrollPane.setContent(newBorderPane);
		newBorderPane.setTop(newAnimationInfo);
		
		Button animationButton = new Button("ANIMATION");
		animationButton.setOnAction( e -> System.out.println("ANIMATION"));
		animationButton.setPrefSize(400.0,70.0);

		newAnimationInfo.getColumnConstraints().addAll(new ColumnConstraints(250), new ColumnConstraints(200), new ColumnConstraints(200));
		newAnimationInfo.getRowConstraints().addAll(new RowConstraints(70));
		newAnimationInfo.add(animationButton, 1, 0); //col, row
		
        GridPane newTableInfo = new GridPane();
        newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(100),new ColumnConstraints(150),new ColumnConstraints(200),new ColumnConstraints(100) );
        newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
        newTableInfo.setPrefSize(600, 200);
		newBorderPane.setLeft(newTableInfo);
		GridPane bottomInfo = new GridPane();
		bottomInfo.getColumnConstraints().addAll(new ColumnConstraints(530), new ColumnConstraints(90), new ColumnConstraints(70));
		Button ok = new Button("OK");
		ok.setOnAction(e -> createNewUnit(up));
		bottomInfo.add(ok, 2, 0);
		newBorderPane.setBottom(bottomInfo);
        
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        
        addTextFields(newTableInfo);
        
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String wweorpawt  = "Projectiles";
		newTableInfo.add(new Text(wweorpawt), 1, index);
		ComboBox<String> cbox = new ComboBox<String>();
		cbox.getItems().addAll(unitNames);
		newTableInfo.add(cbox, 2, index);
		Projectiles.add(cbox);
		int currentInt = index;
		index++;
		
		Button projectileButton = new Button("+ Add New Projectile");
		projectileButton.setOnAction(e-> addNewProjectileSpace(currentInt, newTableInfo, projectileButton, cbox, 2, Projectiles));
		newTableInfo.add(projectileButton, 2, index);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String affectors = "Affector(s) For Unit";
		newTableInfo.add(new Text(affectors), 1, index);
		ComboBox<String> cbox1 = new ComboBox<String>();
		
		cbox1.getItems().addAll(affectorNames);
		newTableInfo.add(cbox1, 2, index);
		affectorsToUnit.add(cbox1);
		int currentInt1 = index;
		index++;
		//labels stuff
		
		Button newAffectorButton = new Button("+ Add New Affector");
		newAffectorButton.setOnAction(e-> addNewAffectorSpace(currentInt1, newTableInfo, newAffectorButton, cbox1, 2, affectorsToUnit));
		newTableInfo.add(newAffectorButton, 2, index);
		index++;

		
		////
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		affectors = "Affector(s) to Apply";
		newTableInfo.add(new Text(affectors), 1, index);
		ComboBox<String> cbox2 = new ComboBox<String>();
		cbox2.getItems().addAll(affectorNames);
		newTableInfo.add(cbox2, 2, index);
		affectorsToApply.add(cbox2);
		int currentInt2 = index;
		index++;
		
		//labels stuff
		
		Button newApplyAffectorButton = new Button("+ Add Apply Affector");
		newApplyAffectorButton.setOnAction(e-> addNewAffectorApplySpace(currentInt2, newTableInfo, newApplyAffectorButton, cbox2, 2, affectorsToApply));
		newTableInfo.add(newApplyAffectorButton, 2, index);
		index++;
       
		this.setContent(myPane);
	}
	
	private void addNewAffectorApplySpace(int row, GridPane newTableInfo, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list) {
		if(cbox.getValue() != null){
			int newcol = col + 1;		
			newTableInfo.getColumnConstraints().add(new ColumnConstraints(150));
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(affectorNames);
			newTableInfo.add(newcbox, newcol, row);
			list.add(newcbox);
			button.setOnAction(e -> addNewAffectorApplySpace(row, newTableInfo, button, newcbox, newcol, list));
		}
	}

	private void addNewAffectorSpace(int row, GridPane newTableInfo, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list) {
		if(cbox.getValue() != null){
			int newcol = col + 1;	
			newTableInfo.getColumnConstraints().add(new ColumnConstraints(150));
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(affectorNames);
			newTableInfo.add(newcbox, newcol, row);
			list.add(newcbox);
			button.setOnAction(e -> addNewAffectorSpace(row, newTableInfo, button, newcbox, newcol, list));
		}
		
	}
	
	private void addNewProjectileSpace(int row, GridPane newTableInfo, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list) {
		if(cbox.getValue() != null){
			System.out.println("wat");
			int newcol = col + 1;	
			newTableInfo.getColumnConstraints().add(new ColumnConstraints(150));
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(unitNames);
			newTableInfo.add(newcbox, newcol, row);
			list.add(newcbox);
			button.setOnAction(e -> addNewProjectileSpace(row, newTableInfo, button, newcbox, newcol, list));
		}
		
	}
	
	private void refresh(){
//		System.out.println("I AM REFRESHED");
		
		affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
		unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();	
	}

	private void addTextFields(GridPane newTableInfo) {
//		newTableInfo.getRowConstraints().add(new RowConstraints(30));
//		newTableInfo.add(new Text("UnitType"), 1, index);
//		TextField myTextField = new TextField();
//		newTableInfo.add(myTextField, 2, index);
//		Button typeButton = new Button("ok");
//		newTableInfo.add(typeButton, 3, index);
//		index++;
//		int iterationNum = index;
		//typeButton.setOnAction(e -> setYnitType(myTextField.getText(), iterationNum, newTableInfo));
		
		List<String> myFields = new ArrayList<String>();
		myFields.add("Unit Type");
		myFields.add("Death Delay");
		myFields.add("Type");
		myFields.add("NumFrames");
		myFields.add("Direction");
		myFields.add("Speed");
		myFields.add("Price");
		myFields.add("State");
		myFields.add("Health");
		//UnitProperties unitProp =myUnitFactory.getUnitLibrary().getPropertyByType(type);
		for(String s: myFields){
			newTableInfo.getRowConstraints().add(new RowConstraints(30));
			newTableInfo.add(new Text(s), 1, index);
			TextField myTextField = new TextField();
			newTableInfo.add(myTextField, 2, index);
			strTextMap.put(s, myTextField);
			index++;
		}
		
		
	}
	
	// ^^^ can refaccotttryo vv
	
//	private void setUnitType(String type, int index, GridPane newTableInfo){
//		List<String> myFields = myUnitFactory.getFields();
//		UnitProperties unitProp =myUnitFactory.getUnitLibrary().getPropertyByType(type);
//		for(String s: myFields){
//			newTableInfo.getRowConstraints().add(new RowConstraints(30));
//			newTableInfo.add(new Text(s), 1, index);
//			TextField myTextField = new TextField();
//			newTableInfo.add(myTextField, 2, index);
//			System.out.println("qq");
//			System.out.println(unitProp);
//			Class<?> c = unitProp.getClass();
//			Method[] allMethods = c.getMethods();
//			for(Method m: allMethods){
//				System.out.println(m.getName());
//			}
//				//if(m.getName().startsWith("set" + str)){
//			//}
//			myTextField.setText("1");
//			strTextMap.put(s, myTextField);
//			index++;
//		}
//		
//	}

	private void createNewUnit(UnitPicker up) {
		
		//change to Mapvv
		HashMap<String, String> strToStrMap = new HashMap<String, String>();
		String name;
    	for(String str: strTextMap.keySet()){
    			strToStrMap.put(str, strTextMap.get(str).getText());
    			strTextMap.get(str).clear();
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
//    	Unit unit = myUnitFactory.createUnit(strToStrMap);	
    	System.out.println(strToStrMap);
    	System.out.println(proj);
    	System.out.println(atu);
    	System.out.println(ata);
    	Unit unit = myUnitFactory.createUnit(strToStrMap, proj, atu, ata);
    	
//    	up.add(unit, this);
    	
    	myPane.getChildren().clear();
    	init();
	}

//	private void addNewApplyAffectorSpace(GridPane newTableInfo, Button AffectorButton, ComboBox cbox) {
//		if(cbox.getValue() != null){
//			newTableInfo.getChildren().remove(AffectorButton);
//			ComboBox<String> newcbox = new ComboBox<String>();
//			newcbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
//			newTableInfo.add(newcbox, 2, index);
//			index++;
//			Button newAffectorButton = new Button("+ Add New Affector");
//			newAffectorButton.setOnAction(e-> addNewApplyAffectorSpace(newTableInfo, newAffectorButton, newcbox));
//			newTableInfo.add(newAffectorButton, 2, index);
//			affectorsToApply.add(newcbox);
//		}
//	}
	
	//there has to be a better way to do this omg
	//ok make a border pane
	// with just the affectors
	// and then have some on left some on right ok cool
//	private void addNewAffectorSpace(GridPane newTableInfo, Button AffectorButton, ComboBox cbox) {
//		if(cbox.getValue() != null){
//			newTableInfo.getChildren().remove(AffectorButton);
//			ComboBox<String> newcbox = new ComboBox<String>();
//			newcbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
//			newTableInfo.add(newcbox, 2, index);
//			index++;
//			Button newAffectorButton = new Button("+ Add New Affector");
//			newAffectorButton.setOnAction(e-> addNewAffectorSpace(newTableInfo, newAffectorButton, newcbox));
//			newTableInfo.add(newAffectorButton, 2, index);
//			affectorsToUnit.add(newcbox);
//		}
//	}

	public void updateMenu(Unit unit) {
		//refactor this part
		strTextMap.get("Health").setText(unit.getProperties().getHealth().getValue()+"");
		strTextMap.get("Unit Type").setText(unit.getName());
		strTextMap.get("Type").setText(unit.getType());
		strTextMap.get("Team").setText(unit.getProperties().getTeam().getTeam()+ "");
		strTextMap.get("Initial Speed").setText(unit.getProperties().getVelocity().getSpeed() +"");
		strTextMap.get("Initial Direction").setText(unit.getProperties().getVelocity().getDirection() + "");
		strTextMap.get("Price").setText(unit.getProperties().getPrice().getValue()+"");
		strTextMap.get("State").setText(unit.getProperties().getState().getValue()+"");
		strTextMap.get("Mass").setText(unit.getProperties().getMass().getMass()+"");
	}
	

	
}
