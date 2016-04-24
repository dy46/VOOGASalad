package auth_environment.view.tabs;

import java.util.List;

import game_engine.game_elements.Unit;

public class AnimationLoaderTab {
	
	private Unit myUnit; 
	private String myType; 
	
	public AnimationLoaderTab(Unit unit) {
		this.myUnit = unit;
		this.init();
	}
	
	// TODO: make sure va has already configured Type in her Unit creation Tab
	private void init() {
		this.myType = this.myUnit.getType();
	}
	
	

}
