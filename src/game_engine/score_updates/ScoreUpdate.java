package game_engine.score_updates;

import java.util.List;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.GameEngineInterface;


public abstract class ScoreUpdate {

    public ScoreUpdate () {
    };

    public abstract void updateScore (GameEngineInterface engineWorkspace, Level currentLevel);

    public void updateLivesFromUnitList (List<Unit> unitList, GameEngineInterface engineWorkspace) {
        engineWorkspace.getLevelController().decrementLives(unitList.size());
    }

    public void updateScoreFromUnitList (List<Unit> unitList, GameEngineInterface engineWorkspace) {
        unitList.stream().forEach(e -> engineWorkspace.getLevelController().setScore(engineWorkspace.getLevelController().getScore() +
                          e.getProperties().getPrice().getValue()));
    }

    public void removeAllInvisibleEnemies (GameEngineInterface engineWorkspace,
                                           Level currentLevel) {
        engineWorkspace.getUnitController().getUnitType("Enemy")
                .removeIf(e -> currentLevel.isEnemyAtGoal(e));
        engineWorkspace.getUnitController().getUnitType("Enemy").removeIf(e -> !e.isVisible());
    }

}
