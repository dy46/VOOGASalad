package game_engine.affectors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Property;
import game_engine.games.GameEngineInterface;

public abstract class Affector {

	private int TTL;
	private int elapsedTime;
	private GameEngineInterface engineWorkspace;
	private AffectorData myData;

	public Affector(AffectorData data){
		this.myData = data;
	}

	public void setWorkspace(GameEngineInterface workspace){
		this.engineWorkspace = workspace;
	}

	public Affector copyAffector() {
		//may need to copy functions too
		Affector copy = null;
		try {
			copy = (Affector) Class.forName(this.getClass().getName())
					.getConstructor(List.class)
					.newInstance(myData);
			copy.setWorkspace(this.getWS());
			//			copy.setEndEvents(this.myEndEvents);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//copy.setBaseNumbers(this.getBaseNumbers());
		copy.setTTL(this.getTTL());
		return copy;
	}

	public void apply(Unit u) {
		for(int x=0; x<getData().getUnitProperties().size(); x++){
			for(String prop : getData().getUnitProperties().get(x)){
				Property p = getProperty(u, prop);
				List<Function> functions = getData().getFunctions().get(x);
				int type = getData().getTypes().get(x);
				List<Double> values = getValues(p);
				if(type == 0){
					setValues(p, functions, values);
				}
				else if(type == 1){
					incrementValues(p, functions, values);
				}
				else if(type == 2){
					decrementValues(p, functions, values);
				}
			}
		}
		updateElapsedTime();
	}

	public void incrementValues(Property p, List<Function> functions, List<Double> values){
		for(int y=0; y<values.size(); y++){
			values.set(y, values.get(y) + functions.get(y).evaluate(getElapsedTime()));
		}
		p.changeValues(values);
	}

	public void decrementValues(Property p, List<Function> functions, List<Double> values){
		for(int y=0; y<values.size(); y++){
			values.set(y, values.get(y) - functions.get(y).evaluate(getElapsedTime()));
		}
		p.changeValues(values);
	}

	public void setValues(Property p, List<Function> functions, List<Double> values){
		for(int y=0; y<values.size(); y++){
			values.set(y, functions.get(y).evaluate(getElapsedTime()));
		}
		p.changeValues(values);
	}

	private Property getProperty(Unit u, String property){
		Class cls = null;
		try {
			cls = Class.forName("game_engine.properties." + property);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method method = null;
		try {
			method = cls.getDeclaredMethod("get" + property);
		} catch (NoSuchMethodException | SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Property p = null;
		try {
			p = ((Property) method.invoke(u.getProperties()));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return p;
	}

	private List<Double> getValues(Property p){
		List<Double> values = new ArrayList<>();
		try {
			values = p.getValues();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return values;
	}

	public int getElapsedTime(){
		return elapsedTime;
	}

	public void updateElapsedTime(){
		elapsedTime++;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	//	public List<Double> getBaseNumbers () {
	//		return baseNumbers;
	//	}
	//
	//	public void setBaseNumbers (List<Double> baseNumbers) {
	//		this.baseNumbers = baseNumbers;
	//	}

	public int getTTL () {
		return TTL;
	}

	public void setElapsedTimeToDeath() {
		this.setElapsedTime(this.getTTL());
	}

	public void setTTL(int TTL) {
		this.TTL = TTL;
	}

	public GameEngineInterface getWS() {
		return engineWorkspace;
	}

	public void setEngineWorkspace(GameEngineInterface engineWorkspace) {
		this.engineWorkspace = engineWorkspace;
	}

	public AffectorData getData(){
		return myData;
	}

}