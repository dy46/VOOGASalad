package game_engine.game_elements;

public interface Enemy {
	/*
	* updates the Enemy position, as well as applies any Affectors 
	* that it currently is being impacted by due to environmental conditions,
	* or because of attacks from towers.
	*
	*/
	public void update();
	
	/*
	* the Enemy fires a projectile at a target, which subsequently applies
	* an Affector on it. 
	*
	*/
	public void fire();
	
	/*
	 * updates the Enemy's health when an Affector is applied to it. 
	 */
	public void takeDamage(double damage);
}
