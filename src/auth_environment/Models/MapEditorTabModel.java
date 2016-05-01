package auth_environment.Models;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IMapEditorTabModel;
import game_engine.game_elements.Unit;


/*
 * This class serves as a controller between MapEditorTab and the backend data.
 * @Author: Alexander Tseng
 */
public class MapEditorTabModel implements IMapEditorTabModel{

	private IAuthEnvironment myAuthData; 

	public MapEditorTabModel(IAuthEnvironment auth) {
		myAuthData = auth;
	}
	
	public void refresh(IAuthEnvironment auth) {
		myAuthData = auth; 
	}
	
	public void addTerrain(double xPos, double yPos, Unit element){
		element.getProperties().setPosition(xPos, yPos);
		myAuthData.getPlacedUnits().add(element);
	}
	
	public void deleteTerrain(Unit element){
		System.out.println(myAuthData.getPlacedUnits().remove(element));
		
		myAuthData.getPlacedUnits().stream().forEach( e-> System.out.println("Attempt to print:" + e.toString()));
	}
	
	public List<Unit> getPlacedUnits(){
		return myAuthData.getPlacedUnits();
	}
	
	public List<Unit> getTerrains(){
		return myAuthData.getUnitFactory().getUnitLibrary().getUnits();
	}
	
	public void clear(){
		myAuthData.getPlacedUnits().clear();
	}


}
