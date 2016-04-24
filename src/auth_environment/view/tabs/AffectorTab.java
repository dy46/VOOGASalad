package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.view.UnitPicker;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
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
	private IAuthEnvironment myInterface;
	private Map<String, TextField> strTextMap;
	private Map<String, ComboBox<String>> strDropMap;
	List<TextField> effects = new ArrayList<TextField>();
	List<TextField> functions = new ArrayList<TextField>();
	private int index = 1;
	
	public AffectorTab(String name, IAuthEnvironment myInterface){
		super(name);
		strTextMap = new HashMap<String, TextField>();
		strDropMap = new HashMap<String, ComboBox<String>>();
		this.myInterface = myInterface;
		// TODO Auto-generated constructor stub
		BorderPane myPane = new BorderPane();
		TitledPane newPane = new TitledPane();
//		TitledPane editPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
//		ScrollPane editScrollPane = new ScrollPane();
		BorderPane newBorderPane = new BorderPane();
		GridPane newAnimationInfo = new GridPane();					//*****	
//		FlowPane editInfo = new FlowPane();							//*****	
		myPane.setLeft(newPane);
		
		this.setClosable(false);
		
	    UnitPicker up = new UnitPicker("Edit");
	    myPane.setRight(up.getRoot());
		newPane.setText("New");
		newPane.setContent(newScrollPane);
		newPane.setPrefSize(700.0, 800.0);
		newPane.setCollapsible(false);
		newScrollPane.setContent(newBorderPane);
		newBorderPane.setTop(newAnimationInfo);
		newScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
//		Button animationButton = new Button("ANIMATION");
//		animationButton.setOnAction( e -> System.out.println("ANIMATION"));
//		animationButton.setPrefSize(400.0,70.0);

		newAnimationInfo.getColumnConstraints().addAll(new ColumnConstraints(250), new ColumnConstraints(200), new ColumnConstraints(200));
		newAnimationInfo.getRowConstraints().addAll(new RowConstraints(70));
//		newAnimationInfo.add(animationButton, 1, 0); //col, row
		
        GridPane newTableInfo = new GridPane();
        newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(100),new ColumnConstraints(150),new ColumnConstraints(200),new ColumnConstraints(100) );
        newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
        newTableInfo.setPrefSize(600, 200);
		newBorderPane.setLeft(newTableInfo);
		GridPane bottomInfo = new GridPane();
		bottomInfo.getColumnConstraints().addAll(new ColumnConstraints(530), new ColumnConstraints(90), new ColumnConstraints(70));
		Button ok = new Button("OK");
		ok.setOnAction(e -> createNewAffector(up));
		bottomInfo.add(ok, 2, 0);
		newBorderPane.setBottom(bottomInfo);
//        Button apply = new Button("Apply");
//        apply.setOnAction(e -> updateOldUnit());
//        bottomInfo.add(apply, 1, 0);
        
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        
        addTextFields(newTableInfo);
        
        //labels stuff
