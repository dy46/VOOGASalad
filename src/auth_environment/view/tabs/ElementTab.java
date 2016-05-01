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

public class ElementTab extends UnitTab{
         
        private Map<String, TextField> strTextMap;
        private Map<String, ComboBox<String>> strComboMap;
        private List<ComboBox<String>> affectorsToUnit;
        private List<ComboBox<String>> affectorsToApply;
        private List<ComboBox<String>> myProjectiles;
        private List<String> affectorNames;
        private List<String> unitNames;
        AnimationPane myAnimationPane;
        
        private IElementTabModel myElementTabModel;

        public ElementTab(String name, IAuthModel authModel){
                super(name);
                strComboMap = new HashMap<String, ComboBox<String>>();
                strTextMap = new HashMap<String, TextField>();
                affectorsToUnit = new ArrayList<ComboBox<String>>();
                affectorsToApply = new ArrayList<ComboBox<String>>();
                myProjectiles = new ArrayList<ComboBox<String>>();
                myAnimationPane = new AnimationPane();
                this.myElementTabModel = new ElementTabModel(authModel.getIAuthEnvironment()); 
                addRefresh();
                setUp();
        }
        
        private void addRefresh(){
                this.setOnSelectionChanged(l -> setUp());
        }
        
        public UnitPicker setUpUnitPicker(){
            UnitPicker up = new UnitPicker(getLabelsBundle().getString("editLabel"), this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnits());
            up.setClickable(this);
            return up;
        }
        
        public void setUpAnimation(BorderPane gp){
                gp.setTop(myAnimationPane.getRoot());
        }
        
        public void refresh(){
        reset();
        setIndex(1);
                affectorNames = this.myElementTabModel.getAffectoryFactory().getAffectorLibrary().getAffectorNames();
                unitNames = this.myElementTabModel.getUnitFactory().getUnitLibrary().getUnitNames();    
        }
        
        private void addTextFields(GridPane newTableInfo, FrontEndCreator creator){
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

        public void createNewElement() {
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
        System.out.println(affectorsToApply.size());
   
                UnitFactory myUnitFactory = this.myElementTabModel.getUnitFactory();
            Unit unit = myUnitFactory.createUnit(name, strComboMap.get("UnitType").getValue(), strToDoubleMap, projectiles, atu, ata);
        
        myAnimationPane.getAnimationLoaderTab().setUnit(unit);
        myUnitFactory.getUnitLibrary().addUnit(unit);
        setUp();
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
        public void addFields(GridPane gp, FrontEndCreator creator) {
                addTextFields(gp, creator);
                addTextComboButtonTrio(gp, creator, unitNames, myProjectiles, getLabelsBundle().getString("childButton") ,getLabelsBundle().getString("childText"));
                addTextComboButtonTrio(gp, creator, affectorNames, affectorsToUnit, getLabelsBundle().getString("affectorsButton"), getLabelsBundle().getString("affectorsText"));
                addTextComboButtonTrio(gp, creator, affectorNames, affectorsToApply, getLabelsBundle().getString("applyButton"), getLabelsBundle().getString("applyText"));             
        }
        
        private void addTextComboButtonTrio(GridPane gp, FrontEndCreator creator, List<String> listOfNames, List<ComboBox<String>> comboList, String buttonText, String labelText){
                creator.createTextLabels(gp, labelText, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
                ComboBox<String> cbox = creator.createStringComboBox(gp, listOfNames, getIndex(), 2);
                comboList.add(cbox);
                int currentInt = getIndex();
                iterateIndex();
                
                Button button = creator.createButton(gp, buttonText, getIndex(), 2);
                button.setOnAction(e-> addNewComboBox(currentInt, gp, button, cbox, 2, comboList, listOfNames, creator));
                iterateIndex();
        }
        
        private void addNewComboBox(int row, GridPane gp, Button button, ComboBox<String> cbox, int col, List<ComboBox<String>> list, List<String> names, FrontEndCreator creator) {
                if(cbox.getValue() != null){
                        int newcol = col + 1;           
                        gp.getRowConstraints().add(new RowConstraints(Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize"))));
                        gp.getColumnConstraints().add(new ColumnConstraints(150));
                        ComboBox<String> newcbox = creator.createStringComboBox(gp, names, row, newcol);
                        list.add(newcbox);
                        button.setOnAction(e -> addNewComboBox(row, gp, button, newcbox, newcol, list, names, creator));
                }
        }
}