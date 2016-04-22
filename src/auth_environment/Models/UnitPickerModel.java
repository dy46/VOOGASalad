package auth_environment.Models;

import java.util.List;

import auth_environment.Models.Interfaces.IUnitPickerModel;
import game_engine.game_elements.Unit;

public class UnitPickerModel implements IUnitPickerModel {
	
	private List<UnitView> myUnitViews;

	@Override
	public List<UnitView> setUnits(List<Unit> units) { 
		// TODO: check that s.getImgName() works
		units.stream().forEach(s -> myUnitViews.add(new UnitView(s, s.getImgName())));
		return myUnitViews;
	}

	@Override
	public UnitView getUnitViewWithHash(int hash) {
		for (UnitView uv : this.myUnitViews) {
			if (uv.hashCode() == hash) {
				return uv;
			}
		}
		return null; // TODO: throw some error
	}

}
