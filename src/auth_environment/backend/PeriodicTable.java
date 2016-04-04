package auth_environment.backend;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Tower;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class holds all created Game Elements. The Developer can click on elements
 * and place them on the map or add them to the game.
 *
 * Developer should be able to click on a Game Element. This copies the Game Element
 * to be placed on the MapDisplay.
 * 
 * Features:
 * 1) Developer should be able to click on a GameElement to edit its Properties
 * 2) Developer should be able to drag the GameElement onto the Map if it is place-able
 * 3) Adding a new GameElement or renaming an existing one should check for duplicate names. If so, the Developer
 * should be asked whether they want to overwrite the old one. 
 */

public class PeriodicTable {
	
	// TODO: include other Game Elements. Let's test with just these two for now. 
	private Family towers = new Family(); 
	private Family enemies = new Family(); 
	
	public PeriodicTable() {
		
	}

	// Ask: should we use Reflection to add to the correct Family? 
	public void addTower(Tower tower) {
		this.towers.addElement(tower);
	}
	
	public void addEnemy(Enemy enemy) {
		this.enemies.addElement(enemy);
	}
}
