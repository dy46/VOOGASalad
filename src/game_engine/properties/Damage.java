package game_engine.properties;

import java.util.Arrays;
import java.util.List;

public class Damage extends Property{
	
	private double myDamage;
	
	public Damage(double damage){
		this.myDamage = damage; 
	}
	
	public double getDamage(){
		return myDamage; 
	}
	
	public void setDamage(double damage){
		myDamage = damage; 
	}
	
	public Damage copyDamage() {
	    return new Damage(this.getDamage());
	}

	@Override
	public void changeValues(List<Double> values) {
		myDamage = values.get(0);
	}

	@Override
	public List<Double> getValues() {
		return Arrays.asList(myDamage);
	}
	
}