package auth_environment.Models;

import auth_environment.Models.Interfaces.IGlobalGameTabModel;
import game_data.AuthSerializer;
import game_engine.IEngineWorkspace;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

// TODO: check that loading GameData is reflected at AuthViewModel level (one level above this Model) 

public class GlobalGameTabModel implements IGlobalGameTabModel {
	
	private IEngineWorkspace myEngineWorkspace; 
	
	// TODO: are type arguments necessary? 
	private AuthSerializer<IEngineWorkspace> writer = new AuthSerializer<IEngineWorkspace>();

	
	public GlobalGameTabModel(IEngineWorkspace engineWorkspace) {
		this.myEngineWorkspace = engineWorkspace; 
	}

	@Override
	public void saveToFile() {
		writer.saveElement(this.myEngineWorkspace); 
	}

	@Override
	public void loadFromFile() {
		// TODO: add error checking
		this.myEngineWorkspace = (IEngineWorkspace) writer.loadElement();
	}
	
	
}
