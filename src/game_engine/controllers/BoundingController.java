package game_engine.controllers;

import java.util.List;
import java.util.PriorityQueue;
import game_engine.game_elements.Unit;

public class BoundingController {

	private PriorityQueue<Unit> maxBoundingQueue;
	private EnemyController myEnemyController;
	
	public BoundingController(EnemyController enemyController){
		this.myEnemyController = enemyController;
		this.maxBoundingQueue = new PriorityQueue<Unit>(myEnemyController.getBoundingEnemies().size(), new BoundsComparator());
		setupBoundingQueue();
	}
	
	public double getMaxBounding(){
		Unit unit = maxBoundingQueue.peek();
		if (unit==null) {
			return 0.0; 
		}
		while(!unit.isAlive() || !unit.isVisible()){
			maxBoundingQueue.remove();
			unit = maxBoundingQueue.peek();
			if (unit==null) {
				return 0.0; 
			}
		}
		return unit.getProperties().getBounds().getMaxBoundingDistance();
	}
	
	private void setupBoundingQueue(){
		List<Unit> boundingEnemies = myEnemyController.getBoundingEnemies();
		for(Unit unit : boundingEnemies){
			this.maxBoundingQueue.add(unit);
		}
	}
	
}