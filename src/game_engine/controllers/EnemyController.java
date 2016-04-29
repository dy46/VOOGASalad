package game_engine.controllers;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;

public class EnemyController {

	private LevelController myLevelController;
	private UnitController myUnitController;
	private BoundingController myBoundingController;
	
	public EnemyController(LevelController levelController, UnitController unitController){
		this.myLevelController = levelController;
		this.myUnitController = unitController;
		this.myBoundingController = new BoundingController(this);
	}
	
	public double getMaxBoundingDistance(){
		return myBoundingController.getMaxBounding();
	}
	
	public List<Unit> getBoundingEnemies(){
		List<Unit> unspawned = getUnspawnedEnemies();
		List<Unit> active = getActiveEnemies();
		List<Unit> bounding = new ArrayList<>();
		bounding.addAll(unspawned);
		bounding.addAll(active);
		return bounding;
	}
	
	public Wave getCurrentWave(){
		return myLevelController.getCurrentLevel().getCurrentWave();
	}
	
	private List<Unit> getUnspawnedEnemies(){
		return myLevelController.getCurrentLevel().getCurrentWave().getSpawningUnits();
	}
	
	private List<Unit> getActiveEnemies(){
		List<Unit> placedUnits = myUnitController.getUnitType("Enemy");
		placedUnits.stream().filter(e -> getCurrentWave().getPlacingUnits().contains(e) && e.isAlive() && e.isVisible());
		return placedUnits;
	}
	
}