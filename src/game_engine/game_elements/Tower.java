package game_engine.game_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
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

<<<<<<< HEAD
	private List<Unit> allProjectiles;
	private List<Projectile> myProjectiles;

	public Tower (String name, List<Affector> affectors, int numFrames) {
		super(name, affectors, numFrames);
		// setID(getWorkspace().getIDFactory().createID(this));
	}


	public Tower (String name, List<Affector> affectors, List<Unit> allProjectiles, 
			List<Projectile> myProjectiles, int numFrames) {
		super(name, affectors, numFrames);
		this.allProjectiles = allProjectiles;
		this.myProjectiles = myProjectiles;
		// setID(getWorkspace().getIDFactory().createID(this));
	}

	public Tower copyTower(double x, double y) {
		List<Affector> copyAffectors = new ArrayList<>();
		List<Projectile> newMyProjectiles = myProjectiles.stream().map(p -> p.copyProjectile()).collect(Collectors.toList());
		for(int i = 0; i < newMyProjectiles.size(); i++) {
			Path path = newMyProjectiles.get(i).getProperties().getPath();
			List<Position> newMyPositions = path.getMyPositions().stream().map(p -> p.copyPosition()).collect(Collectors.toList());
			Path newPath = new Path("SomePath");
			for(int j = 0; j < newMyPositions.size(); j++) {
				newMyPositions.get(j).addToXY(x - this.getProperties().getPosition().getX(), 
						y - this.getProperties().getPosition().getY());
				newPath.addPosition(newMyPositions.get(j));
			}
			newMyProjectiles.get(i).getProperties().setPath(newPath);
		}

		Tower copy = new Tower(this.toString(), copyAffectors, allProjectiles, newMyProjectiles, this.getNumFrames());
		copy.setTTL(this.getTTL());
		copy.setProperties(this.getProperties().copyUnitProperties());
		copy.getProperties().setPosition(x, y);
		copy.setDeathDelay(this.getDeathDelay());
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
			allProjectiles.add(p);
		});      
	}

	public boolean update () {
		boolean alive = super.update();
		if(alive)
			myProjectiles.stream().forEach(p -> p.incrementElapsedTime(1));
		return alive;
	}

	/*
	 * sells the tower, and removes it from the list of active towers
	 */
	public void sell () {
		super.sell(this);
	}

	/*
	 * changes the UnitProperties of the tower to reflect an upgrade (higher damage, better armor,
	 * etc).
	 */
	public void upgrade (UnitProperties newProperties) {
		setProperties(newProperties);
	}
=======
    private List<Unit> allProjectiles;
    private List<Projectile> myProjectiles;
    private List<Unit> allTowers;
    
    public Tower (String name, List<Affector> affectors, int numFrames) {
        super(name, affectors, numFrames);
        // setID(getWorkspace().getIDFactory().createID(this));
    }
    

    public Tower (String name, List<Affector> affectors, List<Unit> allProjectiles, 
                  List<Projectile> myProjectiles, List<Unit> allTowers, int numFrames) {
        super(name, affectors, numFrames);
        this.allProjectiles = allProjectiles;
        this.myProjectiles = myProjectiles;
        this.allTowers = allTowers;
        // setID(getWorkspace().getIDFactory().createID(this));
    }
    
    public Tower copyTower(double x, double y) {
        List<Affector> copyAffectors = new ArrayList<>();
        List<Projectile> newMyProjectiles = myProjectiles.stream().map(p -> p.copyProjectile()).collect(Collectors.toList());
        for(int i = 0; i < newMyProjectiles.size(); i++) {
            Path path = newMyProjectiles.get(i).getProperties().getPath();
            List<Position> newMyPositions = path.getMyPositions().stream().map(p -> p.copyPosition()).collect(Collectors.toList());
            Path newPath = new Path("SomePath");
            for(int j = 0; j < newMyPositions.size(); j++) {
                newMyPositions.get(j).addToXY(x - this.getProperties().getPosition().getX(), 
                                              y - this.getProperties().getPosition().getY());
                newPath.addPosition(newMyPositions.get(j));
            }
            newMyProjectiles.get(i).getProperties().setPath(newPath);
        }
        
        Tower copy = new Tower(this.toString(), copyAffectors, allProjectiles, newMyProjectiles, this.getAllTowers(), this.getNumFrames());
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
    
    /*
     * sells the tower, and removes it from the list of active towers
     */
    public void sell () {
        super.sell(this);
    }
    
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
>>>>>>> 2644dea77f1258a7f847453cb880537379bb2d20

}
