package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.Models.AffectorTabModel;
import auth_environment.Models.Interfaces.IAffectorTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.view.UnitPicker;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AffectorTab extends Tab{
	
	private Map<String, TextField> strTextMap;
	private Map<String, ComboBox<String>> strDropMap;
	private List<ComboBox<String>> effects = new ArrayList<ComboBox<String>>();
	private List<TextField> functions = new ArrayList<TextField>();
	
	private static final String NAME_PACKAGE = "auth_environment/properties/property_name";
	private static final ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAME_PACKAGE);
	
	private int index;
	
	private BorderPane myPane; 
	
//	private IAuthModel myAuthModel; // passed around b/w all tabs
	//private IAuthEnvironment myInterface; // came out of the above
	private IAffectorTabModel myAffectorTabModel; // kept ONLY in this class
	
	public AffectorTab(String name, IAuthModel authModel){
		super(name);
		strTextMap = new HashMap<String, TextField>();
		strDropMap = new HashMap<String, ComboBox<String>>();
//		this.myAuthModel = authModel; 
		//this.myInterface = this.myAuthModel.getIAuthEnvironment();
		this.myAffectorTabModel = new AffectorTabModel(authModel.getIAuthEnvironment());
		this.myPane = new BorderPane();
		this.init();
	}
	
	private void init() {
		index = 1;
		TitledPane newPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		BorderPane newBorderPane = new BorderPane();
		myPane.setLeft(newPane);
		
		this.setClosable(false);
		
	    UnitPicker up = new UnitPicker("Edit");
	    myPane.setRight(up.getRoot());
		newPane.setText("New");
		newPane.setContent(newScrollPane);
		newPane.setPrefSize(700.0, 800.0);
		newPane.setCollapsible(false);
		newScrollPane.setContent(newBorderPane);
		newScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

        GridPane newTableInfo = new GridPane();
        newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(100),new ColumnConstraints(150),new ColumnConstraints(200),new ColumnConstraints(100) );
        newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
        newTableInfo.setPrefSize(600, 200);
		newBorderPane.setLeft(newTableInfo);
		GridPane bottomInfo = new GridPane();
		bottomInfo.getColumnConstraints().addAll(new ColumnConstraints(530), new ColumnConstraints(90), new ColumnConstraints(70));
		Button ok = new Button("OK");
		ok.setOnAction(e -> createNewAffector());
		bottomInfo.add(ok, 2, 0);
		newBorderPane.setBottom(bottomInfo);
        
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        
        addTextFields(newTableInfo);

		this.setContent(myPane);
	}
	
	private void createNewAffector() {
		String name = strTextMap.get("Name").getText();
		String type = "game_engine.affectors." + strDropMap.get("Iteration Type?").getValue() + "Affector";
		strTextMap.remove("Name");
		int ttl = Integer.parseInt(strTextMap.get("TTL").getText());
		
		String property = effects.remove(0).getValue();
		List<String> eff = new ArrayList<String>();
		
		for(ComboBox<String> e: effects){
			eff.add(e.getValue());
		}
		
		List<Double> values = new ArrayList<Double>();
		for(TextField f: functions){
			values.add(Double.parseDouble(f.getText()));
		}
		this.myAffectorTabModel.getAffectorFactory().constructAffector(name, type, property, ttl, eff, values);

		myPane.getChildren().clear();
		init();
	}

	private void addTextFields(GridPane newTableInfo) {
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s = "Name";
		newTableInfo.add(new Text(s), 1, index);
		TextField myTextField = new TextField();
		newTableInfo.add(myTextField, 2, index);
		strTextMap.put(s, myTextField);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s1 = "TTL";
		newTableInfo.add(new Text(s1), 1, index);
		TextField myTextField1 = new TextField();
		newTableInfo.add(myTextField1, 2, index);
		strTextMap.put(s1, myTextField1);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s2 = "Iteration Type?";
		newTableInfo.add(new Text(s2), 1, index);
		ComboBox<String> dropit = new ComboBox<String>();
		newTableInfo.add(dropit, 2, index);
		// TODO: extract to properties file
		dropit.getItems().addAll("AIPathFollow", "BasicSet", "BasicDecrement",
		                         "BasicIncrement", "BasicSet", "CursorDirection", 
		                         "FiringChildren", "HomingMove", "PathFollow", 
		                         "PathFollowPositionMove", "PositionHoming", "PositionMove", 
		                         "RandomPathFollow", "RangeConstantPositionMove", 
		                         "RangePathFollowPositionMove", "SingleTrackRange");
		strDropMap.put(s2, dropit);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s3 = "Effects";
		newTableInfo.add(new Text(s3), 1, index);
		ComboBox<String> txtfld = new ComboBox<String>();
		for(String key: myNamesBundle.keySet()){
			txtfld.getItems().add(myNamesBundle.getString(key));
		}
		newTableInfo.add(txtfld, 2, index);
		effects.add(txtfld);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		ComboBox<String> txtfld1 = new ComboBox<String>();
		txtfld1.getItems().addAll("Constant", "Exponential");
		newTableInfo.add(txtfld1, 2, index);
		effects.add(txtfld1);
		
		TextField txtfld2 = new TextField();
		newTableInfo.add(txtfld2, 3, index);
		functions.add(txtfld2);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		Button buuton = new Button("+ New Effect");	//change
		buuton.setOnAction(e -> addNewEffect(buuton, newTableInfo, txtfld, txtfld2));		//change
		newTableInfo.add(buuton, 2, index);
	}

	private void addNewEffect(Button button, GridPane newTableInfo, ComboBox<String> txtfld3, TextField txtfld4) {
		if(txtfld3.getValue() != null && !txtfld4.getText().equals("")){
		newTableInfo.getChildren().remove(button);
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		ComboBox<String> txtfld1 = new ComboBox<String>();
		txtfld1.getItems().addAll("Constant", "Exponential");
		newTableInfo.add(txtfld1, 2, index);
		effects.add(txtfld1);
		TextField txtfld2 = new TextField();
		newTableInfo.add(txtfld2, 3, index);
		functions.add(txtfld2);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		newTableInfo.add(button, 2, index);
		}
	}
}
