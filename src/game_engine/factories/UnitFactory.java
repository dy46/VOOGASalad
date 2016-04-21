package game_engine.factories;

import java.util.Arrays;
import java.util.List;

import exceptions.WompException;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.libraries.UnitLibrary;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
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
	
	public void setUnitMode(Unit unit, String mode){
		if(mode.equals("Artificial Intelligence")){
			List<Branch> branches = unit.getProperties().getMovement().getBranches();
			if(branches.size() > 1){
				unit.getProperties().getMovement().setBranches(Arrays.asList(branches.get(0)));
			}
			else{
				try {
					throw new WompException("AI unit needs a spawn");
				} catch (WompException e) {
					
				}
			}
		}
		else if(mode.equals("Path Following")){
			
		}
		else if (mode.equals("Random Branching")){
			
		}
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
	
}