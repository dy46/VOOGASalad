package game_engine.affectors;

import java.lang.reflect.Method;
import java.util.List;

import game_engine.GameEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Property;


public abstract class Affector {

    private int TTL;
    private int elapsedTime;
    private GameEngineInterface engineWorkspace;
    private AffectorData myData;
    private List<Unit> unitList;
    private String name;

    public Affector (AffectorData data) {
        this.myData = data;
        this.elapsedTime = 0;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }

    public Affector copyAffector () {
        // TODO: may need to copy functions too
        Affector copy = null;
        try {
            copy = (Affector) Class.forName(this.getClass().getName())
                    .getConstructor(AffectorData.class)
                    .newInstance(myData);
            copy.setWorkspace(this.getWS());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        copy.setTTL(this.getTTL());
        return copy;
    }

    public void apply (Unit u) {
        for (int i = 0; i < myData.getUnitProperties().size(); i++) {
            apply(myData.getFunctions().get(i),
                  getProperty(u, myData.getUnitProperties().get(i)), u);
        }
        updateElapsedTime();
    }

    public abstract void apply (List<Function> functions, Property property, Unit u);

    private Property getProperty (Unit u, String property) {
        Property p = null;
        if (property == null) {
            return p;
        }
        try {
            Method method = Class.forName("game_engine.properties.UnitProperties")
                    .getDeclaredMethod("get" + property);
            p = ((Property) method.invoke(u.getProperties()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public int getElapsedTime () {
        return elapsedTime;
    }

    public void updateElapsedTime () {
        elapsedTime++;
    }

    public void setElapsedTime (int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getTTL () {
        return TTL;
    }

    public void setElapsedTimeToDeath () {
        this.setElapsedTime(this.getTTL());
    }

    public void setTTL (int TTL) {
        this.TTL = TTL;
    }

    public GameEngineInterface getWS () {
        return engineWorkspace;
    }

    public void setWorkspace (GameEngineInterface workspace) {
        this.engineWorkspace = workspace;
    }

    public List<Unit> getUnitList () {
        return unitList;
    }

    public void setUnitList (List<Unit> unitList) {
        this.unitList = unitList;
    }
}
