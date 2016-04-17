package auth_environment.backend;

import java.util.HashMap;
import java.util.Map;

import auth_environment.view.Tile;
import game_engine.game_elements.GameElement;
import game_engine.properties.Position;

public class MapDisplayModel {

	public Map<Position, Tile> myMap; 
	private int xMax;
	private int yMax;
	
	public MapDisplayModel(int xLim, int yLim) {
		xMax = xLim;
		yMax = yLim;
		generateMap();
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
		myMap = new HashMap<Position, Tile>(); 

	}
	
	// TODO: check for x, y in bounds 
	// TODO: consider making this a void method
	public void addElement(Tile element, int x, int y) {
		if (inBounds(x,y)) {
			Position point = new Position(x,y); 
				myMap.put(point, element);
		}
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

}
