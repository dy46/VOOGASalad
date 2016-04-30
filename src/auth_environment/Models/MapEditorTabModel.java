package auth_environment.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IMapEditorTabModel;
import auth_environment.Models.Interfaces.IMapPane;
import auth_environment.delegatesAndFactories.GridMapPane;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class MapEditorTabModel implements IMapEditorTabModel{

	private IAuthEnvironment myAuthData;  
	private Map<Position, Unit> myPositionMap = new HashMap<Position, Unit>();
	private Map<Unit, Position> myUnitMap = new HashMap<Unit, Position>();
	private List<Unit> myTerrains;
	private List<Unit> allTerrains;

	public MapEditorTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;
//		this.myTerrains = new ArrayList<Unit>(); 
//		this.allTerrains = new ArrayList<Unit>();
	}

//	public List<Unit> getSampleUnits() {
//	       GameEngineInterface gameInterface = new TestingEngineWorkspace();
//	       gameInterface.setUpEngine(null);
//	       Unit unit = new Unit("Tower", 2);
//	       unit.setProperties(new UnitProperties());
//	       return Arrays.asList(unit);
//	   }
	
	public void refresh(IAuthEnvironment auth) {
		this.myAuthData = auth; 
		this.myTerrains = auth.getUnitFactory().getUnitLibrary().getUnits();
	}
	
	
//	public void changeMode(IMapPane inputMapPane){
//		myAuthData.getPlacedUnits().clear();
//	}
	
	public void addTerrain(double xPos, double yPos, Unit element){
		Unit newUnit = element.copyUnit();
		newUnit.getProperties().setPosition(xPos, yPos);
//		System.out.println("XPOS" + xPos);
//		System.out.println("YPOS" + yPos);
		myAuthData.getPlacedUnits().add(newUnit);
//		element.getProperties().getPosition().setX(xPos);
//		element.getProperties().getPosition().setY(yPos);
//		myPositionMap.put(new Position(xPos, yPos), element);
//		System.out.println("MapEditorModel X:" + element.getProperties().getPosition().getX());
//		myAuthData.getPlacedUnits().add(element.copyUnit()); 
	}
	
	public void deleteTerrain(double xPos, double yPos){
		myPositionMap.remove(new Position(xPos, yPos));
	}
	
	public void deleteTerrain(Unit element){
		myAuthData.getPlacedUnits().remove(element);
		myPositionMap.remove(element.getProperties().getPosition());
	}
	
	public void updateTerrainList(List<Unit> update){
		this.myTerrains = update;
	}
	
	public List<Unit> getTerrains(){
		return myAuthData.getUnitFactory().getUnitLibrary().getUnits();
	}
	
	public void convert(IMapPane mapPane){
		myTerrains = myAuthData.getPlacedUnits();
		myTerrains.stream().forEach( e-> {
			mapPane.getRoot().getChildren().clear();
			UnitView tempUnitView = new UnitView(e.copyUnit(), e.toString() + ".png");
			mapPane.addToPane(tempUnitView);
		});
		
	}
	

//	public List<Unit> getAllTerrains(){
//		allTerrains.addAll(myPositionMap.values());
//		System.out.println(allTerrains);
//		return allTerrains;
//	}

}
