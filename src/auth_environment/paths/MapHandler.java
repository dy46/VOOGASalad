package auth_environment.paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.handlers.PositionHandler;
import game_engine.properties.Position;

public class MapHandler {

	private PathGraphFactory myPGF;
	private PositionHandler myPositionHandler;
	private List<Branch> myBranches;
	private List<Position> myGoals;
	private List<Position> mySpawns;

	public MapHandler(){
		myPGF = new PathGraphFactory();
		myPositionHandler = new PositionHandler();
		myBranches = new ArrayList<>();
		myGoals = new ArrayList<>();
		mySpawns = new ArrayList<>();
	}

	public MapHandler(List<Branch> engineBranches, List<Position> spawns, List<Position> goals){
		myBranches = engineBranches;
		this.mySpawns = spawns; 
		this.myGoals = goals; 
		myPGF = new PathGraphFactory(engineBranches);
		myPositionHandler = new PositionHandler();
		mySpawns = new ArrayList<>();
		myGoals = new ArrayList<>();
	}

	public void processPositions(List<Position> positions){
		List<Position> interpolatedPositions = myPositionHandler.getInterpolatedPositions(positions, false);
		myPGF.insertBranch(interpolatedPositions);
	}

	public void addSpawn(Position spawn){
		Position validSpawn = filterValidPos(spawn, 20);
		if(validSpawn == null)
			return;
		this.mySpawns.add(spawn);
	}

	public void addGoal(Position goal){
		Position validGoal = filterValidPos(goal, 20);
		if(validGoal == null)
			return;
		this.myGoals.add(goal);
		processPositions(Arrays.asList(goal));
	}

	public void setInitGoal(Position pos) {
		myGoals.add(pos);
	}

	public void createGrid(){
		myPGF.insertGrid();
	}

	private Position filterValidPos(Position pos, double radius){
		if(myPGF.getBranches() == null){
			return null;
		}
		boolean match = false;
		Position nearby = null;
		for(Branch b : this.myBranches){
			for(Position branchPos : b.getPositions()){
				if(pos.equals(branchPos)){
					match = true;
				}
				if(pos.distanceTo(branchPos) < radius){
					nearby = branchPos;
				}
			}
		}
		if(match){
			return pos;
		}
		return nearby;
	}

	public PathGraphFactory getPGF(){
		return myPGF;
	}

	public List<Branch> getBranches() {
		myBranches.addAll(myPGF.getBranches());
		return myBranches;
	}

	public List<Position> getGoals() {
		return myGoals;
	}

	public List<Position> getSpawns() {
		return mySpawns;
	}

}