package auth_environment.view.Menus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.TerrainBuildingBlock;
import auth_environment.buildingBlocks.TowerBuildingBlock;
import auth_environment.view.ElementPicker;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Tower;
import game_engine.libraries.AffectorLibrary;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class TerrainMenu extends SuperMenu{
	public TerrainMenu(ElementPicker myPicker) {
		super(myPicker);
		// TODO Auto-generated constructor stub
	}

	private static final String TERRAIN_LABELS_PACKAGE = "auth_environment/properties/terrain_labels";
	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
	
	@Override
	public void createNewElement() {
		ResourceBundle myTerrainLabelsBundle = ResourceBundle.getBundle(TERRAIN_LABELS_PACKAGE);
		createElement(myTerrainLabelsBundle, new TerrainBuildingBlock(), intTextMap, strTextMap);
	}

	@Override
	public void makeElement(Tooltip t, BuildingBlock block) {
	    	Class<?> c = block.getClass();
	    	for(String str: strTextMap.keySet()){
	    		block.setMyName(strTextMap.get(str).getText());
	    	}
	    	for(String str: intTextMap.keySet()){
	    		Method[] allMethods = c.getMethods();
	    		
	    		for(Method m: allMethods){;
//	    			String[] mString = m.getName().split(".");
//	    			System.out.println(m.getName());
//	    			System.out.println("setMy" + str);
	    			if(m.getName().startsWith("setMy" + str)){
	    				try {
							m.invoke(block,Double.parseDouble(intTextMap.get(str).getText()));
							break;

						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println("ERROR");
						}
	    			}
	    		}	
	    	}
	    	
	    	block.setMyImage((ImageView)t.getGraphic());
	    	
	    	TowerFactory towerFac = new TowerFactory(new AffectorLibrary());
	    	Tower tower = towerFac.defineTowerModel(block);
	    	
	    	getPicker().updateTower(tower);
		
	}

}
