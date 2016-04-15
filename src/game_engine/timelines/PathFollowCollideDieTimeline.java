package game_engine.timelines;

import java.util.List;

import game_engine.affectors.Affector;

public class PathFollowCollideDieTimeline extends Timeline{

	public PathFollowCollideDieTimeline(List<List<Affector>> affectors) {
		super(affectors);
		System.out.println("CREATED");
	}

}