//      int index = 1;
//		newTableInfo.getRowConstraints().add(new RowConstraints(30));
//		String name = "Name";
//		newTableInfo.add(new Text(name), 1, index);
//		TextField myTextField = new TextField();
//		newTableInfo.add(myTextField, 2, index);
//		strTextMap.put(name, myTextField);
//		index++;
//		
//		newTableInfo.getRowConstraints().add(new RowConstraints(30));
//		String damage = "Attack";
//		newTableInfo.add(new Text(damage+":"), 1, index);
//		myTextField = new TextField();
//		newTableInfo.add(myTextField, 2, index);
//		index++;
//		
//        
//		newTableInfo.getRowConstraints().add(new RowConstraints(30));
//		String wweorpawt  = "Projectiles";
//		newTableInfo.add(new Text(wweorpawt), 1, index);
//		ComboBox<String> cbox = new ComboBox<String>();
//		cbox.getItems().addAll("Hello", "its", "me");
//		newTableInfo.add(cbox, 2, index);
//		index++;
//		
//		newTableInfo.getRowConstraints().add(new RowConstraints(30));
//		String affectors = "Affector(s) For Unit";
//		newTableInfo.add(new Text(affectors), 1, index);
//		ComboBox<String> cbox1 = new ComboBox<String>();
//		cbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
//		newTableInfo.add(cbox1, 2, index);
//		index++;
//		//labels stuff
		
		
	    
		this.setContent(myPane);
		
	}
	
	private void createNewAffector(UnitPicker up) {
		// TODO Auto-generated method stub
		String name = strTextMap.get("Name").getText();
		String type = "game_engine.affectors.Basic" + strDropMap.get("Iteration Type?").getValue() + "Affector";
		strTextMap.remove("Name");
		
		List<String> eff = new ArrayList<String>();
		List<List<Double>> funct = new ArrayList<List<Double>>();

		
		for(TextField e: effects){
			eff.add(e.getText());
		}
		
		for(TextField f: functions){
			String[] func = f.getText().split("//s+");
			List<Double> fun = new ArrayList<Double>();
			for(String ff: func){
				try{
					fun.add(Double.parseDouble(ff));
				}
				catch(Exception e){
					System.out.println("womp. not an int.");
				}
			}
			funct.add(fun);
		}
		
		AffectorFactory affect = new AffectorFactory(new FunctionFactory());
		Affector plz = affect.constructAffector(name, type, eff, funct);
		System.out.println(plz.getName());
		
		
		
//		for(String s: strTextMap.keySet()){
//			effects.add(s);
//			List<Integer> func = new ArrayList<Integer>();
//			String[] funcs = strTextMap.get(s).getText().split("//s+");
//			for(String f: funcs){
//				try{
//					func.add(Integer.parseInt(f));
//				}
//				catch(Exception e){
//					System.out.println("womp not an int");
//				}
//			}
//			functions.add(func);	
//		}
		
		
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
		//typeButton.setOnAction(e -> setUnitType(myTextField.getText(), iterationNum, newTableInfo));
		
		//UnitProperties unitProp =myUnitFactory.getUnitLibrary().getPropertyByType(type);
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s = "Name";
		newTableInfo.add(new Text(s), 1, index);
		TextField myTextField = new TextField();
		newTableInfo.add(myTextField, 2, index);
		strTextMap.put(s, myTextField);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s1 = "Iteration Type?";
		newTableInfo.add(new Text(s1), 1, index);
		ComboBox<String> dropit = new ComboBox<String>();
		newTableInfo.add(dropit, 2, index);
		dropit.getItems().addAll("Increment", "Decrement", "Set");
		strDropMap.put(s1, dropit);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s2 = "Effects";
		newTableInfo.add(new Text(s2), 1, index);
		TextField txtfld = new TextField();
		newTableInfo.add(txtfld, 2, index);
		effects.add(txtfld);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s3 = "Function";
		newTableInfo.add(new Text(s3), 1, index);
		TextField txtfld2 = new TextField();
		newTableInfo.add(txtfld2, 2, index);
		functions.add(txtfld2);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		Button buuton = new Button("New Thing");	//change
		buuton.setOnAction(e -> newThing(buuton, newTableInfo, txtfld, txtfld2));		//change
		newTableInfo.add(buuton, 2, index);
		index++;
	
		//possibly just make index global MAKE INDEX GLOBAL
		
		
	}

	private void newThing(Button buuton, GridPane newTableInfo, TextField txtfld3, TextField txtfld4) {
		if(!txtfld3.getText().equals("") && !txtfld4.getText().equals("")){
		newTableInfo.getChildren().remove(buuton);
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		
		String s2 = "Effects";
		newTableInfo.add(new Text(s2), 1, index);
		TextField txtfld = new TextField();
		newTableInfo.add(txtfld, 2, index);
		effects.add(txtfld);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String s3 = "Function";
		newTableInfo.add(new Text(s3), 1, index);
		TextField txtfld2 = new TextField();
		newTableInfo.add(txtfld2, 2, index);
		functions.add(txtfld2);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		newTableInfo.add(buuton, 2, index);
		
		index++;
		}
		
	}

}
