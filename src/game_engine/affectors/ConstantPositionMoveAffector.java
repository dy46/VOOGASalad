package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;

public class ConstantPositionMoveAffector extends PositionMoveAffector {

//	public PositionMoveAffector (List<Double> baseNumbers, int timeToLive) {
//		super(baseNumbers, timeToLive);
//	}

	public ConstantPositionMoveAffector(List<Function> functions, int TTL){
		super(functions, TTL);
	}

}