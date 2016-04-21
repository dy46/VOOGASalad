package auth_environment.Models;

import java.util.HashMap;
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
	
	
	public MapEditorTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;  
	}

	@Override
	public void saveToFile() {
		writer.saveElement(this.myAuthData); 
	}

	@Override
	public void loadFromFile() {
		// TODO: add error checking
		this.myAuthData = (IAuthEnvironment) writer.loadElement();
	}
	
	public void addUnit(double xPos, double yPos, Unit element){
		myMap.put(new Position(xPos, yPos), element);
	}
	
	

}
