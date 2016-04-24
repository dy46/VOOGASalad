package auth_environment.Models.Interfaces;

import java.util.List;

public interface IWaveOverviewTabModel {
	/*
	 * Adds a unit associated with a specific name. If the unit does not exist in the library,
	 * nothing is added to the list of units and an error is thrown.
	 * 
	 * @param	name represents the name of the unit to be added
	 */
	void addSpawningUnit(String name, int time);
	
	/*
	 * adds a list of units into the wave, along with their corresponding times.
	 * 
	 * @param	names refers to the list of names for the units
	 * @param	times refers to the corresponding time that each unit will spawn.
	 */
	void addSpwaningUnits(List<String> names, List<Integer> times);
	/*
	 * Returns a list of information on the enemies that are currently added to the wave
	 * 
	 * @return	List<String> contains strings related to the information of each unit in the wave.
	 */
	List<String> getEnemyInfo();
}
