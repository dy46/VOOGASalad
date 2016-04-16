package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;

public abstract class PathFollowAffector extends Affector{

	public PathFollowAffector(List<Function> functions){
		super(functions);
	}
	
	public void apply (Unit u) {
		super.apply(u);
		if (this.getElapsedTime() <= this.getTTL()) {
			double speed = u.getProperties().getVelocity().getSpeed();
			Movement move = u.getProperties().getMovement();
			for (int i = 0; i < speed; i++) {
				Position next = getNextPosition(u);
				if(next == null){
					u.kill();
					setElapsedTimeToDeath();
					return;
				}
				u.getProperties().getPosition().setX(next.getX());
				u.getProperties().getPosition().setY(next.getY());
				u.getProperties().getVelocity().setDirection(getNextDirection(u));
			}
			this.updateElapsedTime();
		}
	}
	
	public abstract Position getNextPosition(Unit u);
	public abstract Double getNextDirection(Unit u);
	
}