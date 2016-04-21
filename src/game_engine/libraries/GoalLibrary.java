package game_engine.libraries;

import java.util.ArrayList;
import java.util.List;

import game_engine.properties.Position;

public class GoalLibrary {

	private List<Position> myGoals;

	public GoalLibrary(){
		this.myGoals = new ArrayList<>();
	}
	
	public GoalLibrary(List<Position> goals){
		myGoals = goals;
	}

	public void addGoal(Position goal){
		this.myGoals.add(goal);
	}

	public void addGoals(List<Position> goals){
		this.myGoals.addAll(goals);
	}

	public void setGoals(List<Position> goals){
		this.myGoals = goals;
	}

	public void removeGoal(Position goal) {
		this.myGoals.remove(goal);
	}
	
	public List<Position> getGoals(){
		return this.myGoals;
	}

}