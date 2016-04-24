package game_engine.score_updates;

import java.util.List;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.GameEngineInterface;
import game_engine.properties.Position;

public abstract class ScoreUpdate {

	public ScoreUpdate() {};

	public abstract void updateScore(GameEngineInterface engineWorkspace, Level currentLevel); 

	public boolean isEnemyAtGoal(Unit e, Level currentLevel) {
		for(Position p : currentLevel.getGoals()) {
			if(e.getProperties().getPosition().equals(p)) {
				return true;
			}
		}
		return false;
	}

	public void updateLivesFromUnitList(List<Unit> unitList, GameEngineInterface engineWorkspace) {
		engineWorkspace.decrementLives(unitList.size());
	}

	public void updateScoreFromUnitList(List<Unit> unitList, GameEngineInterface engineWorkspace) {
		unitList.stream().forEach(e -> engineWorkspace.setScore(engineWorkspace.getScore() 
				+ e.getProperties().getPrice().getValue()));
	}
	public void removeAllInvisibleEnemies(GameEngineInterface engineWorkspace, Level currentLevel) {
		engineWorkspace.getEnemies().removeIf(e -> isEnemyAtGoal(e, currentLevel));
		engineWorkspace.getEnemies().removeIf(e -> !e.isVisible());
	}

}
