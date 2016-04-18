package game_engine.game_elements;

import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.timelines.Timeline;


/*
 * Internal API used to represent projectiles fired by towers and/or enemies.
 * 
 */
public class Projectile extends Unit {

    private int fireRate;

    public Projectile (String name, List<Affector> affectors, int numFrames) {
        super(name, affectors, numFrames);
        // setID(getWorkspace().getIDFactory().createID(this));
    }

//    /*
//     * Updates the position of the projectile
//     */
//    public boolean update () {
//       return super.update();
//    }

    public Projectile copyProjectile () {
        List<Affector> copyAffectors = this.getAffectors().stream().map(a -> a.copyAffector()).collect(Collectors.toList());
        Projectile copy = new Projectile(this.toString(), copyAffectors, this.getNumFrames());
        copy.setTTL(this.getTTL());
        copy.setFireRate(this.getFireRate());
        List<Affector> copyApplyAffectors = this.getAffectorsToApply().stream().map(a -> a.copyAffector()).collect(Collectors.toList());
        copy.setAffectorsToApply(copyApplyAffectors);
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
    
    public void update() {
        super.update();
    }

    public int getFireRate () {
        return fireRate;
    }

    public void setFireRate (int fireRate) {
        this.fireRate = fireRate;
    }

}