package auth_environment.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IMapEditorTabModel;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class MapEditorTabModel implements IMapEditorTabModel{

	private IAuthEnvironment myAuthData;  
	private Map<Position, Unit> myMap = new HashMap<Position, Unit>();
	private List<Unit> myTerrains;
	
	public MapEditorTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;
		myTerrains = auth.getTerrains();
	}

//	public List<Unit> getSampleUnits() {
//	       GameEngineInterface gameInterface = new TestingEngineWorkspace();
//	       gameInterface.setUpEngine(null);
//	       Unit unit = new Unit("Tower", 2);
//	       unit.setProperties(new UnitProperties());
//	       return Arrays.asList(unit);
//	   }
	
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
