package game_engine.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.timelines.Timeline;

public class TimelineLibrary {

	private HashMap<String, Timeline> myTimelines;
	private List<Timeline> allTimelines;
	
	public TimelineLibrary(){
		myTimelines = new HashMap<>();
		allTimelines = new ArrayList<>();
	}
	
	public Timeline getTimeline(String name){
		Timeline t = myTimelines.get(name).copyTimeline();
		allTimelines.add(t);
		return t;
	}
	
	public void addTimeline(String name, Timeline timeline){
		myTimelines.put(name, timeline);
	}
	
	public List<Timeline> getTimelines(){
		return allTimelines;
	}
	
}