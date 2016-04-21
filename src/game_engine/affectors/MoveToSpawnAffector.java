//package game_engine.affectors;
//
//import java.util.List;
//
//import game_engine.functions.Function;
//import game_engine.game_elements.Unit;
//
//public class MoveToSpawnAffector extends Affector{
//
//	public MoveToSpawnAffector(AffectorData data){
//		super(data);
//	}
//	
//	public void apply(Unit u){
//		Function xValue = getWS().getFunctionFactory().createConstantFunction(u.getProperties().getMovement().getSpawn().getX());
//		Function yValue = getWS().getFunctionFactory().createConstantFunction(u.getProperties().getMovement().getSpawn().getY());
//		List<Function> functions = getData().getFunctions().get(0);
//		functions.get(0).setFunction(xValue);
//		functions.get(1).setFunction(yValue);
//		super.apply(u);
//	}
//	
//}