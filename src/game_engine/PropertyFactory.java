package game_engine;

import game_engine.functions.Function;
import game_engine.properties.Velocity;

public class PropertyFactory {

	public EngineWorkspace myWorkspace;
	
	public PropertyFactory(EngineWorkspace workspace){
		this.myWorkspace = workspace;
	}
	
	public Velocity createVelocity(Function speedFunction, Function directionFunction){
		Velocity velocity = new Velocity(speedFunction, directionFunction);
		velocity.setWorkspace(myWorkspace);
		return velocity;
	}
	
}