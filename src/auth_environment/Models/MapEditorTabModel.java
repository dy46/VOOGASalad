package auth_environment.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IMapEditorTabModel;
import game_data.AuthSerializer;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class MapEditorTabModel implements IMapEditorTabModel{

	private IAuthEnvironment myAuthData;  
	
	// TODO: are type arguments necessary? 
	private AuthSerializer writer = new AuthSerializer();
	
	Map<Position, Unit> myMap = new HashMap<Position, Unit>();
	List<Unit> myTerrains;
	public MapEditorTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;
		myTerrains = auth.getTerrains();
	}

	
	public void addTerrain(double xPos, double yPos, Unit element){
		element.getProperties().getPosition().setX(xPos);
		element.getProperties().getPosition().setY(yPos);
		myMap.put(new Position(xPos, yPos), element);
	}
	
	public void deleteTerrain(double xPos, double yPos){
		myMap.remove(new Position(xPos, yPos));
	}
	
	public void deleteTerrain(Unit element){
		myMap.remove(element.getProperties().getPosition());
	}
	
	public void updateTerrainList(List<Unit> update){
		this.myTerrains = update;
	}
	
	public List<Unit> getTerrains(){
		return myTerrains;
	}

}
