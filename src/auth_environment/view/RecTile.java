package auth_environment.view;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Team member responsible: Xander
 * Modifications from: Brian
 *
 * This represents the smallest unit of the Map Display. This tile consists of several layers: empty (blank), Terrain
 * and GameElement (stacked with GameElements on top). 
 * 
 * The key action is that on mouse click. 
 */

public class RecTile extends Rectangle {

	public RecTile() {
	}

	public RecTile(double arg0, double arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public RecTile(double arg0, double arg1, Paint arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public RecTile(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
