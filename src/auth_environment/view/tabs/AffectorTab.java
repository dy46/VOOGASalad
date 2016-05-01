package auth_environment.view.tabs;

import java.util.*;

import auth_environment.Models.AffectorTabModel;
import auth_environment.Models.Interfaces.IAffectorTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.UnitPicker;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AffectorTab extends ElementTab{
        
        private Map<String, TextField> strTextMap;
        private Map<String, ComboBox<String>> strDropMap;
        private List<ComboBox<String>> effects;
        private List<TextField> functions;
        
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
             return new UnitPicker(getLabelsBundle().getString("editLabel"));    
        }
        
        public void setUpAnimation(BorderPane bp){}
        
        public void createNewElement() {
                try{
                        String name = strTextMap.get(getLabelsBundle().getString("affectorTextFields").split(getLabelsBundle().getString("regex"))[0]).getText();
                        String type = getLabelsBundle().getString("packageName") +
                                strDropMap.get(getLabelsBundle().getString("affectorComboBoxes").split(getLabelsBundle().getString("regex"))[0]).getValue() +
                                getLabelsBundle().getString("affectorText");
                        int ttl = Integer.parseInt(strTextMap.get(getLabelsBundle().
                                getString("affectorTextFields").split(getLabelsBundle().getString("regex"))[1]).getText());
                        String property = strDropMap.get(getLabelsBundle().getString("affectorComboBoxes").
                                split(getLabelsBundle().getString("regex"))[1]).getValue();
                
                        this.myAffectorTabModel.getAffectorFactory().constructAffector(name, type, property, ttl,
                                comboListToStringList(effects), textFieldListToStringList(functions));
                        setUp();
                }
                catch(NumberFormatException e){
                        return;
                }
        }

        public void addFields(GridPane gp) {
                addTextFields(gp);
                addComboBoxes(gp);
                addComboTextButtonTrio(gp);
        }
        
        private void addTextFields(GridPane gp){
                for(String s: Arrays.asList(getLabelsBundle().getString("affectorTextFields").split(getLabelsBundle().getString("regex")))){
                        getCreator().createTextLabels(gp, s, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
                        strTextMap.put(s, getCreator().createTextField(gp, getIndex(), 2));
                        iterateIndex();
                }
        }
        
        private void addComboBoxes(GridPane gp){
                for(String s: Arrays.asList(getLabelsBundle().getString("affectorComboBoxes").split(getLabelsBundle().getString("regex")))){
                        getCreator().createTextLabels(gp, s, getIndex(), 1, Double.parseDouble(getDimensionsBundle().getString("rowConstraintSize")));
                        strDropMap.put(s, getCreator().createStringComboBox(gp, Arrays.asList(
                                        getLabelsBundle().getString(s).split(getLabelsBundle().getString("regex"))), getIndex(), 2));
                        iterateIndex();
                }
        }
        
        private ComboBox<String> addEffects(GridPane gp){
                ComboBox<String> effectsCBox = getCreator().createStringComboBox(gp, Arrays.asList(
                                getLabelsBundle().getString("Functions").split(getLabelsBundle().getString("regex"))), getIndex(), 2);
                effects.add(effectsCBox);
                return effectsCBox;
        }
        
        private TextField addFunction(GridPane gp){
                TextField functionText = getCreator().createTextField(gp, getIndex(), 3);
                functions.add(functionText);
                iterateIndex();
                return functionText;
        }
        
        private void createEffectButton(GridPane gp, ComboBox<String> effectsCBox, TextField functionText){
                Button button = getCreator().createButton(gp, getLabelsBundle().getString("addEffectText"), getIndex(), 2);
                button.setOnAction(e -> addNewEffect(button, gp, effectsCBox, functionText));
        }
        
        private void addComboTextButtonTrio(GridPane gp){
                createEffectButton(gp, addEffects(gp), addFunction(gp));
        }

        private void addNewEffect(Button button, GridPane gp, ComboBox<String> cbox, TextField txtfld) {
                if(cbox.getValue() != null && !txtfld.getText().equals("")){
                        gp.getChildren().remove(button);
                        addComboTextButtonTrio(gp);
                }
        }
}