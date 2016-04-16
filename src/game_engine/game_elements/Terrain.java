package game_engine.game_elements;

import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;

/*
* Internal API that will be used to reflect the inclusion of terrain effects in games. 
* Most implementations of the Terrain API will apply some sort of affector to game elements within
* a certain area on the map.
*/
public class Terrain extends Unit{
    
	public Terrain(String name, int numFrames) {
		super(name, numFrames);
	}
	
	@Override
	public void update() {
//		System.out.println(this.getProperties().getVelocity() == null);
//		System.out.println(" testing");
		super.update();
//		System.out.println(this);
	}
	
	public Terrain copyTerrain() {
	    Terrain copy = new Terrain(this.toString(), this.getNumFrames());
	    List<AffectorTimeline> copyApplyTimelines = this.getTimelinesToApply().stream().map(t -> t.copyTimeline()).collect(Collectors.toList());
	    copy.setTimelinesToApply(copyApplyTimelines);
	    copy.setTTL(this.getTTL());
	    copy.getProperties().setPosition((this.getProperties().getPosition().copyPosition()));
	    copy.getProperties().setBounds((this.getProperties().getBounds().copyBounds()));
	    copy.setDeathDelay(this.getDeathDelay());
	    copy.setNumberList(this.getNumberList());
	    return copy;
	}
	
}