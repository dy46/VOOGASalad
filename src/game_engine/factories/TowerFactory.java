package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.libraries.AffectorLibrary;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Mass;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;
import game_engine.store_elements.Store;


public class TowerFactory {

	private AffectorLibrary myAffectorLibrary;

	public TowerFactory (AffectorLibrary affectorLibrary) {
		this.myAffectorLibrary = affectorLibrary;
	}

	public Unit createTackTower (String name,
			List<Unit> allProjectiles,
			List<Unit> myTowers,
			Position startingPosition,
			Store myStore) {
		List<Unit> myProjectiles = new ArrayList<>();
		Affector move = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		move.setTTL(Integer.MAX_VALUE);
		Unit p =
				new Unit("Tack_Projectile", Arrays.asList(move), 3);
		p.setDeathDelay(30);
		p.setTTL(60);
		Velocity velocity = new Velocity(0.5, 180);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(-20, -20));
		l1.add(new Position(20, -20));
		l1.add(new Position(20, 20));
		l1.add(new Position(-20, 20));
		Bounds b = new Bounds(l1);
		State st = new State(2);
		Branch p2 = new Branch();
		Health h = new Health(30);
		List<Position> l2 = new ArrayList<>();
		l2.add(new Position(-100, -100));
		l2.add(new Position(-100, 100));
		l2.add(new Position(100, 100));
		l2.add(new Position(100, -100));
		Bounds range = new Bounds(l2);
		move.setTTL(60);
		p2.addPosition(startingPosition.copyPosition());
		p2.addPosition(new Position(startingPosition.getX() + 636, startingPosition.getY() - 636));
		UnitProperties properties =
				new UnitProperties(h, null, velocity, b, range, null, st,
						new Movement(startingPosition.copyPosition()), new Mass(1));
		Affector damage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		damage.setTTL(1);
		Affector stateToDamaging = myAffectorLibrary.getAffector("State", "Change");
		stateToDamaging.setTTL(1);
		p.setAffectorsToApply(Arrays.asList(new Affector[] { damage, stateToDamaging }));
		p.setProperties(properties);

		Unit pp2 = p.copyUnit();
		Affector move2 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		pp2.setAffectors(Arrays.asList(move2));
		Branch path2 = new Branch();
		path2.addPosition(startingPosition.copyPosition());
		path2.addPosition(new Position(startingPosition.getX() - 636,
				startingPosition.getY() + 636));
		move2.setTTL(60);
		pp2.getProperties().setVelocity(0.5, 90);
		pp2.getProperties().setMovement(new Movement(Arrays.asList(path2), startingPosition.copyPosition()));

		Unit pp3 = p.copyUnit();
		pp3.setTTL(30);
		Affector move3 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		pp3.setAffectors(Arrays.asList(move3));
		Branch path3 = new Branch();
		move3.setTTL(30);
		path3.addPosition(startingPosition.copyPosition());
		path3.addPosition(new Position(startingPosition.getX(), startingPosition.getY() + 900));
		pp3.getProperties().setVelocity(0.5, 0);
		pp3.getProperties().setMovement(new Movement(Arrays.asList(path3), startingPosition.copyPosition()));

		Unit pp4 = p.copyUnit();
		pp4.setTTL(60);
		Affector move4 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		pp4.setAffectors(Arrays.asList(move4));
		Branch path4 = new Branch();
		path4.addPosition(startingPosition.copyPosition());
		path4.addPosition(new Position(startingPosition.getX() + 900, startingPosition.getY()));
		pp4.getProperties().setVelocity(0.5, 270);
		move4.setTTL(30);
		pp4.getProperties().setMovement(new Movement(Arrays.asList(path4), startingPosition.copyPosition()));

		Unit pp5 = p.copyUnit();
		pp5.setTTL(30);
		Affector move5 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		move5.setTTL(60);
		pp5.setAffectors(Arrays.asList(move5));
		Branch path5 = new Branch();
		path5.addPosition(startingPosition.copyPosition());
		path5.addPosition(new Position(startingPosition.getX() + 636,
				startingPosition.getY() + 636));
		pp5.getProperties().setVelocity(0.5, 225);
		pp5.getProperties().setMovement(new Movement(Arrays.asList(path5), startingPosition.copyPosition()));

		Unit pp6 = p.copyUnit();
		pp6.setTTL(30);
		Affector move6 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		move6.setTTL(60);
		pp6.setAffectors(Arrays.asList(move6));
		Branch path6 = new Branch();
		path6.addPosition(startingPosition.copyPosition());
		path6.addPosition(new Position(startingPosition.getX() - 900, startingPosition.getY()));
		pp6.getProperties().setVelocity(0.5, 135);
		pp6.getProperties().setMovement(new Movement(Arrays.asList(path6), startingPosition.copyPosition()));

