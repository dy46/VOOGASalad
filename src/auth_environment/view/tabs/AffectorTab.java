package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.FrontEndCreator;
import auth_environment.Models.AffectorTabModel;
import auth_environment.Models.Interfaces.IAffectorTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.UnitPicker;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AffectorTab extends UnitTab{
	
	private Map<String, TextField> strTextMap;
	private Map<String, ComboBox<String>> strDropMap;
	private List<ComboBox<String>> effects;
	private List<TextField> functions;
	
	//private BorderPane myPane; 
	
	private IAffectorTabModel myAffectorTabModel;
	
	public AffectorTab(String name, IAuthModel authModel){
		super(name);
		strTextMap = new HashMap<String, TextField>();
		strDropMap = new HashMap<String, ComboBox<String>>();
		this.myAffectorTabModel = new AffectorTabModel(authModel.getIAuthEnvironment());
		setUp();
	}
	
	public void refresh(){
		effects = new ArrayList<ComboBox<String>>();
		functions = new ArrayList<TextField>();
	}
	
	public UnitPicker setUpUnitPicker(){
	     return new UnitPicker("Edit");    
	}
	
	public void setUpAnimation(BorderPane bp){}
	
	public void createNewElement() {
		String name = strTextMap.get(getLabelsBundle().getString("affectorTextFields").split(getLabelsBundle().getString("regex"))[0]).getText();
		String type = getLabelsBundle().getString("packageName") + strDropMap.get(getLabelsBundle().getString("affectorComboBoxes").split(getLabelsBundle().getString("regex"))[0]).getValue() + getLabelsBundle().getString("affectorText");
		int ttl = Integer.parseInt(strTextMap.get(getLabelsBundle().getString("affectorTextFields").split(getLabelsBundle().getString("regex"))[1]).getText());
		String property = strDropMap.get(getLabelsBundle().getString("affectorComboBoxes").split(getLabelsBundle().getString("regex"))[1]).getValue();
		
		List<String> eff = new ArrayList<String>();
    	effects.stream().forEach(s -> eff.add(s.getValue()));
		
		List<Double> values = new ArrayList<Double>();
    	functions.stream().forEach(s -> {if(s.getText() != null)values.add(Double.parseDouble(s.getText()));});
    	
		this.myAffectorTabModel.getAffectorFactory().constructAffector(name, type, property, ttl, eff, values);
		setUp();
	}

	public void addFields(GridPane gp, FrontEndCreator creator) {
		for(String s: Arrays.asList(getLabelsBundle().getString("affectorTextFields").split(getLabelsBundle().getString("regex")))){
			creator.createTextLabels(gp, s, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
			strTextMap.put(s, creator.createTextField(gp, getIndex(), 2));
			iterateIndex();
		}
		
		for(String s: Arrays.asList(getLabelsBundle().getString("affectorComboBoxes").split(getLabelsBundle().getString("regex")))){
			creator.createTextLabels(gp, s, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
			strDropMap.put(s, creator.createStringComboBox(gp, Arrays.asList(getLabelsBundle().getString(s).split(getLabelsBundle().getString("regex"))), getIndex(), 2));
			iterateIndex();
		}
		
		ComboBox<String> effectsCBox = creator.createStringComboBox(gp, Arrays.asList(getLabelsBundle().getString("Functions").split(getLabelsBundle().getString("regex"))), getIndex(), 2);
		effects.add(effectsCBox);
		
		TextField txtfld = creator.createTextField(gp, getIndex(), 3);
		functions.add(txtfld);
		iterateIndex();
		
		Button button = creator.createButton(gp, getLabelsBundle().getString("addEffectText"), getIndex(), 2);
		button.setOnAction(e -> addNewEffect(button, gp, effectsCBox, txtfld, creator));		//change
	}

	private void addNewEffect(Button button, GridPane gp, ComboBox<String> cbox, TextField txtfld, FrontEndCreator creator) {
		if(cbox.getValue() != null && !txtfld.getText().equals("")){
			gp.getChildren().remove(button);
		
			ComboBox<String> newcbox = creator.createStringComboBox(gp, Arrays.asList(getLabelsBundle().getString("Functions").split(getLabelsBundle().getString("regex"))), getIndex(), 2);
			effects.add(newcbox);
			TextField newText = creator.createTextField(gp, getIndex(), 3);
			functions.add(newText);
			iterateIndex();
		
			button.setOnAction(e -> addNewEffect(button, gp, newcbox, newText, creator));
			gp.add(button, 2, getIndex());
		}
	}
}
