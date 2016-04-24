package auth_environment.Models;

import java.util.List;

import auth_environment.Models.Interfaces.IWaveOverviewTabModel;

import java.util.ArrayList;

import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.libraries.UnitLibrary;

public class WaveOverviewTabModel implements IWaveOverviewTabModel{
	private Wave myWave;
	private UnitLibrary myLibrary;
	
	public WaveOverviewTabModel(UnitLibrary lib, String name, int spawnTime){
		this.myWave = new Wave(name, spawnTime);
		this.myLibrary = lib;
	}
	
	@Override
	public void addSpawningUnit(String name, int time){
		Unit u = myLibrary.getUnitByName(name);
		if(u != null){
			this.myWave.addSpawningUnit(u, time);
		}
		// TODO: throw an error if the unit cannot be found
	}

	@Override
	public List<String> getEnemyInfo() {
		List<String> ret = new ArrayList<String>();
		for(Unit u : this.myWave.getSpawningUnits()){
			
		}
		return ret;
	}

	@Override
	public void addSpwaningUnits(List<String> names, List<Integer> times) {
		for(int i = 0;i < names.size();i++){
			this.addSpawningUnit(names.get(i), times.get(i));
		}
	}
	
	
	
}
