package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.FrontEndCreator;
import auth_environment.Models.ElementTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IElementTabModel;
import auth_environment.view.UnitPicker;
import game_engine.affectors.Affector;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Text;

public class ElementTab extends UnitTab{
	 
	private Map<String, TextField> strTextMap;
	private Map<String, ComboBox<String>> strComboMap;
	private List<ComboBox<String>> affectorsToUnit;
	private List<ComboBox<String>> affectorsToApply;
	private List<ComboBox<String>> myProjectiles;
	private List<String> affectorNames;
	private List<String> unitNames;
	
	private IElementTabModel myElementTabModel;

	public ElementTab(String name, IAuthModel authModel){
		super(name);
		strComboMap = new HashMap<String, ComboBox<String>>();
		strTextMap = new HashMap<String, TextField>();
		affectorsToUnit = new ArrayList<ComboBox<String>>();
		affectorsToApply = new ArrayList<ComboBox<String>>();
		myProjectiles = new ArrayList<ComboBox<String>>();
		
		this.myElementTabModel = new ElementTabModel(authModel.getIAuthEnvironment()); 
		addRefresh();
		setUp();
		//init();
	}
	
	private void addRefresh(){
		this.setOnSelectionChanged(l -> setUp());
	}
	
	public UnitPicker setUpUnitPicker(){
	    UnitPicker up = new UnitPicker(getLabelsBundle().getString("editLabel"), this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnits());
	    up.setClickable(this);
	    return up;
	}
	
	
	private void init(){
        

	}
	
	public void setUpAnimation(BorderPane gp){
		AnimationPane newAnimationInfo = new AnimationPane();
		gp.setTop(newAnimationInfo.getRoot());
	}

