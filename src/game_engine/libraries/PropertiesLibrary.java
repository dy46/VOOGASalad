package game_engine.libraries;

import java.util.HashMap;

import exceptions.CorrectableException;
import exceptions.WompException;
import exceptions.AskChangeException;
import game_engine.properties.UnitProperties;

public class PropertiesLibrary {

	private HashMap<String, UnitProperties> myProperties;

	public PropertiesLibrary(){
		myProperties = new HashMap<>();
	}

	public HashMap<String, UnitProperties> getProperties(){
		return myProperties;
	}

	public void addPropertiesByType(String type, UnitProperties properties){
		if(myProperties.keySet().contains(type)){
			if(new AskChangeException().getResult()){
				this.myProperties.put(type, properties);
			}
		}
		else{
			myProperties.put(type, properties);
		}
	}

	public void changePropertiesType(String type, UnitProperties newProperties) throws WompException{
		if(!myProperties.keySet().contains(type)){
			throw new WompException("UnitProperties type does not exist");
		}
		this.myProperties.put(type, newProperties);
	}

}