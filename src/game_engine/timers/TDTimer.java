package game_engine.timers;
import game_engine.game_elements.Tower;
import game_engine.games.IPlayerEngineInterface;
import game_engine.games.TD.TDGame;

public class TDTimer extends GameTimer{

	private TDGame myGame;

	public TDTimer(TDGame workspace) {
		super(workspace);
		this.myGame = workspace;
	}

	public void updateElements() {
		if(!myGame.isPaused() && !myGame.isGameOver()){
			myGame.getTowers().forEach(t -> t.update());
			myGame.getTowers().forEach(t -> ((Tower) t).fire());
			myGame.getTowers().forEach(e -> e.update());
			myGame.getCollisionDetector().resolveEnemyCollisions(myGame.getProjectiles(), myGame.getTerrains());
			myGame.addEnemy(myGame.getCurrentLevel().update());
			// tries to spawn new enemies using Waves
			if(myGame.getCurrentWave().isFinished()){
				myGame.clearProjectiles();
				myGame.setPaused();
				myGame.getCurrentLevel().getWaveTimer().reset();
			}       
		}
		else if(myGame.getCurrentLevel().getNextWave() != null && myGame.getCurrentLevel().getNextWave().getTimeBeforeWave() <= myGame.getCurrentLevel().getWaveTimer().getTicks()){
			myGame.continueWaves();
		}
		myGame.getProjectiles().forEach(p -> p.update());
		myGame.getTerrains().forEach(t -> t.update());
		updateLives();
	}

	public void updateLives () {
		int livesToSubtract = 0;
		for (int i = 0; i < myGame.getEnemies().size(); i++) {
			if (myGame.getEnemies().get(i).getProperties().getMovement().isUnitAtLastPosition(myGame.getEnemies().get(i))) {
				livesToSubtract++;
				myGame.getEnemies().get(i).setElapsedTimeToDeath();
			}
		}
		myGame.getCurrentLevel().setMyLives(myGame.getCurrentLevel().getStartingLives() - livesToSubtract);
	}

	public void setWorkspace(IPlayerEngineInterface ws) throws Exception{
		super.setWorkspace(ws);
		if(ws instanceof TDGame)
			myGame = (TDGame) ws;
		else{
			throw new Exception("TD Timer requires TD Workspace");
		}
	}

}