package game_engine.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.properties.Position;
import game_engine.store_elements.Store;


public class UnitController {

    private List<Unit> myPlacedUnits;
    private List<PlaceValidation> myPlaceValidations;
    private Store myStore;
    private List<Unit> unitsToRemove;

    public UnitController (List<Unit> myPlacedUnits,
                           List<PlaceValidation> placeValidations,
                           Store store,
                           List<Unit> unitsToRemove) {
        this.myPlacedUnits = myPlacedUnits;
        this.myPlaceValidations = placeValidations;
        this.myStore = store;
        this.unitsToRemove = unitsToRemove;
    }

    public void clearProjectiles () {
        getUnitType("Projectile").forEach(t -> {
            t.setInvisible();
            t.setHasCollided(true);
        });
    }

    public List<Unit> getTowerTypes () {
        return myStore.getTowerList();
    }

    public boolean addTower (String name, double x, double y) {
        Unit purchased = myStore.purchaseUnit(name);
        if (purchased != null) {
            boolean canPlace = false;
            for (int i = 0; i < myPlaceValidations.size(); i++) {
                canPlace = myPlaceValidations.get(i).validate(purchased, x, y);
            }
            if (canPlace) {
                Unit copy = purchased.copyUnit();
                copy.getProperties().setPosition(x, y);
                myPlacedUnits.add(copy);
                return true;
            }
            else {
                myStore.sellUnit(purchased);
            }
        }
        return false;
    }

    public List<Affector> getUpgrades (Unit unitToUpgrade) {
        return myStore.getUpgrades(unitToUpgrade);
    }

    public void sellUnit (Unit u) {
        List<String> namesOfChildren = new ArrayList<>();
        u.getChildren().stream().forEach(c -> namesOfChildren.add(c.toString()));
        unitsToRemove
                .addAll(getPlacedUnits().stream()
                        .filter(c -> namesOfChildren.contains(c.toString()))
                        .collect(Collectors.toList()));
        u.setInvisible();
        u.update();
        unitsToRemove.add(u);
        myStore.sellUnit(u);
    }

    public void applyUpgrade (Unit unitToUpgrade, Affector affector) {
        myStore.buyUpgrade(unitToUpgrade, affector);
    }

    public void moveUnit (Unit unit, double x, double y) {
        unit.getProperties().setPosition(new Position(x, y));
    }

    public List<Unit> getUnitType (String type) {
        return myPlacedUnits.stream().filter(u -> u.toString().contains(type))
                .collect(Collectors.toList());
    }

    public void removeUnitType (String type, Unit unitToRemove) {
        List<Unit> units = getUnitType(type);
        if (units.contains(unitToRemove)) {
            units.remove(unitToRemove);
        }
    }

    public List<Unit> getPlacedUnits () {
        return myPlacedUnits;
    }

    public Store getStore () {
        return myStore;
    }
}