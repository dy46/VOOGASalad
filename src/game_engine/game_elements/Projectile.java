package game_engine.game_elements;
/*
* Internal API used to represent projectiles fired by towers and/or enemies. 
* 
*/
public class Projectile extends MapPiece{
	
	public Projectile(String ID) {
		super(ID);
	}

	/*
	* Updates the position of the projectile
	*/
	public void update(){
		
	}

	/*
	* Applies any affectors that the projectile may have, which can include burns, freezes, and so forth
	*
	* @param  elem  specifies the GameElement that the effects are being applied to
	* 
 	*/
	public void applyEffects(GameElement elem){
		
	}

	public String toString() {
		return getID();
	}
	
}