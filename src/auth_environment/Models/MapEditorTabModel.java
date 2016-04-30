package auth_environment.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IMapEditorTabModel;
import auth_environment.Models.Interfaces.IMapPane;
import auth_environment.delegatesAndFactories.GridMapPane;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
/*
 * This class now serves as a controller between MapEditorTab and the backend data instead of a backend model.
 * @Author: Alexander Tseng
 */
public class MapEditorTabModel implements IMapEditorTabModel{

	private IAuthEnvironment myAuthData;  
	private Map<Position, Unit> myPositionMap = new HashMap<Position, Unit>();

	public MapEditorTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;
	}
	
	public void refresh(IAuthEnvironment auth) {
		this.myAuthData = auth; 
	}
	
	
	public void addTerrain(double xPos, double yPos, Unit element){
		Unit newUnit = element.copyUnit();
		newUnit.getProperties().setPosition(xPos, yPos);
		myAuthData.getPlacedUnits().add(newUnit);
	}
	
	public void deleteTerrain(double xPos, double yPos){
		myPositionMap.remove(new Position(xPos, yPos));
	}
	
	public void deleteTerrain(Unit element){
		myAuthData.getPlacedUnits().remove(element);
		myPositionMap.remove(element.getProperties().getPosition());
	}
	
	public List<Unit> getTerrains(){
		return myAuthData.getUnitFactory().getUnitLibrary().getUnits();
	}
	
	public void convert(IMapPane mapPane){
		mapPane.getRoot().getChildren().clear();
		myAuthData.getPlacedUnits().forEach( e-> {
			UnitView tempUnitView = new UnitView(e.copyUnit(), e.toString() + ".png");
			tempUnitView.addContextMenu(mapPane, tempUnitView);
			mapPane.addToPane(tempUnitView);
			Unit tempUnit = e.copyUnit();
			tempUnit.getProperties().setPosition(tempUnitView.getX(), tempUnitView.getY());
			Collections.replaceAll(myAuthData.getPlacedUnits(), e, tempUnit);
		});
	}
	
	public void clear(){
		myAuthData.getPlacedUnits().clear();
	}

	@Override
	public void updateTerrainList(List<Unit> update) {
		// TODO Auto-generated method stub
		
	}
	

//	public List<Unit> getAllTerrains(){
//		allTerrains.addAll(myPositionMap.values());
//		System.out.println(allTerrains);
//		return allTerrains;
//	}

}
