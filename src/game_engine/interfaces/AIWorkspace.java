package game_engine.interfaces;

import java.util.List;
import game_engine.controllers.EnemyController;
import game_engine.controllers.LevelController;
import game_engine.controllers.UnitController;
import game_engine.game_elements.Branch;

public interface AIWorkspace {

	public List<Branch> getBranches();

	public LevelController getLevelController();

	public EnemyController getEnemyController();

	public UnitController getUnitController();

	public ICollisionDetector getCollisionDetector();

}