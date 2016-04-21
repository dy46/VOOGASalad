package game_engine.factories;

import java.util.List;

import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.libraries.WaveLibrary;

public class WaveFactory {
	
	private WaveLibrary myWaveLibrary;

	public WaveFactory(){
		myWaveLibrary = new WaveLibrary();
	}
	
	public WaveFactory(WaveLibrary waveLibrary){
		myWaveLibrary = waveLibrary;
	}
	
	public void createWave(String name, List<Unit> enemies, List<Integer> spawnTimes){
		myWaveLibrary.addWave(new Wave(name, enemies, spawnTimes));
	}
	
	public void addEnemyToWave(String waveName, Unit enemy, int spawnTime){
		myWaveLibrary.addUnitToWave(waveName, enemy, spawnTime);
	}
	
	public WaveLibrary getWaveLibrary(){
		return myWaveLibrary;
	}
	
}