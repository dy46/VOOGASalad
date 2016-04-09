package game_engine.game_elements;

import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;


/*
 * Internal API used to represent projectiles fired by towers and/or enemies.
 * 
 */
public class Projectile extends CollidableUnit {

    private int fireRate;

    public Projectile (String name, List<Affector> affectors) {
        super(name, affectors);
        // setID(getWorkspace().getIDFactory().createID(this));
    }

    /*
     * Updates the position of the projectile
     */
    public void update () {
       super.update();
    }

    public Projectile copyProjectile () {
        List<Affector> copyAffectors = this.getAffectors().stream().map(a -> a.copyAffector()).collect(Collectors.toList());
        Projectile copy = new Projectile(this.toString(), copyAffectors);
        copy.setTTL(this.getTTL());
        copy.setFireRate(this.getFireRate());
        copy.setAffectorsToApply(this.getAffectorsToApply());
        copy.setProperties(this.getProperties().copyUnitProperties());
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
