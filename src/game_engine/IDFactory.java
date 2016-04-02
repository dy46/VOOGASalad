package game_engine;
import java.lang.reflect.Field;

import game_engine.game_elements.GameElement;

public class IDFactory {

	private Integer lastLevelID;
	private Integer lastTowerID;
	private Integer lastWaveID;
	private Integer lastPathID;
	private Integer lastEnemyID;
	private Integer lastTimerID;
	
	private int DEFAULT_VALUE = -1;
	
	public IDFactory(){
		lastLevelID = new Integer(DEFAULT_VALUE);
		lastTowerID = new Integer(DEFAULT_VALUE);
		lastWaveID = new Integer(DEFAULT_VALUE);
		lastPathID = new Integer(DEFAULT_VALUE);
		lastEnemyID = new Integer(DEFAULT_VALUE);
		lastTimerID = new Integer(DEFAULT_VALUE);
	}
	
	public String createID(GameElement element){
		String className = element.getClass().getName();
		String instanceVarName = "last" + className + "ID";
		Field f = null;
		try {
			f = getClass().getDeclaredField(instanceVarName);
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO: womp exception
			e1.printStackTrace();
		}
		f.setAccessible(true);
		Integer instanceVar = null;
		try {
			instanceVar = (Integer) f.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception
			e.printStackTrace();
		}
		int nextIDNum = instanceVar++;
		instanceVar = new Integer(nextIDNum);
		return className+" "+instanceVar;
	}
	
	public int getLastLevelID(){
		return lastLevelID;
	}
	
	public int getLastTowerID(){
		return lastTowerID;
	}
	
	public int getLastPathID(){
		return lastPathID;
	}
	
	public int getLastEnemyID(){
		return lastEnemyID;
	}
	
	public int getLastTimerID(){
		return lastTimerID;
	}
	
	public int getLastWaveID(){
		return lastWaveID;
	}
	
}