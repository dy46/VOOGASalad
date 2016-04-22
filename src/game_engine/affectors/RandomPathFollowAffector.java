package game_engine.affectors;

import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class RandomPathFollowAffector extends PathFollowAffector{

	public RandomPathFollowAffector(AffectorData data) {
		super(data);
	}

	public Position respondToPosition(Unit u, Position next) {
		if(next == null){
			Branch currentBranch = pickRandomBranch(u);
			if(currentBranch == null){
				return getCurrentPosition(u);
			}
			u.getProperties().getMovement().setCurrentBranch(currentBranch);
			next = currentBranch.getFirstPosition();
		}
		return next;
	}

	private Branch pickRandomBranch(Unit u) {
		List<Branch> choices = getBranchChoices(u);
		if(choices.size() == 0)
			return null;
		int random = (int) (Math.random()*choices.size());
		return choices.get(random);
	}

}
