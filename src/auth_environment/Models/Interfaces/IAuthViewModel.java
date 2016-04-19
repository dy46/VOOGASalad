package auth_environment.Models.Interfaces;

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

// TODO: add to API changes document

public interface IAuthViewModel {
	
	// Pass the IEngineWorkspace instance to GlobalGameTab to be saved
	public IEngineWorkspace getEngineWorkspace(); 
	
	// Receive an IEngineWorkspace instance from GlobalGameTab after loading
	public void setEngineWorkspace(IEngineWorkspace engineWorkspace); 
	
}
