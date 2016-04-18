package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;

public class RandomPathFollowAffector extends PathFollowAffector{

	public RandomPathFollowAffector(AffectorData data) {
		super(data);
	}

	public Position getNextPosition(Unit u) {
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		Branch currentBranch = move.getCurrentBranch();
		if(currentBranch == null){
			getWS().decrementLives();
			return null;
		}
		Position next = currentBranch.getNextPosition(currentPosition);
		if(next == null){
			currentBranch = pickRandomBranch(u);
			u.getProperties().getMovement().setCurrentBranch(currentBranch);
			if(currentBranch == null){
				getWS().decrementLives();
				return null;
			}
			next = currentBranch.getFirstPosition();
		}
		return next;
	}

	private Branch pickRandomBranch(Unit u) {
		List<Branch> choices = getBranchChoices(u);
		int random = (int) (Math.random()*choices.size());
		return choices.get(random);
	}

}
