package game_engine.factories;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import exceptions.CorrectableException;
import exceptions.WompException;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorData;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.libraries.UnitLibrary;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Mass;
import game_engine.properties.Position;
import game_engine.properties.Price;
import game_engine.properties.State;
import game_engine.properties.Team;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;

public class UnitFactory {

	private UnitLibrary myUnitLibrary;

	public UnitFactory(){
		myUnitLibrary = new UnitLibrary();
	}

	public Unit createUnit(String name, UnitProperties unitProperties){
		return new Unit(name, unitProperties);
	}
	
	// Pass field inputs here
	public Unit createUnit(HashMap<String, String> inputs){
		UnitProperties unitProperties = new UnitProperties();
		String name = getUnitName(inputs.get("Name"));
		unitProperties.setHealthProp(getUnitHealth(inputs.get("Health")));
		unitProperties.setPriceProp(getUnitPrice(inputs.get("Price")));
		unitProperties.setMassProp(getUnitMass(inputs.get("Mass")));
		unitProperties.setTeamProp(getUnitTeam(inputs.get("Team")));
		unitProperties.setStateProp(getUnitState(inputs.get("State")));
		return createUnit(name, unitProperties);
	}
	
	private String getUnitName(String str){
		String name = str;
		if(name == null){
			CorrectableException we = new CorrectableException("Invalid name. Please enter a name.", String.class);
			name = we.getResult();
		}
		return name;
	}
	
	private Health getUnitHealth(String str){
		double health = 0;
		try{
			Double.parseDouble(str);
		}
		catch(Exception e){
			CorrectableException we = new CorrectableException("Invalid health value. Please enter a number", Double.class);
			health = Double.parseDouble(we.getResult());
		}
		return new Health(health);
	}
	
	private Price getUnitPrice(String str){
		double price = 0;
		try{
			Double.parseDouble(str);
		}
		catch(Exception e){
			CorrectableException we = new CorrectableException("Invalid price value. Please enter a number", Double.class);
			price = Double.parseDouble(we.getResult());
		}
		return new Price(price);
	}
	
	private Mass getUnitMass(String str){
		double mass = 0;
		try{
			Double.parseDouble(str);
		}
		catch(Exception e){
			CorrectableException we = new CorrectableException("Invalid mass value. Please enter a number", Double.class);
			mass = Double.parseDouble(we.getResult());
		}
		return new Mass(mass);
	}
	
	private Team getUnitTeam(String str){
		double team = 0;
		try{
			Double.parseDouble(str);
		}
		catch(Exception e){
			CorrectableException we = new CorrectableException("Invalid team value. Please enter a number", Double.class);
			team = Double.parseDouble(we.getResult());
		}
		return new Team(team);
	}
	
	private State getUnitState(String str){
		double state = 0;
		try{
			Double.parseDouble(str);
		}
		catch(Exception e){
			CorrectableException we = new CorrectableException("Invalid team value. Please enter a number", Double.class);
			state = Double.parseDouble(we.getResult());
		}
		return new State(state);
	}

	public void setUnitType(Unit unit, String type) throws WompException{
		if(unit.getProperties().getMovement().getSpawn() == null){
			throw new WompException("Unit needs a spawn.");
		}
		Affector a = getPathFollowingAffector(type);
		a.setTTL(Integer.MAX_VALUE);
		setupPathFollowingType(unit, type);
	}

	private void setupPathFollowingType(Unit unit, String type){
		List<String> pathTypes = Arrays.asList("PathFollowPositionMoveAffector", "RandomPathFollowAffector", "AIPathFollowAffector");
		for(String p : pathTypes){
			if(!p.equals(type))
				unit.removeAffectorsByName(p);
		}
	}

	private Affector getPathFollowingAffector(String type) throws WompException{
		Class<?> clazz = null;
		Constructor<?> constructor = null;
		Object instance = null;
		try {
			clazz = Class.forName(type);
			constructor = clazz.getConstructor(String.class, Integer.class);
			instance = constructor.newInstance(new AffectorData());
		} catch (Exception e) {
			throw new WompException("Invalid path affector.");
		}
		return (Affector) instance;
	}

	public void addChildrenToUnit(String name, List<Unit> children){
		myUnitLibrary.getUnitByName(name).addChildren(children);
	}

	public void addAffectorsToUnit(String name, List<Affector> affectors){
		myUnitLibrary.getUnitByName(name).addAffectors(affectors);
	}

	public void addAffectorsToApplyToUnit(String name, List<Affector> affectorsToApply){
		myUnitLibrary.getUnitByName(name).addAffectorsToApply(affectorsToApply);
	}

	public void setSpawnForUnit(String name, Position spawn){
		myUnitLibrary.getUnitByName(name).getProperties().getMovement().setSpawn(spawn);
	}

	public void addBranchesToUnit(String name, List<Branch> branches){
		myUnitLibrary.getUnitByName(name).getProperties().getMovement().setBranches(branches);
	}

	public void addStartingBranch(String name, Branch startingBranch){
		myUnitLibrary.getUnitByName(name).getProperties().getMovement().setBranches(Arrays.asList(startingBranch));
	}

	public void setNewPropertiesForUnit(String name, UnitProperties newProperties){
		myUnitLibrary.getUnitByName(name).setProperties(newProperties);
	}

	public void setNewBoundPositionsForUnit(String name, List<Position> bounds){
		myUnitLibrary.getUnitByName(name).getProperties().getBounds().setPositions(bounds);
	}

	public void setNewBoundsForUnit(String name, Bounds bounds){
		myUnitLibrary.getUnitByName(name).getProperties().getBounds().setPositions(bounds.getPositions());
	}

	public void setNewHealthForUnit(String name, double health){
		myUnitLibrary.getUnitByName(name).getProperties().getHealth().setValue(health);
	}

	public void setNewPriceForUnit(String name, double price){
		myUnitLibrary.getUnitByName(name).getProperties().getPrice().setPrice(price);
	}

	public void setNewBranchesForUnit(String name, List<Branch> branches){
		myUnitLibrary.getUnitByName(name).getProperties().getMovement().setBranches(branches);
	}

	public void setNewSpeedForUnit(String name, double speed){
		myUnitLibrary.getUnitByName(name).getProperties().getVelocity().setSpeed(speed);
	}

	public void setNewDirectionForUnit(String name, double direction){
		myUnitLibrary.getUnitByName(name).getProperties().getVelocity().setDirection(direction);
	}

	public void setNewVelocityForUnit(String name, Velocity velocity){
		setNewDirectionForUnit(name, velocity.getDirection());
		setNewSpeedForUnit(name, velocity.getSpeed());
	}

	public void addNewAffectorForUnit(String name, Affector affector){
		myUnitLibrary.getUnitByName(name).addAffectors(Arrays.asList(affector));
	}

	public void addNewAffectorToApplyForUnit(String name, Affector affectorToApply){
		myUnitLibrary.getUnitByName(name).addAffectorsToApply(Arrays.asList(affectorToApply));
	}
	
	public List<String> getFields(){
		return Arrays.asList("Unit type", "Unit Properties");
	}

}