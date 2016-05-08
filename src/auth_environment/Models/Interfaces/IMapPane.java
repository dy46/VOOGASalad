//This entire file is part of my masterpiece.
//Alexander Tseng
/*It was a challenging decision when deciding how to oragnize the map panes because it was a choice
between using interfaces and class heirarchy (extends subclass). While interfaces provide more flexbility in terms of the individual 
methods within each class, it might cause a problem of duplicate code among the classes if the classes share very similar code among 
their methods. In this case, I chose to implement interfaces because the code within the methods they share are generally different. 
Furthermore, for the gridmappane, I extended it from gridpane so that it would be able to use the methods from gridpane.
*/

package auth_environment.Models.Interfaces;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import javafx.scene.layout.Pane;

public interface IMapPane {
	
	public void adjustUnitViewScale(UnitView uv);
	public void adjustUnitViewXY(UnitView uv, double x, double y);
	
	public MapEditorTabModel getModel();
	
	
	public void addToPane(UnitView uv);
	
	public Pane getRoot();
}
