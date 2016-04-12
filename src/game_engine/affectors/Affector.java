package game_engine.affectors;

import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public class Affector {

	private List<Double> baseNumbers;
	// this specifies how many ticks the affector applies its effect (it's "time to live")
	private int TTL;
	private int elapsedTime;
	private List<Function> myFunctions;
	private IPlayerEngineInterface engineWorkspace;

	/**
	 * Applies an effect to a unit by altering the 
	 * UnitProperties of a GameElement object. The effect is determined
	 * by the implementation of the method (this could involve)
	 * decrementing health, increasing/decreasing speed, etc.
	 * The overall effect is dependent on which properties are changed
	 *
	 * @param  properties  A UnitProperties object that represents the current state of the GameElement 
	 *
	 *
	 */

	public Affector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
		this.myFunctions = functions;
		this.elapsedTime = 0;
		this.engineWorkspace = engineWorkspace;
	}

	public Affector(){
		this.elapsedTime = 0;
	}

	public Affector copyAffector() {
		//may need to copy functions too
		Affector copy = null;
		try {
			copy = (Affector) Class.forName(this.getClass().getName())
					.getConstructor(List.class, IPlayerEngineInterface.class)
					.newInstance(this.getFunctions(), this.getEngineWorkspace());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		copy.setBaseNumbers(this.getBaseNumbers());
		copy.setTTL(this.getTTL());
		return copy;
	}

	public void apply(UnitProperties properties) {
		updateElapsedTime();
	};

	public int getElapsedTime(){
		return elapsedTime;
	}

	public void updateElapsedTime(){
		elapsedTime++;
	}
	
	public void setElapsedTime(int elapsedTime) {
	    this.elapsedTime = elapsedTime;
	}

	public List<Double> getBaseNumbers () {
		return baseNumbers;
	}

	public void setBaseNumbers (List<Double> baseNumbers) {
		this.baseNumbers = baseNumbers;
	}

	public int getTTL () {
		return TTL;
	}
	
	public void setElapsedTimeToDeath() {
	    this.setElapsedTime(this.getTTL());
	}
	
	public void setTTL(int TTL) {
		this.TTL = TTL;
	}

	public List<Function> getFunctions(){
		return myFunctions;
	}
	
	public IPlayerEngineInterface getEngineWorkspace() {
	        return engineWorkspace;
	}
	
	public void setEngineWorkspace(IPlayerEngineInterface engineWorkspace) {
	    this.engineWorkspace = engineWorkspace;
	}

}