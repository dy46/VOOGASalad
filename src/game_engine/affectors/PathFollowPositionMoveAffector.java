package game_engine.affectors;

import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


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
		if (this.getElapsedTime() <= this.getTTL()) {
			double speed = u.getProperties().getVelocity().getSpeed();
			Movement move = u.getProperties().getMovement();
			for (int i = 0; i < speed; i++) {
				System.out.println("UNIT: " + u);
				System.out.println(" Error?: ");
				System.out.println(move.getNextPosition(u.getProperties().getPosition()));
				Position next = move.getNextPosition(u.getProperties().getPosition());
				u.getProperties().getPosition().setX(next.getX());
				u.getProperties().getPosition().setY(next.getY());
				u.getProperties().getVelocity().setDirection(move.getNextDirection(u.getProperties().getPosition(),
																						  u.getProperties().getVelocity().getDirection()));
			}
			this.updateElapsedTime();
		}
	}

}
