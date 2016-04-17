package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.TowerBuildingBlock;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Tower;
import game_engine.libraries.AffectorLibrary;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;


public class TowerFactory {

	private AffectorLibrary myAffectorLibrary;

	public TowerFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
	}

	public Tower defineTowerModel(BuildingBlock block){
		TowerBuildingBlock tBlock = (TowerBuildingBlock) block;
		List<Affector> affectors = new ArrayList<>(); 
		Tower t = new Tower(tBlock.getMyName(), Arrays.asList(new AffectorTimeline(affectors)), null, null, null, 2);
		List<Position> l1 = new ArrayList<>();
		Health hp = tBlock.getMyHealth();
		Velocity velo = tBlock.getMyVelocity();
		State towerState = new State("Stationary");
		Movement movement = new Movement(Arrays.asList(new Branch("Something here")));      
		UnitProperties towerProp = new UnitProperties(hp, null, null, velo, null, null, null, null, towerState, movement, null); 
		t.setProperties(towerProp);
		t.setTTL(1000000);
		t.setDeathDelay(100);
		return t; 
	}

	public Tower createTackTower (String name,
			List<Unit> allProjectiles,
			List<Unit> myTowers,
			Position startingPosition) {
		List<Projectile> myProjectiles = new ArrayList<Projectile>();
		Affector move = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
		move.setTTL(Integer.MAX_VALUE);
		Projectile p = new Projectile("Tack", Arrays.asList(new AffectorTimeline(Arrays.asList(move))), 3);
		p.setDeathDelay(30);
		p.setTTL(60);
		p.setFireRate(30);
		Velocity velocity = new Velocity(0.5, 180); 
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0,0));
		l1.add(new Position(30,0));
		l1.add(new Position(30,30));
		l1.add(new Position(0,30));
		Bounds b = new Bounds(l1);
		State st = new State("Moving");
		Branch p2 = new Branch("MyBranch");
		Health h = new Health(30);
	        List<Position> l2 = new ArrayList<>();
	        l2.add(new Position(-100, -100));
	        l2.add(new Position(-100,100));
	        l2.add(new Position(100,100));
	        l2.add(new Position(100,-100));
	        Bounds range = new Bounds(l2);     
		p2.addPosition(startingPosition.copyPosition());
		p2.addPosition(new Position(startingPosition.getX()+636, startingPosition.getY()-636));
		UnitProperties properties = new UnitProperties(h, null, null, velocity, b, range, startingPosition.copyPosition(), null, st, new Movement(Arrays.asList(p2)), null);
		Affector damage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		damage.setTTL(1);
		damage.setBaseNumbers(Arrays.asList(new Double(5)));
		Affector stateToDamaging = myAffectorLibrary.getAffector("State", "Change");
		stateToDamaging.setBaseNumbers(Arrays.asList(new Double(4)));
		stateToDamaging.setTTL(1);
		p.setTimelinesToApply(Arrays.asList(new AffectorTimeline(Arrays.asList(new Affector[]{damage, stateToDamaging}))));
		p.setProperties(properties);
		
		Projectile pp2 = p.copyProjectile();
	        Affector move2 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
		pp2.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move2))));
		Branch path2 = new Branch("Something here");
		path2.addPosition(startingPosition.copyPosition());
		path2.addPosition(new Position(startingPosition.getX()-636, startingPosition.getY()+636));
		pp2.getProperties().setVelocity(0.5, 90);
		pp2.getProperties().setMovement(new Movement(Arrays.asList(path2)));
		
		
              Projectile pp3 = p.copyProjectile();
              pp3.setTTL(30);
              Affector move3 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
              pp3.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move3))));
              Branch path3 = new Branch("Something here");
              path3.addPosition(startingPosition.copyPosition());
              path3.addPosition(new Position(startingPosition.getX(), startingPosition.getY()+900));
              pp3.getProperties().setVelocity(0.5, 0);
              pp3.getProperties().setMovement(new Movement(Arrays.asList(path3)));

              Projectile pp4 = p.copyProjectile();
              pp4.setTTL(30);
                    Affector move4 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
                      pp4.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move4))));
              Branch path4 = new Branch("Something here");
              path4.addPosition(startingPosition.copyPosition());
              path4.addPosition(new Position(startingPosition.getX()+900, startingPosition.getY()));
              pp4.getProperties().setVelocity(0.5, 270);
              pp4.getProperties().setMovement(new Movement(Arrays.asList(path4)));


              Projectile pp5 = p.copyProjectile();
              pp5.setTTL(30);
                    Affector move5 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
                      pp5.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move5))));
              Branch path5 = new Branch("Something here");
              path5.addPosition(startingPosition.copyPosition());
              path5.addPosition(new Position(startingPosition.getX()+636, startingPosition.getY()+636));
              pp5.getProperties().setVelocity(0.5, 225);
              pp5.getProperties().setMovement(new Movement(Arrays.asList(path5)));

              Projectile pp6 = p.copyProjectile();
              pp6.setTTL(30);
                    Affector move6 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
                      pp6.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move6))));
              Branch path6 = new Branch("Something here");
              path6.addPosition(startingPosition.copyPosition());
              path6.addPosition(new Position(startingPosition.getX()-900, startingPosition.getY()));
              pp6.getProperties().setVelocity(0.5, 135);
              pp6.getProperties().setMovement(new Movement(Arrays.asList(path6)));

              Projectile pp7 = p.copyProjectile();
              pp7.setTTL(30);
                    Affector move7 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
                      pp7.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move7))));
              Branch path7 = new Branch("Something here");
              path7.addPosition(startingPosition.copyPosition());
              path7.addPosition(new Position(startingPosition.getX(), startingPosition.getY()-900));
              pp7.getProperties().setVelocity(0.5, 315);
              pp7.getProperties().setMovement(new Movement(Arrays.asList(path7)));

              Projectile pp8 = p.copyProjectile();
                    Affector move8 = myAffectorLibrary.getAffector("RangePathFollow", "PositionMove");
                      pp8.setTimelines(Arrays.asList(new AffectorTimeline(Arrays.asList(move8))));
              Branch path8 = new Branch("Something here");
              path8.addPosition(startingPosition.copyPosition());
              path8.addPosition(new Position(startingPosition.getX()-450, startingPosition.getY()-450));
              pp8.getProperties().setVelocity(0.5, 45);
              pp8.getProperties().setMovement(new Movement(Arrays.asList(path8)));

              myProjectiles.add(pp8);
              myProjectiles.add(pp7);
              myProjectiles.add(pp6);
              myProjectiles.add(pp5);
              myProjectiles.add(pp4);
              myProjectiles.add(pp3);
		myProjectiles.add(pp2);
		
		myProjectiles.add(p);
		
		return createSpecifiedTower(name, allProjectiles, myTowers, myProjectiles);
	}

	public Tower createHomingTower (String name,
			List<Unit> myProjectiles2,
			List<Unit> myTowers,
			Position startingPosition) {
		List<Projectile> myProjectiles = new ArrayList<Projectile>();
		Affector move = myAffectorLibrary.getAffector("Homing", "Move");
		move.setTTL(Integer.MAX_VALUE);
		Projectile p = new Projectile("Projectile", Arrays.asList(new AffectorTimeline(Arrays.asList(move))), 3);
		p.setDeathDelay(15);
		p.setTTL(1000000);
		p.setFireRate(30);
		Velocity velocity = new Velocity(2, 90);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0, 0));
		l1.add(new Position(30, 0));
		l1.add(new Position(30, 30));
		l1.add(new Position(0, 30));
		Bounds b = new Bounds(l1);
		State st = new State("Moving");
		Branch p2 = new Branch("Something here");
		p2.addPosition(startingPosition.copyPosition());
		p2.addPosition(new Position(startingPosition.getX(), startingPosition.getY() - 900));
		List<Position> l2 = new ArrayList<>();
	        l2.add(new Position(-100, -100));
	        l2.add(new Position(-100,100));
	        l2.add(new Position(100,100));
	        l2.add(new Position(100,-100));
	        Bounds range = new Bounds(l2);  
		UnitProperties properties =
				new UnitProperties(new Health(1), null, null, velocity, b, range,
						startingPosition.copyPosition(), null, st, new Movement(Arrays.asList(p2)), null);
		Affector damage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		damage.setTTL(1);
		damage.setBaseNumbers(Arrays.asList(new Double(10)));
		Affector stateToDamaging = myAffectorLibrary.getAffector("State", "Change");
		stateToDamaging.setBaseNumbers(Arrays.asList(new Double(4)));
		stateToDamaging.setTTL(1);
		p.setTimelinesToApply(Arrays.asList(new AffectorTimeline(Arrays.asList(new Affector[] { damage, stateToDamaging }))));
		p.setProperties(properties);
		myProjectiles.add(p);
		return createSpecifiedTower(name, myProjectiles2, myTowers, myProjectiles);
	}


	public Tower createSpecifiedTower(String name, List<Unit> myProjectiles2, List<Unit> myTowers, List<Projectile> myProjectiles) {
		List<Affector> affectors = new ArrayList<>();
		Tower t = new Tower(name, Arrays.asList(new AffectorTimeline(affectors)), myProjectiles2, myProjectiles, myTowers, 2);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0,0));
		l1.add(new Position(70,0));
		l1.add(new Position(70,55));
		l1.add(new Position(0,55));
		Bounds b = new Bounds(l1);
		Health health2 = new Health(50);
		Position position2 = new Position(200, 300);
		Velocity velocity2 = new Velocity(0, 180);
		State st = new State ("Stationary");
		Movement p2 = new Movement(Arrays.asList(new Branch("Something here")));
		UnitProperties properties2 = new UnitProperties(health2, null, null, velocity2, b, null, position2, null, st, p2, null);
		t.setProperties(properties2);
		t.setTTL(1000000);
		t.setDeathDelay(100);
		return t;
	}
}