package game_engine.affectors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import auth_environment.paths.VisibilityGraph;
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
			return u.getProperties().getVelocity().getDirection();
		}
		return move.getNextDirection(currentPosition);
	}
	
	public List<Branch> getBranchChoices(Unit u){
		List<Branch> choices = u.getProperties().getMovement().getCurrentBranch().getNeighbors();
		VisibilityGraph vg = new VisibilityGraph(getWS());
		HashSet<Branch> visibleChoices = new HashSet<>();
		List<Branch> visibleBranches = vg.getVisibilityBranches();
		for(Branch choice : choices){
			for(Branch v : visibleBranches){
				if(choice.equals(v)){
					visibleChoices.add(choice);
				}
			}
		}
		return new ArrayList<>(visibleChoices);
	}
	
	public boolean isAccessible(Branch b, Position p){
		if(b.getPositions().contains(p)){
			return true;
		}
		return new VisibilityGraph(getWS()).isAccessibleFrom(b, p);
	}
	
}