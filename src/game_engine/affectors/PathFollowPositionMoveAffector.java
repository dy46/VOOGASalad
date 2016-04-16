package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;


/*
 * This class modifies units based on their set speed, as well as the path that they are supposed to
 * follow.
 * This is done by calling the apply method repeatedly based on their speed, and changing the
 * position of the unit
 * based on a sampled version of the path that has been drawn out for them.
 * 
 */
public class PathFollowPositionMoveAffector extends Affector {

	public PathFollowPositionMoveAffector(List<Function> functions){
		super(functions);
	}

	@Override
	public void apply (Unit u) {
		super.apply(u);
		System.out.println("APPLYING POSITION MOVE");
		if (this.getElapsedTime() <= this.getTTL()) {
			double speed = u.getProperties().getVelocity().getSpeed();
			Movement move = u.getProperties().getMovement();
			for (int i = 0; i < speed; i++) {
				Position next = getNextPosition(u);
				u.getProperties().getPosition().setX(next.getX());
				u.getProperties().getPosition().setY(next.getY());
				u.getProperties().getVelocity().setDirection(getNextDirection(u));
			}
			this.updateElapsedTime();
		}
	}

	public double getNextDirection(Unit u){
		// TODO: womp exception if nextDirection is null? this shouldn't happen
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		if(currentPosition.equals(move.getLastBranch().getLastPosition())) {
			return u.getProperties().getVelocity().getDirection();
		}
		return move.getCurrentBranch().getNextDirection(currentPosition);
	}

	public Position getNextPosition(Unit u){
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		Branch currentBranch = move.getCurrentBranch();
		List<Branch> branches = move.getBranches();
		if(currentBranch == null){
			return move.getLastBranch().getLastPosition();
		}
		Position next = currentBranch.getNextPosition(currentPosition);
		if(next == null){
			System.out.println("My branches: " + branches.size());
			currentBranch = move.getNextBranch();
			if(currentBranch == null) {
				return move.getLastBranch().getLastPosition();
			}
			next = currentBranch.getFirstPosition();
		}
		System.out.println("MOVED");
		return next;
	}

}