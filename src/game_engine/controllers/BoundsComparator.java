package game_engine.controllers;

import java.util.Comparator;
import game_engine.game_elements.Unit;

public class BoundsComparator implements Comparator<Unit>{

	@Override
	public int compare(Unit o1, Unit o2) {
		return (int) (o1.getProperties().getBounds().getMaxBoundingDistance() - o2.getProperties().getBounds().getMaxBoundingDistance());
	}

}