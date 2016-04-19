package auth_environment.Models;

import game_data.AuthSerializer;
import game_engine.EngineWorkspace;
import game_engine.IEngineWorkspace;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This class holds the highest level of Auth Environment backend data. Most important is a single instance
 * of IEngineWorkspace.java (all of our data). 
 * 
 * There will also be some helper methods- such as saving or loading the data. 
 */

public class AuthViewModel implements IAuthViewModel {
	
	private IEngineWorkspace myEngineWorkspace; 
	
	public AuthViewModel() {
		// Start with empty EngineWorkspace. Can load one from file however. 
		myEngineWorkspace = new EngineWorkspace(); 
	}

	@Override
	public void saveToFile() {
		AuthSerializer writer = new AuthSerializer(); 
		writer.saveElement(this.myEngineWorkspace);
	}
	
}
