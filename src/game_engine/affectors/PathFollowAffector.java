package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.Property;

public abstract class PathFollowAffector extends Affector{

	public PathFollowAffector(AffectorData data){
		super(data);
	}

	public void apply (List<Function> functions, Property property, Unit u) {
		pathFollow(u);
	}

	public void pathFollow(Unit u) {
		if (this.getElapsedTime() <= this.getTTL()) {
			double speed = u.getProperties().getVelocity().getSpeed();
			for (int i = 0; i < speed; i++) {
				Position next = getNextPosOnBranch(u);
				next = respondToPosition(u, next);
				if(next == null){
					return;
				}
				u.getProperties().setPosition(next);
				u.getProperties().getVelocity().setDirection(getNextDirection(u));
			}
			this.updateElapsedTime();
		}
	}
	
	public abstract Position respondToPosition(Unit u, Position next);

	public Position getNextPosOnBranch(Unit u){
		Movement move = u.getProperties().getMovement();
		Branch currentBranch = move.getCurrentBranch();
		if (currentBranch == null) {
			return null;
		}
		Position currentPosition = u.getProperties().getPosition();
		return currentBranch.getNextPosition(currentPosition);
	}

	public Double getNextDirection(Unit u){
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		if(currentPosition.equals(move.getLastBranch().getLastPosition())) {
			return u.getProperties().getVelocity().getDirection();	
		}
		return move.getCurrentBranch().getNextDirection(currentPosition);
	}

	public List<Branch> getBranchChoicesOnPath(Unit u){
		return u.getProperties().getMovement().getCurrentBranch().getForwardNeighbors();
	}
	
	public Position getCurrentPosition(Unit u){
		return u.getProperties().getPosition();
	}

}