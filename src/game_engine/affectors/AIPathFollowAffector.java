package game_engine.affectors;
import java.util.List;
import game_engine.properties.Property;


public class AIPathFollowAffector extends PathFollowPositionMoveAffector {

	public AIPathFollowAffector (AffectorData data) {
		super(data);
	}

	public List<Double> transformValues (Property p, List<Double> values) {
		return values;
	}

}