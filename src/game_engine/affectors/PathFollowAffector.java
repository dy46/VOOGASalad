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
                        Position next = getNextPosition(u);
                        if(next != null) {
                            u.getProperties().getPosition().setX(next.getX());
                            u.getProperties().getPosition().setY(next.getY());
                            u.getProperties().getVelocity().setDirection(getNextDirection(u));
                        }
                }
                this.updateElapsedTime();
            }
	}
	
	public abstract Position getNextPosition(Unit u);
	
	public Double getNextDirection(Unit u){
		Position currentPosition = u.getProperties().getPosition();
		Movement move = u.getProperties().getMovement();
		if(currentPosition.equals(move.getLastBranch().getLastPosition())) {
			//this is the end of the path
			return u.getProperties().getVelocity().getDirection();
		}
		return move.getNextDirection();
	}
	
	public List<Branch> getBranchChoices(Unit u){
		return u.getProperties().getMovement().getCurrentBranch().getForwardNeighbors();
	}
	
}