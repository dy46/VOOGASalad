package auth_environment.Models.Interfaces;

import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.view.BoundLine;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public interface IPathTabModel extends IWorkspaceModel {
	
	public void refresh(IAuthEnvironment auth); 
	
	public void setPathWidth(double width);
	
	public double getPathWidth(); 
	
	public void submitBranch(); 
	
	public void printCurrentPositions(); 
	
	public List<Branch> getEngineBranches();
	
	public List<Branch> getVisualBranches();
	
	public void continueFromLastPosition(double x, double y);

	public void createGrid();

	public void addNewPosition(double x, double y);

	public void addNewSpawn(double x, double y);

	public void addNewGoal(double x, double y);

	public List<Position> getGoals();
	
	public List<Position> getSpawns();
	
	public List<String> getLevelNames();
	
	public List<String> getWaveNames(String selectedLevel); 
	
	public List<Unit> getWaveUnits(String selectedWave); 
	
	public Branch reselectBranch(BoundLine line); 
	
	public void saveBranch(BoundLine line, Branch branch); 
	
}