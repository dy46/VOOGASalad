package game_engine.factories;

import java.util.Arrays;
import java.util.List;

import exceptions.WompException;
import game_engine.libraries.PropertiesLibrary;
import game_engine.properties.UnitProperties;

public class PropertiesFactory {
	
	private PropertiesLibrary myPropertiesLibrary;

	public PropertiesFactory(){
		myPropertiesLibrary = new PropertiesLibrary();
	}
	
	public void createPropertiesByType(String type, UnitProperties properties){
		this.myPropertiesLibrary.addPropertiesByType(type, properties);
	}
	
	public PropertiesLibrary getPropertiesLibrary(){
		return myPropertiesLibrary;
	}
	
	public void changePropertiesByType(String type, UnitProperties newProperties) throws WompException{
		this.myPropertiesLibrary.changePropertiesType(type, newProperties);
	}
	
	public List<String> getFields(){
		return Arrays.asList("Health", "Team", "Initial Speed", "Initial Direction", "Price", "State");
	}

}