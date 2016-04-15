package game_engine.timelines;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Unit;

public class EndEvent {

	private Field myLimitedField;
	private double myLimitedValue;
	private Class myLimitedClass;
	private static final List<String> events = Arrays.asList("<", ">", "==");
	private int eventValue;

	public EndEvent(Field field, Class cls, double value, String event){
		this.myLimitedField = field;
		this.myLimitedValue = value;
		this.myLimitedClass = cls;
		eventValue = events.indexOf(event);
	}

	public boolean checkEvent(Unit unit){
		double unitValue = 0;
		try {
			unitValue = (double) myLimitedField.get(myLimitedClass);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception: variable must be numerical
		}
		if(eventValue == 0)	return unitValue < myLimitedValue;
		else if(eventValue == 1)	return unitValue > myLimitedValue;
		else return unitValue == myLimitedValue;
	}

}