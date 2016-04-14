package auth_environment.view.Menus;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.view.ElementPicker;
import auth_environment.view.TowerView;
import game_engine.properties.Bounds;
import game_engine.properties.Damage;
import game_engine.properties.Health;
import game_engine.properties.Position;
import game_engine.properties.Price;
import game_engine.properties.Team;
import game_engine.properties.Velocity;
import java.util.*;
import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.EnemyBuildingBlock;
import auth_environment.buildingBlocks.TerrainBuildingBlock;
import auth_environment.buildingBlocks.TowerBuildingBlock;
import game_engine.factories.EnemyFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.libraries.AffectorLibrary;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Created by BrianLin on 4/1/16.
 */
public class TowerMenu extends SuperMenu {

	public TowerMenu(PickerMenu myPicker) {
		super(myPicker);
		// TODO Auto-generated constructor stub
	}
	private static final String ELEMENT_LABELS_PACKAGE = "auth_environment/properties/labels";
	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
	
	public void createNewElement(){
		ResourceBundle myElementLabelsBundle = ResourceBundle.getBundle(ELEMENT_LABELS_PACKAGE);
		createElement(myElementLabelsBundle, new TowerBuildingBlock(), intTextMap, strTextMap);
		
	}
	

    
    public void makeElement(Tooltip t, BuildingBlock block){
    	Class<?> c = block.getClass();
    	for(String str: strTextMap.keySet()){
    		block.setMyName(strTextMap.get(str).getText());
    	}
    	for(String str: intTextMap.keySet()){
    		Method[] allMethods = c.getMethods();
    		
    		for(Method m: allMethods){;
//    			String[] mString = m.getName().split(".");
//    			System.out.println(m.getName());
//    			System.out.println("setMy" + str);
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
