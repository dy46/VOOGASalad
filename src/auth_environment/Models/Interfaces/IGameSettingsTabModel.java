package auth_environment.Models.Interfaces;

import java.util.List;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

public interface IGameSettingsTabModel {
	
	public void setGameName(String name); 
	
	public String getGameName(); 
	
	public void setSplashFile(String name);
	
	public String getSplashFile(); 
	
	public void saveToFile(); 
	
	public void loadFromFile(); 
	
	public void chooseItem(String selectedItem, String key);
	
	public List<String> getSelectedNames(String key);
	
}
