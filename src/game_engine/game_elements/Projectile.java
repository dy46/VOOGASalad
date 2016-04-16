package game_engine.game_elements;

import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.timelines.Timeline;


/*
 * Internal API used to represent projectiles fired by towers and/or enemies.
 * 
 */
public class Projectile extends Unit {

    private int fireRate;

    public Projectile (String name, List<Timeline> timelines, int numFrames) {
        super(name, timelines, numFrames);
        // setID(getWorkspace().getIDFactory().createID(this));
    }

//    /*
//     * Updates the position of the projectile
//     */
//    public boolean update () {
//       return super.update();
//    }

    public Projectile copyProjectile () {
        List<Timeline> copyTimelines = this.getTimelines().stream().map(t -> t.copyTimeline()).collect(Collectors.toList());
        Projectile copy = new Projectile(this.toString(), copyTimelines, this.getNumFrames());
        copy.setTTL(this.getTTL());
        copy.setFireRate(this.getFireRate());
        List<Timeline> copyApplyTimelines = this.getTimelinesToApply().stream().map(t -> t.copyTimeline()).collect(Collectors.toList());
        copy.setTimelinesToApply(copyApplyTimelines);
        copy.setProperties(this.getProperties().copyUnitProperties());
        copy.setDeathDelay(this.getDeathDelay());
        copy.setNumberList(this.getNumberList());
        return copy;
    }

    /*
     * Applies any affectors that the projectile may have, which can include burns, freezes, and so
     * forth
     *
     * @param elem specifies the GameElement that the effects are being applied to
     * 
     */
    public void applyEffects (GameElement elem) {

    }

    public int getFireRate () {
        return fireRate;
    }

    public void setFireRate (int fireRate) {
        this.fireRate = fireRate;
    }
    
}