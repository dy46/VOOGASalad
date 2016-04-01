package game_engine.game_elements;

public interface Enemy {
	/*
	* updates the Enemy position, as well as applies any Affectors 
	* that it currently is being impacted by due to environmental conditions,
	* or because of attacks from towers.
	*
	*/
	public void update();
}
