package game_engine.timelines;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Unit;

public class EndEvent {

	private Field myLimitedField;
	private double myLimitedValue;
	private Unit myLimitedUnit;
	private static final List<String> events = Arrays.asList("<", ">", "==");
	private int eventValue;

	public EndEvent(Field field, Unit unit, double value, String event){
		this.myLimitedField = field;
		this.myLimitedValue = value;
		this.myLimitedUnit = unit;
		eventValue = events.indexOf(event);
	}

	public boolean checkEvent(Unit unit){
		System.out.println("CHECKING END EVENT");
		double unitValue = 0;
		System.out.println("NAME: " + myLimitedField.getName());
		System.out.println(myLimitedField.getDeclaringClass());
		try {
			unitValue = (double) myLimitedField.getDouble(myLimitedUnit);
			System.out.println("UNIT VALUE SET");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception: variable must be numerical
		}
		System.out.println("END EVENT " + unitValue);
		if(eventValue == 0)	return unitValue < myLimitedValue;
		else if(eventValue == 1)	return unitValue > myLimitedValue;
		else return unitValue == myLimitedValue;
	}

}