package game_engine.properties;

import java.util.Arrays;
import java.util.List;

public class Mass extends Property{
	
	private double myMass;

	public List<Double> getValues() {
		return Arrays.asList(myMass);
	}

	public void setValues(List<Double> values) {
		myMass = values.get(0);
	}
	
	public double getMass(){
		return myMass;
	}

}