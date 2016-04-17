//package auth_environment.view.Menus;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//import auth_environment.backend.FactoryController;
//import auth_environment.buildingBlocks.BuildingBlock;
//import auth_environment.buildingBlocks.EnemyBuildingBlock;
//import auth_environment.view.ElementPicker;
//import game_engine.factories.EnemyFactory;
//import game_engine.game_elements.Enemy;
//import game_engine.libraries.AffectorLibrary;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.ImageView;
//
//public class EnemyMenu extends SuperMenu{
//	
//	public EnemyMenu(ElementPicker myPicker, FactoryController factoryController) {
//		super(myPicker, factoryController);
//		// TODO Auto-generated constructor stub
//	}
//	private static final String ENEMY_LABELS_PACKAGE = "auth_environment/properties/enemy_labels";
//	private static final String STRING_LABELS_PACKAGE = "auth_environment/properties/enemy_string_labels";
//	
//	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
//	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
//	@Override
//	public void createNewElement() {
//		// TODO Auto-generated method stub
//		ResourceBundle myEnemiesBundle = ResourceBundle.getBundle(ENEMY_LABELS_PACKAGE);
//		ResourceBundle myStringBundle = ResourceBundle.getBundle(STRING_LABELS_PACKAGE);
//		createElement(new EnemyBuildingBlock(), intTextMap, strTextMap, myEnemiesBundle, myStringBundle);
//		
//	}
////	@Override
////	public void makeElement(Tooltip t, BuildingBlock block) {
////		// TODO Auto-generated method stub
////	    	Class<?> c = block.getClass();
////	    	for(String str: strTextMap.keySet()){
////	    		Method[] allMethods = c.getMethods();
////	    		
////	    		for(Method m: allMethods){;
//////	    			String[] mString = m.getName().split(".");
//////	    			System.out.println(m.getName());
//////	    			System.out.println("setMy" + str);
////	    			if(m.getName().startsWith("setMy" + str)){
////	    				try {
////							m.invoke(block,strTextMap.get(str).getText());
////							break;
////
////						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
////							// TODO Auto-generated catch block
////							//e.printStackTrace();
////							System.out.println("what");
////							System.out.println("ERROR");
////						}
////	    			}
////	    		}	
////	    	}
////	    	for(String str: intTextMap.keySet()){
////	    		Method[] allMethods = c.getMethods();
////	    		
////	    		for(Method m: allMethods){;
//////	    			String[] mString = m.getName().split(".");
//////	    			System.out.println(m.getName());
//////	    			System.out.println("setMy" + str);
////	    			if(m.getName().startsWith("setMy" + str)){
////	    				try {
////							m.invoke(block,Double.parseDouble(intTextMap.get(str).getText()));
////							break;
////
////						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
////							// TODO Auto-generated catch block
////							//e.printStackTrace();
////							System.out.println(str + intTextMap.get(str).getText());
////							System.out.println("ERROR");
////						}
////	    			}
////	    		}	
////	    	}
////	    	
////	    	block.setMyImage((ImageView)t.getGraphic());
////	    	
////	    	EnemyFactory enemyFac = super.getFactoryController().getEnemyFactory();
////	    	Enemy enemy = enemyFac.defineEnemyModel(block);
////	    	
////	    	getPicker().updateEnemy(enemy);
////	    	
////	    }
////		
//	}
//
