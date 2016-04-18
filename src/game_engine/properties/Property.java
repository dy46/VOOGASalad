package game_engine.properties;

import java.util.List;

public abstract class Property {

	public abstract void changeValues(List<Double> values);
	public abstract List<Double> getValues();
	
}