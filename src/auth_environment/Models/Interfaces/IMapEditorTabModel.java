package auth_environment.Models.Interfaces;

public interface IMapEditorTabModel {
	// Saves IAuthViewModel's instance of IEngineWorkspace
	public void saveToFile(); 
	
	// Loads GameData from file and setsGIAuthViewModel's instance of IEngineWorkspace 
	public void loadFromFile(); 

}
