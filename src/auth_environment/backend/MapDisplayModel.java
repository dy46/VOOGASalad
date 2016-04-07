package auth_environment.backend;

import java.util.ArrayList;
import java.util.List;

public class MapDisplayModel {

	List<Coordinate> myMap;
	private int xMax;
	private int yMax;
	
	public MapDisplayModel(int xLim, int yLim) {
		xMax = xLim;
		yMax = yLim;
	}
	
	public void generateMap(){
		myMap = new ArrayList<Coordinate>();
		for(int i=0; i<xMax; i++){
			for(int j=0; j<yMax; j++){
				Coordinate point = new Coordinate(i,j);
				myMap.add(point);
			}
		}
	}
	
	public int getXmax(){
		return xMax;
	}
	
	public int getYmax(){
		return yMax;
	}
	
	public List getMap(){
		return myMap;
	}

}
