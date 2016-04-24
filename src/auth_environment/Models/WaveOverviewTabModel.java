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
			ret.add(u.getName()); // TODO: maybe some other statistics?
		}
		return ret;
	}

	@Override
	public void addSpawningUnits(List<String> names, List<Integer> times) {
		for(int i = 0;i < names.size();i++){
			this.addSpawningUnit(names.get(i), times.get(i));
		}
	}

	@Override
	public void removeSpawningUnit(int index) {
		this.myWave.removeSpawningUnit(index);
	}

	@Override
	public void addSpawningUnit(String name) {
		this.addSpawningUnit(name, DEFAULT_TIME);
	}

	@Override
	public void addPlacingUnit(String name) {
		Unit u = this.myLibrary.getUnitByName(name);
		if(u != null){
			this.myWave.addPlacingUnit(u);
		}
	}

	@Override
	public void addPlacingUnits(List<String> names) {
		names.forEach(name -> this.addPlacingUnit(name));
	}

	@Override
	public void removePlacingUnit(int index) {
		this.myWave.removePlacingUnit(index);
	}
	
	
	
	
	
}
