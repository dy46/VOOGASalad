package auth_environment.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.game_elements.GameElement;

public class MapDisplayModel {

	private Map<Coordinate, GameElement> myMap; 
	private List<Coordinate> myList;
	private int xMax;
	private int yMax;
	
	private ISelector mySelector; 
	
	public MapDisplayModel(int xLim, int yLim, ISelector selector) {
		xMax = xLim;
		yMax = yLim;
		this.mySelector = selector; 
	}
	
	public void generateMap(){
		// Xander's code
//		myList = new ArrayList<Coordinate>();
//		for(int i=0; i<xMax; i++){
//			for(int j=0; j<yMax; j++){
//				Coordinate point = new Coordinate(i,j);
//				myList.add(point);
//			}
//		}
		
		// Brian's code
		myMap = new HashMap<Coordinate, GameElement>(); 

	}
	
	// TODO: check for x, y in bounds 
	// TODO: consider making this a void method
	public boolean addElement(GameElement element, int x, int y) {
		if (inBounds(x,y)) {
			Coordinate point = new Coordinate(x,y); 
				myMap.put(point, element);
		}
		return inBounds(x,y); 
	}
	
	private boolean inBounds(int x, int y) {
		return (x <= this.xMax & x >= 0 & y <= this.yMax & y >= 0);
	}
	
	public int getXmax(){
		return xMax;
	}
	
	public int getYmax(){
		return yMax;
	}
	
//	public List getMap(){
//		return myList;
//	}
	
	public ISelector getSelector() {
		return this.mySelector;
	}

}
