package game_engine.game_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


/*
 * External API interface that will be available to the authoring environment for extension and
 * creation
 * of XML files for use in games. API specifies some basic functionality of towers and which methods
 * need to
 * be implemented for subclasses created in the authoring environment.
 */
public class Tower extends Unit {

	private List<Unit> allProjectiles;
	private List<Projectile> myProjectiles;
	private List<Unit> allTowers;

	public Tower (String name, List<AffectorTimeline> timelines, int numFrames) {
		super(name, timelines, numFrames);
		// setID(getWorkspace().getIDFactory().createID(this));
	}


	public Tower (String name, List<AffectorTimeline> timelines, List<Unit> allProjectiles, 
			List<Projectile> myProjectiles, List<Unit> allTowers, int numFrames) {
		super(name, timelines, numFrames);
		this.allProjectiles = allProjectiles;
		this.myProjectiles = myProjectiles;
		this.allTowers = allTowers;
		// setID(getWorkspace().getIDFactory().createID(this));
	}

	public Tower copyTower(double x, double y) {
		List<AffectorTimeline> copyTimelines = new ArrayList<>();
		List<Projectile> newMyProjectiles = myProjectiles.stream().map(p -> p.copyProjectile()).collect(Collectors.toList());
		for(int i = 0; i < newMyProjectiles.size(); i++) {
			List<Branch> branches = newMyProjectiles.get(i).getProperties().getMovement().getBranches();
			List<Position> newMyPositions = branches.get(0).getMyPositions().stream().map(p -> p.copyPosition()).collect(Collectors.toList());
			List<Branch> newBranches = Arrays.asList(new Branch("SomePath"));
			for(int j = 0; j < newMyPositions.size(); j++) {
				newMyPositions.get(j).addToXY(x - this.getProperties().getPosition().getX(), 
						y - this.getProperties().getPosition().getY());
				newBranches.get(0).addPosition(newMyPositions.get(j));
			}
			newMyProjectiles.get(i).getProperties().getMovement().setBranches(newBranches);
		}

		Tower copy = new Tower(this.toString(), copyTimelines, allProjectiles, newMyProjectiles, this.getAllTowers(), this.getNumFrames());
		copy.setTTL(this.getTTL());
		copy.setProperties(this.getProperties().copyUnitProperties());
		copy.getProperties().setPosition(x, y);
		copy.setDeathDelay(this.getDeathDelay());
		copy.setNumberList(this.getNumberList());
		return copy;
	}

	/*
	 * method for activating the tower attack (subclasses implement different types of attack
	 * types).
	 */
	public void fire () {
		List<Projectile> newProjectiles = myProjectiles.stream()
				.filter(p -> p.getElapsedTime() % p.getFireRate() == 0)
				.map(p -> p.copyProjectile()).collect(Collectors.toList());
		newProjectiles.forEach(p -> {
			p.getProperties().setPosition(getProperties().getPosition().getX(), 
					getProperties().getPosition().getY());
			p.setNumberList(Arrays.asList(new Double[]{findMyIndex()}));
			allProjectiles.add(p);
		});      
	}

	public void update () {
		super.update();
		myProjectiles.stream().forEach(p -> p.incrementElapsedTime(1));
	}

//	/*
//	 * sells the tower, and removes it from the list of active towers
//	 */
//	public void sell () {
//		super.sell(this);
//	}

	public double findMyIndex() {
		return new Double(allTowers.indexOf(this));
	}

	/*
	 * changes the UnitProperties of the tower to reflect an upgrade (higher damage, better armor,
	 * etc).
	 */
	public void upgrade (UnitProperties newProperties) {
		setProperties(newProperties);
	}

	public List<Unit> getAllTowers() {
		return allTowers;
	}
}