	private void addNewComboBox(int row, GridPane newTableInfo, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list, List<String> names) {
		if(cbox.getValue() != null){
			int newcol = col + 1;		
			newTableInfo.getRowConstraints().add(new RowConstraints(Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize"))));
			newTableInfo.getColumnConstraints().add(new ColumnConstraints(150));
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll(names);
//			vbox.getChildren().add(newcbox);
			newTableInfo.add(newcbox, newcol, row);
			list.add(newcbox);
			button.setOnAction(e -> addNewComboBox(row, newTableInfo, button, newcbox, newcol, list, names));
		}
	}
	
	public void refresh(){
        reset();
        setIndex(1);
		affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
		unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();	
	}
	
	private void addFields(GridPane newTableInfo, FrontEndCreator creator){
		for(String s: Arrays.asList(getLabelsBundle().getString("unitTextProperties").split(getLabelsBundle().getString("regex")))){
			creator.createTextLabels(newTableInfo, s, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
			strTextMap.put(s, creator.createTextField(newTableInfo, getIndex(), 2));
			iterateIndex();
		}
		for(String s: Arrays.asList(getLabelsBundle().getString("unitComboProperties").split(getLabelsBundle().getString("regex")))){
			creator.createTextLabels(newTableInfo, s , getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
			ComboBox<String> cb = creator.createStringComboBox(newTableInfo, Arrays.asList(getLabelsBundle().getString("elementTypes").split(getLabelsBundle().getString("regex"))), getIndex(), 2);
			strComboMap.put(s, cb);
			iterateIndex();
		}

	}

	public void createNewElement(AnimationPane newAnimationInfo) {
		Map<String, List<Double>> strToDoubleMap = new HashMap<String, List<Double>>();
		
		String name = strTextMap.get("Type").getText();
		strTextMap.remove("Type");

    	for(String str: strTextMap.keySet()){
    		List<Double> val = new ArrayList<Double>();  		
    		String[] strings = strTextMap.get(str).getText().trim().split(getLabelsBundle().getString("regex"));
    		for(String s: strings){
    			val.add(Double.parseDouble(s));
    		}
    		strToDoubleMap.put(str, val);
    	}
    	
    	List<String> ata = new ArrayList<String>();
    	List<String> atu = new ArrayList<String>();
    	List<String> projectiles = new ArrayList<String>();
    	
    	affectorsToApply.stream().forEach(s -> ata.add(s.getValue()));
    	affectorsToUnit.stream().forEach(s -> atu.add(s.getValue()));
    	myProjectiles.stream().forEach(s -> projectiles.add(s.getValue()));
   
		UnitFactory myUnitFactory = this.myElementTabModel.getUnitFactory();
	    Unit unit = myUnitFactory.createUnit(name, strComboMap.get("UnitType").getValue(), strToDoubleMap, projectiles, atu, ata);
    	
    	newAnimationInfo.getAnimationLoaderTab().setUnit(unit);
        myUnitFactory.getUnitLibrary().addUnit(unit);
    	init();
	}

	public void updateMenu(Unit unit) {
		strTextMap.get("UnitType").setText(unit.getName());
		strTextMap.get("Type").setText(unit.getType());
		strTextMap.get("DeathDelay").setText(unit.getDeathDelay() + "");
		strTextMap.get("NumFrames").setText(unit.getNumFrames()+"");
		strTextMap.get("Speed").setText(unit.getProperties().getVelocity().getSpeed() +"");//check these 3
		strTextMap.get("Direction").setText(unit.getProperties().getVelocity().getDirection() + ""); //
		strTextMap.get("Price").setText(unit.getProperties().getPrice().getValue()+""); //
		strTextMap.get("State").setText(unit.getProperties().getState().getValue()+"");
		strTextMap.get("Health").setText(unit.getProperties().getHealth().getValue()+"");
		List<Affector> affectors = unit.getAffectors();
		List<Affector> ata = unit.getAffectorsToApply();
		List<Unit> children = unit.getChildren();
	}

	@Override
	public void createNewElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTextFields(GridPane gp, FrontEndCreator creator) {
		// TODO Auto-generated method stub
		addFields(gp, creator);
		gp.getRowConstraints().add(new RowConstraints(Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize"))));
		String wweorpawt  = "Projectiles";
		gp.add(new Text(wweorpawt), 1, getIndex());
		ComboBox<String> cbox = new ComboBox<String>();
		cbox.getItems().addAll(unitNames);
		gp.add(cbox, 2, getIndex());
		myProjectiles.add(cbox);
		int currentInt = getIndex();
		iterateIndex();
		
		Button projectileButton = new Button(getLabelsBundle().getString("addProjectileText"));
		projectileButton.setOnAction(e-> addNewComboBox(currentInt, gp, projectileButton, cbox, 2, myProjectiles, unitNames));
		gp.add(projectileButton, 2, getIndex());
		iterateIndex();
		
		gp.getRowConstraints().add(new RowConstraints(Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize"))));
		String affectors = "Affector(s) For Unit";
		gp.add(new Text(affectors), 1, getIndex());
		ComboBox<String> cbox1 = new ComboBox<String>();
		
		cbox1.getItems().addAll(affectorNames);
		gp.add(cbox1, 2, getIndex());
		affectorsToUnit.add(cbox1);
		int currentInt1 = getIndex();
		iterateIndex();
		
		Button newAffectorButton = new Button(getLabelsBundle().getString("addAffectorText"));
		newAffectorButton.setOnAction(e-> addNewComboBox(currentInt1, gp, newAffectorButton, cbox1, 2, affectorsToUnit, affectorNames));
		gp.add(newAffectorButton, 2, getIndex());
		iterateIndex();

		getCreator().createTextLabels(gp, getLabelsBundle().getString("applyText") ,getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
		ComboBox<String> applyCombo = getCreator().createStringComboBox(gp, affectorNames, getIndex(), 2);
		affectorsToApply.add(applyCombo);
		int currentInt2 = getIndex();
		iterateIndex();
		
		Button newApplyAffectorButton = getCreator().createButton(gp, getLabelsBundle().getString("addApplyText"), getIndex(), 2);
		newApplyAffectorButton.setOnAction(e-> addNewComboBox(currentInt2, gp, newApplyAffectorButton, applyCombo, 2, affectorsToApply, affectorNames));
		iterateIndex();
		
	}
}
