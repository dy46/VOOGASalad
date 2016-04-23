package game_engine.factories;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.TimelineLibrary;
import game_engine.timelines.EndEvent;
import game_engine.timelines.Timeline;

public class TimelineFactory {

	private static final String PACKAGE = "game_engine.timelines.";
	private static final String BASE = "Timeline";
	private AffectorLibrary myAffectorLibrary;
	private TimelineLibrary myTimelineLibrary;
	
	public TimelineFactory(AffectorLibrary affectorLibrary){
		myAffectorLibrary = affectorLibrary;
		myTimelineLibrary = new TimelineLibrary();
		setDefaultTimelines();
	}
	
	private void constructTimeline(String name, List<List<Affector>> affectors){
		Timeline timeline = null;
		try {
			timeline = (Timeline) Class.forName(PACKAGE + name + BASE)
					.getConstructor(List.class)
					.newInstance(affectors);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		myTimelineLibrary.addTimeline(name, timeline);
	}
	
	private void setDefaultTimelines(){
//		String name1 = "PathFollowCollideDie";
//		Affector forward = myAffectorLibrary.getAffector("PathFollow", "PositionMove");
//		Affector die = myAffectorLibrary.getAffector("Death", "Activation");
//		die.setTTL(1);
//		List<List<Affector>> affectors = Arrays.asList(Arrays.asList(forward), Arrays.asList(die));
//		constructTimeline(name1, affectors);
	}

	public TimelineLibrary getTimelineLibrary() {
		return myTimelineLibrary;
	}
	
}