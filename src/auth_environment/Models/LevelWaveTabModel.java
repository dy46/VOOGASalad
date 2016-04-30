package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;

import auth_environment.Models.Interfaces.ILevelWaveTabModel;
import game_engine.game_elements.Level;
import game_engine.game_elements.Wave;

public class LevelWaveTabModel implements ILevelWaveTabModel{
	private List<Level> myLevels;
	private List<Wave> myWaves;
	private int editIndex;
	
	public LevelWaveTabModel(){
		myLevels = new ArrayList<Level>();
		myWaves = new ArrayList<Wave>();
		editIndex = -1;
	}

	@Override
	public List<String> getLevelNames() {
		List<String> names = new ArrayList<String>();
		myLevels.stream().forEach(l -> names.add(l.getName()));
		return names;
	}

	@Override
	public void addNewLevel(String name, int numLives) {
		Level l = new Level(name, numLives);
		myLevels.add(l);
	}

	@Override
	public void changeEditedLevel(int edit) {
		this.editIndex = edit;
	}

	@Override
	public List<String> getWaveNames() {
		if(checkBounds()){
			List<String> names = new ArrayList<String>();
			this.myLevels.get(this.editIndex).getWaves().stream().forEach(w -> names.add(w.getName()));
			return names;
		}
		else{
			throw new IndexOutOfBoundsException("Level edit number is out of bounds");
		}
	}

	private boolean checkBounds(){
		return (this.editIndex >= 0) && (this.editIndex < this.myLevels.size());
	}
}