		Unit pp7 = p.copyUnit();
		pp7.setTTL(30);
		Affector move7 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		move7.setTTL(30);
		pp7.setAffectors(Arrays.asList(move7));
		Branch path7 = new Branch();
		path7.addPosition(startingPosition.copyPosition());
		path7.addPosition(new Position(startingPosition.getX(), startingPosition.getY() - 900));
		pp7.getProperties().setVelocity(0.5, 315);
		pp7.getProperties().setMovement(new Movement(Arrays.asList(path7), startingPosition.copyPosition()));

		Unit pp8 = p.copyUnit();
		Affector move8 = myAffectorLibrary.getAffector("RangeConstantPosition", "Move");
		move8.setTTL(30);
		pp8.setAffectors(Arrays.asList(move8));
		Branch path8 = new Branch();
		path8.addPosition(startingPosition.copyPosition());
		path8.addPosition(new Position(startingPosition.getX() - 450,
				startingPosition.getY() - 450));
		pp8.getProperties().setVelocity(0.5, 45);
		pp8.getProperties().setMovement(new Movement(Arrays.asList(path8), startingPosition.copyPosition()));

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

	public Unit createHomingTower (String name,
			List<Unit> myProjectiles2,
			List<Unit> myTowers,
			Position startingPosition,
			Store myStore) {
		List<Unit> myProjectiles = new ArrayList<>();
		Affector move = myAffectorLibrary.getAffector("Homing", "Move");
//		Affector move = myAffectorLibrary.getAffector("Cursor", "Direction");
		move.setTTL(Integer.MAX_VALUE);
		Unit p = new Unit("Projectile",
						Arrays.asList(move), 3);
		Affector increase = myAffectorLibrary.getAffector("Increase", "Range");
		myStore.addUpgrade(p, increase, 100);
		p.setDeathDelay(15);
		p.setTTL(1000000);
		Velocity velocity = new Velocity(2, 90);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(-20, -20));
		l1.add(new Position(20, -20));
		l1.add(new Position(20, 20));
		l1.add(new Position(-20, 20));
		Bounds b = new Bounds(l1);
		State st = new State(2);
		Branch p2 = new Branch();
		p2.addPosition(startingPosition.copyPosition());
		p2.addPosition(new Position(startingPosition.getX(), startingPosition.getY() - 900));
		List<Position> l2 = new ArrayList<>();
		l2.add(new Position(-100, -100));
		l2.add(new Position(-100, 100));
		l2.add(new Position(100, 100));
		l2.add(new Position(100, -100));
		Bounds range = new Bounds(l2);
		UnitProperties properties =
				new UnitProperties(new Health(1), null, velocity, b, range, null, st,
						new Movement(new Position(0, 0)), new Mass(1));
		Affector damage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		damage.setTTL(1);
		Affector stateToDamaging = myAffectorLibrary.getAffector("State", "Change");
		stateToDamaging.setTTL(1);
		p.setAffectorsToApply(Arrays.asList(new Affector[] { damage, stateToDamaging }));
		p.setProperties(properties);
		myProjectiles.add(p);
		return createSpecifiedTower(name, myProjectiles2, myTowers, myProjectiles);
	}

	public Unit createSpecifiedTower (String name,
			List<Unit> myProjectiles2,
			List<Unit> myTowers,
			List<Unit> myProjectiles) {
		List<Affector> affectors = new ArrayList<>();
		affectors.add(myAffectorLibrary.getAffector("Firing", "Children"));
		affectors.get(0).setTTL(Integer.MAX_VALUE);
		Unit t = new Unit(name, 2);
		t.setAffectors(affectors);
		t.setChildren(myProjectiles);
		myProjectiles.stream().forEach(p -> p.addParent(t));
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(-20, -20));
		l1.add(new Position(20, -20));
		l1.add(new Position(20, 20));
		l1.add(new Position(-20, 20));
		Bounds b = new Bounds(l1);
		Health health2 = new Health(50);
		Velocity velocity2 = new Velocity(0, 180);
		State st = new State(0);
		Movement p2 = new Movement(Arrays.asList(new Branch()), new Position(0, 0));
		UnitProperties properties2 =
				new UnitProperties(health2, null, velocity2, b, null, null, st,
						p2, new Mass(1));
		t.setProperties(properties2);
		t.setTTL(1000000);
		t.setDeathDelay(100);
		return t;
	}
}
