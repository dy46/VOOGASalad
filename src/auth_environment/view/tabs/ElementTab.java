package auth_environment.view.tabs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.IAuthInterface;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ElementTab extends Tab{
	 
	private IAuthInterface myInterface;
	Map<String, TextField> strTextMap = new HashMap<String, TextField>();
	
	public ElementTab(String name, IAuthInterface myInterface){
		super(name);
		this.myInterface = myInterface;
		init();
	}
	
	private void init(){
		BorderPane myPane = new BorderPane();
		TitledPane newPane = new TitledPane();
		TitledPane editPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		ScrollPane editScrollPane = new ScrollPane();
		BorderPane newBorderPane = new BorderPane();
		GridPane newAnimationInfo = new GridPane();					//*****	
		FlowPane editInfo = new FlowPane();							//*****	
		myPane.setLeft(newPane);
		myPane.setRight(editPane);
		
		newPane.setText("New");
		newPane.setContent(newScrollPane);
		newPane.setPrefSize(700.0, 800.0);
		newPane.setCollapsible(false);
		newScrollPane.setContent(newBorderPane);
		newBorderPane.setTop(newAnimationInfo);
		newScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		Button animationButton = new Button("ANIMATION");
		animationButton.setOnAction( e -> System.out.println("ANIMATION"));
		animationButton.setPrefSize(400.0,70.0);

		newAnimationInfo.getColumnConstraints().addAll(new ColumnConstraints(250), new ColumnConstraints(200), new ColumnConstraints(200));
		newAnimationInfo.getRowConstraints().addAll(new RowConstraints(70));
		newAnimationInfo.add(animationButton, 1, 0); //col, row
		
        GridPane newTableInfo = new GridPane();
        newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(175),new ColumnConstraints(150),new ColumnConstraints(200),new ColumnConstraints(100) );
        newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
        newTableInfo.setPrefSize(600, 200);
		newBorderPane.setLeft(newTableInfo);
		GridPane bottomInfo = new GridPane();
		bottomInfo.getColumnConstraints().addAll(new ColumnConstraints(600), new ColumnConstraints(70));
		Button ok = new Button("OK");
		ok.setOnAction(e -> createNewUnit());
		bottomInfo.add(ok, 1, 0);
		newBorderPane.setBottom(bottomInfo);
        
        
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        
        //labels stuff
        int index = 1;
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String name = "Name";
		newTableInfo.add(new Text(name), 1, index);
		TextField myTextField = new TextField();
		newTableInfo.add(myTextField, 2, index);
		strTextMap.put(name, myTextField);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String damage = "Attack";
		newTableInfo.add(new Text(damage+":"), 1, index);
		myTextField = new TextField();
		newTableInfo.add(myTextField, 2, index);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String affectors = "Affector(s) ";
		newTableInfo.add(new Text(affectors), 1, index);
		ComboBox<String> cbox = new ComboBox<String>();
		cbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
		newTableInfo.add(cbox, 2, index);
		index++;
		//labels stuff
		
		Button newAffectorButton = new Button("+ Add New Affector");
		int num = index;
		newAffectorButton.setOnAction(e-> addNewAffectorSpace(num, newTableInfo, newAffectorButton, cbox));
		newTableInfo.add(newAffectorButton, 2, index);
		
		editPane.setPrefSize(200.0, 800.0);
		editScrollPane.setContent(editInfo);
		editScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		editScrollPane.setFitToWidth(true);
		
		editPane.setText("Edit");
		editPane.setContent(editScrollPane);
		editPane.setCollapsible(false);
		List<Unit> myList = myInterface.getTowers();
		for(Unit unit: myList){
			editInfo.getChildren().addAll(new ImageView(new Image(unit.toString())));
		}
		this.setContent(myPane);
	}

	private void createNewUnit() {
		UnitFactory unitFactory = new UnitFactory();
    	Class<?> c = unitFactory.getClass();
    	for(String str: strTextMap.keySet()){
    		Method[] allMethods = c.getMethods();
    		
    		for(Method m: allMethods){;
//    			String[] mString = m.getName().split(".");
//    			System.out.println(m.getName());
//    			System.out.println("setMy" + str);
    			if(m.getName().startsWith("setMy" + str)){
					try {
						m.invoke(unitFactory,strTextMap.get(str).getText());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

    			}
    		}	
    	}
	}

	private void addNewAffectorSpace(int index, GridPane newTableInfo, Button AffectorButton, ComboBox cbox) {
		if(cbox.getValue() != null){
			newTableInfo.getChildren().remove(AffectorButton);
			ComboBox<String> newcbox = new ComboBox<String>();
			newcbox.getItems().addAll("ConstantHealthDamage", "ExpIncrHealthDamage", "HealthDamage", "HomingMove", "PathFollowPositionMove", "RandomPoisonHealthDamage", "StateChange");
			newTableInfo.add(newcbox, 2, index);
			index++;
			Button newAffectorButton = new Button("+ Add New Affector");
			int num = index;
			newAffectorButton.setOnAction(e-> addNewAffectorSpace(num, newTableInfo, newAffectorButton, newcbox));
			newTableInfo.add(newAffectorButton, 2, index);	
		}
	}
	

	
}
