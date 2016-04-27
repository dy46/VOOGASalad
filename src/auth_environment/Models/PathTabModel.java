package auth_environment.Models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.paths.MapHandler;
import auth_environment.view.BoundLine;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.properties.Position;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * This Tab is for placing Paths on the Map 
 */

public class PathTabModel implements IPathTabModel {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	// TODO: Add a PathLibrary to AuthData 
	private IAuthEnvironment myAuthData;  
	private MapHandler myMapHandler; 
	private List<Branch> myVisualBranches;
	private List<Position> myCurrentBranch;
	private List<Position> myGoals;
	private List<Position> mySpawns;
	
	// TODO: reselect a branch by clicking on the corresponding BoundLine (GUI element) 
	private Map<BoundLine, Branch> myBranchMap; 
	
	
	// ComboBox contents
	private List<Level> myLevels;
	private Level currentLevel;
	private Wave currentWave; 

	private double myPathWidth;

	public PathTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth; 
		this.myBranchMap = new HashMap<BoundLine, Branch>(); 
		this.myPathWidth = Double.parseDouble(this.myDimensionsBundle.getString("defaultPathWidth"));
		this.myCurrentBranch = new ArrayList<Position>(); 
		this.myVisualBranches = auth.getVisualBranches();
		this.myGoals = auth.getGoals();
		this.mySpawns = auth.getSpawns();
		this.myMapHandler = new MapHandler(auth.getEngineBranches(), auth.getGridBranches(), auth.getVisualBranches());
		this.myLevels = auth.getLevels(); 
	}

	@Override
	public void refresh(IAuthEnvironment auth) {
		System.out.println("refresh"); 
		this.myCurrentBranch.clear();
		this.myVisualBranches = auth.getVisualBranches();
		this.myGoals = auth.getGoals();
		this.mySpawns = auth.getSpawns();
		this.myMapHandler = new MapHandler(auth.getEngineBranches(), auth.getGridBranches(), auth.getVisualBranches());
		this.myLevels = auth.getLevels(); 
	}

	// TODO: should all Paths have the same width? Where to set this? 
	@Override
	public void setPathWidth(double width) {
		this.myPathWidth = width; 
	}

	@Override
	public double getPathWidth() {
		return this.myPathWidth;
	}

	@Override
	public void submitBranch() {
		System.out.println("My current branch: " + myCurrentBranch);
		this.myMapHandler.processPositions(myCurrentBranch);
		myCurrentBranch.clear();
		this.submit(); 
	}

	@Override
	public void printCurrentPositions() {
//		for(Branch b : myEngineBranches){
//			b.getPositions().stream().forEach(s -> System.out.println(s.getX() + " " + s.getY()));
//		}
	}

	private void submit() {
		this.myAuthData.setEngineBranches(this.myMapHandler.getEngineBranches());
		this.myAuthData.setVisualBranches(this.myVisualBranches);
		this.myAuthData.setGridBranches(this.myMapHandler.getGridBranches());
	}

	@Override
	public List<Branch> getEngineBranches() {
		return this.myAuthData.getEngineBranches();
	}

	@Override
	public List<Branch> getVisualBranches() {
		return this.myAuthData.getVisualBranches();
	}

	@Override
	public void addNewPosition(double x, double y) {
		myCurrentBranch.add(new Position(x, y));
	}

	@Override
	public void continueFromLastPosition(double x, double y) {
		myCurrentBranch.add(new Position(x, y));
	}

	@Override
	public void createGrid() {
		myMapHandler.createGrid();
	}

	@Override
	public void addNewSpawn(double x, double y) {
		System.out.println(this.mySpawns.getClass());
		this.mySpawns.add(new Position(x, y));
	}

	@Override
	public void addNewGoal(double x, double y) {
		this.myGoals.add(new Position(x, y));
	}

	@Override
	public List<Position> getGoals() {
		return myGoals;
	}

	@Override
	public List<Position> getSpawns() {
		return mySpawns;
	}
	
	public List<String> getLevelNames() {
		return this.myLevels.stream().map(level -> level.getName()).collect(Collectors.toList());
	}
	
	public List<String> getWaveNames(String selectedLevel) {
		this.currentLevel = this.myLevels.stream().filter(l -> l.toString().equals(selectedLevel)).collect(Collectors.toList()).get(0);
		return this.currentLevel.getWaves().stream().map(wave -> wave.getName()).collect(Collectors.toList());
	}

	@Override
	public List<Unit> getWaveUnits(String selectedWave) {
		this.currentWave = this.currentLevel.getWaves().stream().filter(w -> w.toString().equals(selectedWave)).collect(Collectors.toList()).get(0);
		return this.currentWave.getSpawningUnitsLeft();
	}

	@Override
	public Branch reselectBranch(BoundLine line) {
		return this.myBranchMap.get(line); 
	}

	@Override
	public void saveBranch(BoundLine line, Branch branch) {
		this.myBranchMap.put(line, branch); 
	}

}