package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.buildingBlocks.*;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorLibrary;
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Tower;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;


public class TowerFactory {

    private AffectorLibrary myAffectorLibrary;
    private TowerBuildingBlock block; 

    public TowerFactory(AffectorLibrary affectorLibrary){
            this.myAffectorLibrary = affectorLibrary;
    }
    
    public Tower createOneWayTower(String name, List<Projectile> allProjectiles, Position startingPosition, TowerBuildingBlock eleBlock){
    	List<Projectile> myProjectiles = new ArrayList<Projectile>(); 
    	Affector projectileMove = myAffectorLibrary.getAffector("Constant", "PositionMove");
    	projectileMove.setTTL(Integer.MAX_VALUE);
    	
    	Projectile p = new Projectile("ConstantHealth", Arrays.asList(projectileMove));
    	p.setTTL(Integer.MAX_VALUE);
    	p.setFireRate(60);
    	Velocity projVelocity = eleBlock.getMyVelocity();
    	Bounds b = eleBlock.getMyBounds(); 
    	UnitProperties properties = new UnitProperties(null, null, null, projVelocity, b, startingPosition.copyPosition(), null);
    	p.setProperties(properties);
    	myProjectiles.add(p);
    	
    	Affector damage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
    	damage.setTTL(1);
    	damage.setBaseNumbers(Arrays.asList(new Double(1)));
    	p.setAffectorsToApply(Arrays.asList(damage));
    	
    	return createSpecifiedTower("OneWay", allProjectiles, myProjectiles); 
	}
    
    
    public Tower createFourWayTower(String name, List<Projectile> allProjectiles, Position startingPosition){
        List<Projectile> myProjectiles = new ArrayList<Projectile>();
        Affector projectileMove = myAffectorLibrary.getAffector("Constant", "PositionMove");
        projectileMove.setTTL(Integer.MAX_VALUE);
        
        Projectile p = new Projectile("ConstantHealth", Arrays.asList(projectileMove));
        p.setTTL(Integer.MAX_VALUE);
        p.setFireRate(60);
        Velocity velocity = new Velocity(0.5, 180);        
        List<Position> l1 = new ArrayList<>();
        l1.add(new Position(0,0));
        l1.add(new Position(62,0));
        l1.add(new Position(62,62));
        l1.add(new Position(0,62));
        Bounds b = new Bounds(l1);
        
        UnitProperties properties = new UnitProperties(null, null, null, velocity, b, startingPosition.copyPosition(), null);
        p.setProperties(properties);
        myProjectiles.add(p);
        
        Projectile p2 = p.copyProjectile();
        p2.getProperties().setVelocity(0.5, 90);
        myProjectiles.add(p2);
        
        Projectile p3 = p.copyProjectile();
        p3.getProperties().setVelocity(0.5, 0);
        myProjectiles.add(p3);
        
        Projectile p4 = p.copyProjectile();
        p4.getProperties().setVelocity(0.5, 270);
        myProjectiles.add(p4);
        
        Affector damage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
        damage.setTTL(1);
        damage.setBaseNumbers(Arrays.asList(new Double(10)));
        p.setAffectorsToApply(Arrays.asList(damage));
        return createSpecifiedTower("OneWay", allProjectiles, myProjectiles);
    }
    
    public Tower createSpecifiedTower(String name, List<Projectile> allProjectiles, 
                                List<Projectile> myProjectiles) {
        List<Affector> affectors = new ArrayList<>();
        Tower t = new Tower(name, affectors, allProjectiles, myProjectiles);
        return t;
    }
    
}
