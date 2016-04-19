package auth_environment.Models;

import auth_environment.Models.Interfaces.IAuthViewModel;
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
	// Only called by Brian's GlobalGameTab
	public IEngineWorkspace getEngineWorkspace() {
		return this.myEngineWorkspace;
	}

	@Override
	// Only called by Brian's GlobalGameTab
	public void setEngineWorkspace(IEngineWorkspace engineWorkspace) {
		this.myEngineWorkspace = engineWorkspace; 
	}
	
}
