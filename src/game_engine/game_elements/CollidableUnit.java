package game_engine.game_elements;

import java.util.List;

import game_engine.affectors.Affector;

public class CollidableUnit extends Unit{

	private List<Affector> affectorsToApply;

	public CollidableUnit(String name, List<Affector> affectors) {
		super(name, affectors);
		// TODO Auto-generated constructor stub
	}

	public CollidableUnit(String name) {
		super(name);
	}

	public List<Affector> getAffectorsToApply() {
		return affectorsToApply;
	}
	
	public void setAffectorsToApply (List<Affector> affectorsToApply) {
        this.affectorsToApply = affectorsToApply;
    }

}