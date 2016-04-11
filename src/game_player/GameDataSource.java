package game_player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDataSource implements IGameDataSource{
	
	private Map<String, Double> doubleValues;
	private Map<String, List<String>> stringValues;
	
	public GameDataSource() {
		doubleValues = new HashMap<>();
		stringValues = new HashMap<>();
	}
	
    @Override
    public void setDoubleValue (String key, double value) {
        doubleValues.put(key, value);
    }

    @Override
    public double getDoubleValue (String key) {
        return doubleValues.get(key);
    }

    @Override
    public void setStringValue (String key, List<String> values) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<String> getStringValue (String key) {
        // TODO Auto-generated method stub
        return null;
    }

}
