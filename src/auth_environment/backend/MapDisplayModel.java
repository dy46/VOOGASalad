package auth_environment.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_engine.game_elements.GameElement;

public class MapDisplayModel {

	Map<Coordinate, GameElement> myMap; 
	List<Coordinate> myList;
	private int xMax;
	private int yMax;
	
	public MapDisplayModel(int xLim, int yLim) {
		xMax = xLim;
		yMax = yLim;
	}
	
	public void generateMap(){
		// Xander's code
		myList = new ArrayList<Coordinate>();
		for(int i=0; i<xMax; i++){
			for(int j=0; j<yMax; j++){
				Coordinate point = new Coordinate(i,j);
				myList.add(point);
			}
		}
		
		// Brian's code
		myMap = new HashMap<Coordinate, GameElement>(); 
	}
	
	public int getXmax(){
		return xMax;
	}
	
	public int getYmax(){
		return yMax;
	}
	
	public List getMap(){
		return myList;
	}

}
