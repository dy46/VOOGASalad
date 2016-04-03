package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

import game_engine.functions.Function;
import game_engine.properties.Position;

public class Wave extends MapPiece{

	private List<Enemy> myEnemies;
	private Level myLevel;

	public Wave(String name){
		super(name);
		setID(getWorkspace().getIDFactory().createID(this));
		initialize();
	}

	private void initialize(){
		myEnemies = new ArrayList<>();
	}
	
	public void setLevel(Level level){
		myLevel = level;
	}

	/*
	 * Returns the number of enemies left in this wave
	 */
	public int getEnemiesLeft(){
		int numEnemies = 0;
		for(Enemy e: myEnemies){
			if(e.isAlive()){
				numEnemies++;
			}
		}
		return numEnemies;
	}
	
	public boolean isFinished(){
		return getEnemiesLeft() == 0;
	}
	
	/*
	 * Spawns an enemy at the spawn location of the level
	 */
	public void spawnEnemy(){
		Position spawnPosition = myLevel.getSpawnPosition();
		Function speedFunction = getWorkspace().getFunctionFactory().createConstantFunction(0.5);
		Function directionFunction = getWorkspace().getFunctionFactory().createConstantFunction(0);
		Enemy enemy = getWorkspace().getEnemyFactory().createEnemy("Spawned", speedFunction, directionFunction, getWorkspace().getInfiniteTime());
		enemy.getProperties().setPosition(spawnPosition.getX(), spawnPosition.getY());
		getWorkspace().addEnemy(enemy);
	}
	
}