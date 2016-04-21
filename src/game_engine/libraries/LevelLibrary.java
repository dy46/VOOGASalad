package game_engine.libraries;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Level;

public class LevelLibrary {

	private List<Level> myLevels;
	
	public LevelLibrary(){
		this.myLevels = new ArrayList<>();
	}
	
	public List<Level> getLibraries(){
		return myLevels;
	}
	
	public Level getLevelByName(String name){
		for(Level l : myLevels){
			if(l.getName().equals(name)){
				return l;
			}
		}
		return null;
	}
	
}