package auth_environment.view.Menus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.backend.FactoryController;
import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.EnemyBuildingBlock;
import auth_environment.view.ElementPicker;
import game_engine.factories.EnemyFactory;
import game_engine.game_elements.Enemy;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class EnemyMenu extends SuperMenu{
	
	public EnemyMenu(ElementPicker myPicker) {
		super(myPicker);
	}
	public EnemyMenu(ElementPicker myPicker, FactoryController factoryController) {
		super(myPicker, factoryController);
		// TODO Auto-generated constructor stub
	}
	private static final String ENEMY_LABELS_PACKAGE = "auth_environment/properties/enemy_labels";
	private static final String STRING_LABELS_PACKAGE = "auth_environment/properties/enemy_string_labels";
	private static final String DROPDOWN_LABELS_PACKAGE = "auth_environment/properties/dropdown_labels";
	
	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
	private Map<String, List<ComboBox<String>>> strDropMap = new HashMap<String, List<ComboBox<String>>>();
	
	@Override
	public void createNewElement() {
		ResourceBundle myEnemiesBundle = ResourceBundle.getBundle(ENEMY_LABELS_PACKAGE);
		ResourceBundle myStringBundle = ResourceBundle.getBundle(STRING_LABELS_PACKAGE);
		ResourceBundle myDropBundle = ResourceBundle.getBundle(DROPDOWN_LABELS_PACKAGE);
		createElement(new EnemyBuildingBlock(), intTextMap, strTextMap, strDropMap, myEnemiesBundle, myStringBundle, myDropBundle);
	}
	
	@Override
	public void makeElement(Tooltip t, BuildingBlock block) {
		// TODO Auto-generated method stub
			for(String s: strDropMap.keySet()){
				for(ComboBox<String> b: strDropMap.get(s)){
					System.out.println(b.getValue());
				}
			}
//	    	Class<?> c = block.getClass();
//	    	for(String str: strTextMap.keySet()){
//	    		Method[] allMethods = c.getMethods();
//	    		
//	    		for(Method m: allMethods){;
//	    			if(m.getName().startsWith("setMy" + str)){
//	    				try {
//							m.invoke(block,strTextMap.get(str).getText());
//							break;
//
//						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//							System.out.println("ERROR");
//						}
//	    			}
//	    		}	
//	    	}
//	    	for(String str: intTextMap.keySet()){
//	    		Method[] allMethods = c.getMethods();
//	    		
//	    		for(Method m: allMethods){;
//	    			if(m.getName().startsWith("setMy" + str)){
//	    				try {
//							m.invoke(block,Double.parseDouble(intTextMap.get(str).getText()));
//							break;
//
//						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//							System.out.println("ERROR");
//						}
//	    			}
//	    		}	
//	    	}
//	    	
//	    	block.setMyImage((ImageView)t.getGraphic());
//	    	
//	    	EnemyFactory enemyFac = super.getEnemyFactory();
//	    	Enemy enemy = enemyFac.defineEnemyModel(block);
//	    	
//	    	getPicker().updateEnemy(enemy);
//	    	

	    }
		
	}

