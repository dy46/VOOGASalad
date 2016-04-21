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
//import auth_environment.buildingBlocks.TerrainBuildingBlock;
//import auth_environment.buildingBlocks.TowerBuildingBlock;
//import auth_environment.view.ElementPicker;
//import game_engine.factories.TerrainFactory;
//import game_engine.factories.TowerFactory;
//import game_engine.game_elements.Terrain;
//import game_engine.game_elements.Tower;
//import game_engine.libraries.AffectorLibrary;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.ImageView;
//
//public class TerrainMenu extends SuperMenu{
//	public TerrainMenu(ElementPicker myPicker, FactoryController factoryController) {
//		super(myPicker, factoryController);
//		// TODO Auto-generated constructor stub
//	}
//
//	private static final String TERRAIN_LABELS_PACKAGE = "auth_environment/properties/terrain_labels";
//	private static final String TERRAIN_STRING_LABELS_PACKAGE = "auth_environment/properties/terrain_string_labels";
//	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
//	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
//	
//	@Override
//	public void createNewElement() { //can refactor this
//		ResourceBundle myTerrainLabelsBundle = ResourceBundle.getBundle(TERRAIN_LABELS_PACKAGE);
//		ResourceBundle myStringLabelsBundle = ResourceBundle.getBundle(TERRAIN_STRING_LABELS_PACKAGE);
//		createElement(new TerrainBuildingBlock(), intTextMap, strTextMap,myTerrainLabelsBundle, myStringLabelsBundle);
//	}
//
//	@Override
//	public void makeElement(Tooltip t, BuildingBlock block) {
//	    	Class<?> c = block.getClass();
//	    	for(String str: strTextMap.keySet()){
//	    		Method[] allMethods = c.getMethods();
//	    		
//	    		for(Method m: allMethods){;
////	    			String[] mString = m.getName().split(".");
////	    			System.out.println(m.getName());
////	    			System.out.println("setMy" + str);
//	    			if(m.getName().startsWith("setMy" + str)){
//	    				try {
//							m.invoke(block,strTextMap.get(str).getText());
//							break;
//
//						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//							// TODO Auto-generated catch block
//							//e.printStackTrace();
//							System.out.println("ERROR");
//						}
//	    			}
//	    		}	
//	    	}
//	    	for(String str: intTextMap.keySet()){
//	    		Method[] allMethods = c.getMethods();
//	    		
//	    		for(Method m: allMethods){;
////	    			String[] mString = m.getName().split(".");
////	    			System.out.println(m.getName());
////	    			System.out.println("setMy" + str);
//	    			if(m.getName().startsWith("setMy" + str)){
//	    				try {
//							m.invoke(block,Double.parseDouble(intTextMap.get(str).getText()));
//							break;
//
//						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//							// TODO Auto-generated catch block
//							//e.printStackTrace();
//							System.out.println("ERROR");
//						}
//	    			}
//	    		}	
//	    	}
//	    	
//	    	block.setMyImage((ImageView)t.getGraphic());
//	    	
//	    	TerrainFactory terrainFac = super.getFactoryController().getTerrainFactory();
//	    	Terrain terrain = terrainFac.defineTerrainModel(block);
//	    	
//	    	getPicker().updateTerrain(terrain);
//		
//	}
//
//}
