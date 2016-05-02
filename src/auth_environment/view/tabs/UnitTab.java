package auth_environment.view.tabs;

//refactor the addCombo and addTextFields, they are the same for unit and affector tab


import java.util.*;

import auth_environment.Models.ElementTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IElementTabModel;
import auth_environment.view.UnitPicker;
import game_engine.affectors.Affector;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class UnitTab extends ElementTab{
	 
	private Map<String, TextField> strTextMap;
	private Map<String, ComboBox<String>> strComboMap;
	private List<ComboBox<String>> affectorsToUnit;
	private List<ComboBox<String>> affectorsToApply;
	private List<ComboBox<String>> myProjectiles;
	private List<String> affectorNames;
	private List<String> unitNames;
	private AnimationPane myAnimationPane;
	
	private Button proj;
	private Button a;
	private Button apply;

	private IElementTabModel myElementTabModel;
	private IAuthModel myAuthModel;

	public UnitTab(String name, IAuthModel authModel){
		super(name);
		this.myAuthModel = authModel;
		this.myElementTabModel = new ElementTabModel(authModel.getIAuthEnvironment()); 
		addRefresh();
		setUp();
	}
	
	public void refresh(){
        setIndex(1);
		refreshLists();
		
		init();
	}
	
	private void init(){
		strComboMap = new HashMap<String, ComboBox<String>>();
		strTextMap = new HashMap<String, TextField>();
		affectorsToUnit = new ArrayList<>();
		affectorsToApply = new ArrayList<>();
		myProjectiles = new ArrayList<>();
		myAnimationPane = new AnimationPane();
	}
	
	private void addRefresh(){
		this.setOnSelectionChanged(l -> setUp());
	}
	
	public UnitPicker setUpUnitPicker(){
	    UnitPicker up = new UnitPicker(getLabelsBundle().getString("editLabel"),
	    		this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnits());
	    up.setClickable(this);
	    return up;
	}
	
	public void setUpAnimation(BorderPane gp){
		gp.setTop(myAnimationPane.getRoot());
	}
	
	private void addTextFields(GridPane gp){
		for(String s: Arrays.asList(getLabelsBundle().getString("unitTextProperties").split(getLabelsBundle().getString("regex")))){
			getCreator().createTextLabels(gp, s, getIndex(), 1,
					Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
			strTextMap.put(s, getCreator().createTextField(gp, getIndex(), 2));
			iterateIndex();
		}
	}
	
	private void addComboFields(GridPane gp){
		for(String s: Arrays.asList(getLabelsBundle().getString("unitComboProperties").split(getLabelsBundle().getString("regex")))){
			getCreator().createTextLabels(gp, s , getIndex(), 1,
					Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
			ComboBox<String> cb = getCreator().createStringComboBox(gp, Arrays.asList(getLabelsBundle()
					.getString(s).split(getLabelsBundle().getString("regex"))), getIndex(), 2);
			strComboMap.put(s, cb);
			iterateIndex();
		}
	}

	public void createNewElement() {
		try{
			Map<String, List<Double>> strToDoubleMap = new HashMap<String, List<Double>>();	
			String name = strTextMap.remove(getLabelsBundle().getString("typeText")).getText();

			for(String str: strTextMap.keySet()){
				List<Double> val = new ArrayList<>();  		
				String[] strings = strTextMap.get(str).getText().trim().split(getLabelsBundle().getString("regex"));
				Arrays.asList(strings).stream().forEach(s -> val.add(Double.parseDouble(s)));
				strToDoubleMap.put(str, val);
			}
    	
			ComboBox<String> cb = strComboMap.get("State");
			List<Double> list = new ArrayList<Double>();
			double state = cb.getItems().indexOf(cb.getValue());
			if(state < 0 || state > 5){
				state = 0;
			}
					
			list.add(state);
			strToDoubleMap.put("State", list);
  	
			createUnit(name, strToDoubleMap, comboListToStringList(myProjectiles),
    			comboListToStringList(affectorsToUnit), comboListToStringList(affectorsToApply));
			setUp();
		}
		catch(NumberFormatException e){
			return;
		}
	}
	
	private void createUnit(String name, Map<String, List<Double>> map, List<String> projectiles, List<String> atu, List<String> ata){
			UnitFactory myUnitFactory = this.myElementTabModel.getUnitFactory();
			Unit unit = myUnitFactory.createUnit(name,
	    		strComboMap.get(getLabelsBundle().getString("unitTypeText")).getValue(), map, projectiles, atu, ata);
			myAnimationPane.getAnimationLoaderTab().setUnit(unit);
			System.out.println(unit.getProperties().getState().getValue());
			myUnitFactory.getUnitLibrary().addUnit(unit);
	}

	public void addFields(GridPane gp) {
		addComboFields(gp);
		addTextFields(gp);
		proj = addTextComboButtonTrio(gp, unitNames, myProjectiles,
				getLabelsBundle().getString("childButton"), getLabelsBundle().getString("childText"));
		a = addTextComboButtonTrio(gp, affectorNames, affectorsToUnit,
				getLabelsBundle().getString("affectorsButton"), getLabelsBundle().getString("affectorsText"));
		apply =addTextComboButtonTrio(gp, affectorNames, affectorsToApply,
				getLabelsBundle().getString("applyButton"), getLabelsBundle().getString("applyText"));		
	}
	
	private Button addTextComboButtonTrio(GridPane gp, List<String> listOfNames, List<ComboBox<String>> comboList, String buttonText, String labelText){
		getCreator().createTextLabels(gp, labelText, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
		ComboBox<String> cbox = getCreator().createStringComboBox(gp, listOfNames, getIndex(), 2);
		comboList.add(cbox);
		int currentInt = getIndex();
		iterateIndex();
		
		Button button = getCreator().createButton(gp, buttonText, getIndex(), 2);
		button.setOnAction(e-> addNewComboBox(currentInt, gp, button, cbox, 2, comboList, listOfNames));
		iterateIndex();
		
		return button;	
	}
	
	private void addNewComboBox(int row, GridPane gp, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list, List<String> names) {
		if(cbox.getValue() != null){
			int newcol = col + 1;		
			gp.getRowConstraints().add(new RowConstraints(Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize"))));
			gp.getColumnConstraints().add(new ColumnConstraints(150));
			ComboBox<String> newcbox = getCreator().createStringComboBox(gp, names, row, newcol);
			list.add(newcbox);
			button.setOnAction(e -> addNewComboBox(row, gp, button, newcbox, newcol, list, names));
		}
	}
	
	public void removeUnit(Unit u){
		this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnits().remove(u);
		setUp();
	}
	
	private void refreshLists(){
		affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
		unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();	
		myElementTabModel.update(myAuthModel.getIAuthEnvironment());
	}
	
	public void updateMenu(Unit unit) {
		setUp();
			String[] name = unit.getType().split("_");
			strTextMap.get("Type").setText(name[0]);
			strComboMap.get("UnitType").setValue(name[1]);
			strTextMap.get("DeathDelay").setText(unit.getDeathDelay() + "");
			strTextMap.get("NumFrames").setText(unit.getNumFrames()+"");
			strTextMap.get("Speed").setText(unit.getProperties().getVelocity().getSpeed() +"");//check these 3
			strTextMap.get("Direction").setText(unit.getProperties().getVelocity().getDirection() + ""); //
			strTextMap.get("Price").setText(unit.getProperties().getPrice().getValue()+""); //
			strComboMap.get("State").setValue(strComboMap.get("State").getItems().get((int)unit.getProperties().getState().getValue()));
			strTextMap.get("Health").setText(unit.getProperties().getHealth().getValue()+"");
			strTextMap.get("Bounds").setText(unit.getProperties().getBounds().toString());
			strTextMap.get("Range").setText(unit.getProperties().getRange().toString());
			strTextMap.get("TTL").setText(unit.getTTL()+"");
			strTextMap.get("Mass").setText(unit.getProperties().getMass().getMass()+"");
			
			List<Affector> affectors = unit.getAffectors();
			List<Affector> ata = unit.getAffectorsToApply();
			List<Unit> children = unit.getChildren();
			if(affectors.size() > 0){
				affectorsToUnit.get(0).setValue(affectors.get(0).getName());
			}
			if(ata.size() > 0){
				affectorsToApply.get(0).setValue(affectors.get(0).getName());
			}
			if(children.size() > 0){
				myProjectiles.get(0).setValue(affectors.get(0).getName());
			}
			
			for(int i = 1; i < affectors.size(); i++){
				a.fire();
				affectorsToUnit.get(i).setValue(affectors.get(i).getName());
			}
			
			for(int i = 1; i < ata.size(); i++){
				apply.fire();
				affectorsToApply.get(i).setValue(ata.get(i).getName());
			}
			for(int i = 1; i < children.size(); i++){
				proj.fire();
				myProjectiles.get(i).setValue(children.get(i).getName());
			}
	}
}