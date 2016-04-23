package game_engine.libraries;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;

public class WaveLibrary {

	private List<Wave> myWaves;
	
	public WaveLibrary(){
		this.myWaves = new ArrayList<>();
	}
	
	public void addWave(Wave wave) {
		this.myWaves.add(wave);
	}
	
	public List<Wave> getWaves(){
		return myWaves;
	}

	public void addUnitToWave(String waveName, Unit enemy, int spawnTime) {
		for(Wave w : myWaves){
			if(w.getName().equals(waveName)){
				w.addSpawningUnit(enemy, spawnTime);
			}
		}
	}

}