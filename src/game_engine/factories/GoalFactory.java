package game_engine.factories;

import java.util.List;

import game_engine.libraries.GoalLibrary;
import game_engine.properties.Position;

public class GoalFactory {

	private GoalLibrary myGoalLibrary;
	
	public GoalFactory(){
		this.myGoalLibrary = new GoalLibrary();
	}
	
	public GoalFactory(GoalLibrary goalLibrary){
		this.myGoalLibrary = goalLibrary;
	}
	
	public void createGoal(Position goal){
		this.myGoalLibrary.addGoal(goal);
	}
	
	public void createGoals(List<Position> goals){
		this.myGoalLibrary.addGoals(goals);
	}
	
	public void setGoals(List<Position> goals){
		this.myGoalLibrary.setGoals(goals);
	}
	
	public void removeGoal(Position goal){
		this.myGoalLibrary.removeGoal(goal);
	}
	
	public GoalLibrary getGoalLibrary(){
		return myGoalLibrary;
	}
	
}