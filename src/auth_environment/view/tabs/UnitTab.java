package auth_environment.view.tabs;

import java.util.*;

import auth_environment.Models.ElementTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IElementTabModel;
import auth_environment.view.UnitPicker;
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
	
	private IElementTabModel myElementTabModel;

	public UnitTab(String name, IAuthModel authModel){
		super(name);
		this.myElementTabModel = new ElementTabModel(authModel.getIAuthEnvironment()); 
		addRefresh();
		setUp();
	}
	
	public void refresh(){
        setIndex(1);
		affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
		unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();	
		init();
	}
	
	private void init(){
		strComboMap = new HashMap<String, ComboBox<String>>();
		strTextMap = new HashMap<String, TextField>();
		affectorsToUnit = new ArrayList<ComboBox<String>>();
		affectorsToApply = new ArrayList<ComboBox<String>>();
		myProjectiles = new ArrayList<ComboBox<String>>();
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
					.getString("elementTypes").split(getLabelsBundle().getString("regex"))), getIndex(), 2);
			strComboMap.put(s, cb);
			iterateIndex();
		}
	}

	public void createNewElement() {
		Map<String, List<Double>> strToDoubleMap = new HashMap<String, List<Double>>();	
		String name = strTextMap.remove(getLabelsBundle().getString("typeText")).getText();

    	for(String str: strTextMap.keySet()){
    		List<Double> val = new ArrayList<Double>();  		
    		String[] strings = strTextMap.get(str).getText().trim().split(getLabelsBundle().getString("regex"));
    		Arrays.asList(strings).stream().forEach(s -> val.add(Double.parseDouble(s)));
    		strToDoubleMap.put(str, val);
    	}
  	
    	createUnit(name, strToDoubleMap, comboListToStringList(myProjectiles),
    			comboListToStringList(affectorsToUnit), comboListToStringList(affectorsToApply));
    	setUp();
	}
	
	private void createUnit(String name, Map<String, List<Double>> map, List<String> projectiles, List<String> atu, List<String> ata){
		try{
			UnitFactory myUnitFactory = this.myElementTabModel.getUnitFactory();
			Unit unit = myUnitFactory.createUnit(name,
	    		strComboMap.get(getLabelsBundle().getString("unitComboProperties")).getValue(), map, projectiles, atu, ata);
			myAnimationPane.getAnimationLoaderTab().setUnit(unit);
			myUnitFactory.getUnitLibrary().addUnit(unit);
		}
		catch(NumberFormatException e){
			return;
		}
	}

	public void addFields(GridPane gp) {
		addComboFields(gp);
		addTextFields(gp);
		addTextComboButtonTrio(gp, unitNames, myProjectiles,
				getLabelsBundle().getString("childButton"), getLabelsBundle().getString("childText"));
		addTextComboButtonTrio(gp, affectorNames, affectorsToUnit,
				getLabelsBundle().getString("affectorsButton"), getLabelsBundle().getString("affectorsText"));
		addTextComboButtonTrio(gp, affectorNames, affectorsToApply,
				getLabelsBundle().getString("applyButton"), getLabelsBundle().getString("applyText"));		
	}
	
	private void addTextComboButtonTrio(GridPane gp, List<String> listOfNames, List<ComboBox<String>> comboList, String buttonText, String labelText){
		getCreator().createTextLabels(gp, labelText, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
		ComboBox<String> cbox = getCreator().createStringComboBox(gp, listOfNames, getIndex(), 2);
		comboList.add(cbox);
		int currentInt = getIndex();
		iterateIndex();
		
		Button button = getCreator().createButton(gp, buttonText, getIndex(), 2);
		button.setOnAction(e-> addNewComboBox(currentInt, gp, button, cbox, 2, comboList, listOfNames));
		iterateIndex();
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
	
	public void updateMenu(Unit unit) {}
}
