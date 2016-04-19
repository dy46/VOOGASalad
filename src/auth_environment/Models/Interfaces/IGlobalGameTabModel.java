package auth_environment.Models.Interfaces;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

public interface IGlobalGameTabModel {
	
	// Saves IAuthViewModel's instance of IEngineWorkspace
	public void saveToFile(); 
	
	// Loads GameData from file and sets IAuthViewModel's instance of IEngineWorkspace 
	public void loadFromFile(); 
	
}
