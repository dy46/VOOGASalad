package auth_environment.Models.Interfaces;

import java.util.List;

import game_engine.game_elements.Level;

public interface ILevelWaveTabModel {
	
	/*
	 * Gets the list of level names in order to use in the combo box for 
	 * viewing which levels have been created.
	 * 
	 * @return	List<Level> is the list of level objects currently
	 */
	public List<String> getLevelNames();
	
	/*
	 * Gets the list of names for the waves in the currently edited level
	 * 
	 * @return	List<String> is the list of names for the level that is currently being edited
	 * 			if the level has no waves, the list is empty.
	 */
	public List<String> getWaveNames();
	
	/*
	 * Adds a new level with the specified name and number of lives for that specific level
	 * 
	 * @param	name is the name of the level that is to be stored in the model
	 * @param	numLives represents the number of lives for the specified level
	 */
	public void addNewLevel(String name, int numLives);
	
	/*
	 * Changes the level that is being currently edited on the backend. 
	 * 
	 * @param	edit is the index of the level that is to be edited
	 */
	public void changeEditedLevel(int edit);
	
	/*
	 * saves the list of current levels out to the game data
	 */
	public void export();
}
