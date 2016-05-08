package game_player.display_views;

//This class shows the how to display aggregated image views. For ranges, we don't want overlap or
//else the screen will get dark. Therefore, we aggregate the image views into one Shape to display.

import java.util.ArrayList;
import java.util.List;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
import game_player.UnitViews.UnitImageView;
import game_player.interfaces.IGameView;
import game_player.utilities.PolygonUtility;
import game_player.utilities.UnitTypeResourceBundleProcessor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;


public class RangeDisplayView implements DisplayViewProcessing {
    
    public static final String SEE_RANGE = "seeRange";
    public static final String RESOURCE_BUNDLE_LINK = "game_player/resources/UnitTypePreferences";
    private Shape unionRange;
    private UnitTypeResourceBundleProcessor myPreferencesBundle;

    public RangeDisplayView (IGameView gameView, Pane root) {
        this.unionRange = new Polygon();
        this.myPreferencesBundle = new UnitTypeResourceBundleProcessor();
        this.myPreferencesBundle.addPatterns(RESOURCE_BUNDLE_LINK);
    }

    @Override
    public void display (List<UnitImageView> unitViews, Pane root) {
        List<Polygon> ranges = new ArrayList<>();
        List<Polygon> rangesToSubtract = new ArrayList<>();
        for (int i = 0; i < unitViews.size(); i++) {
            Unit myUnit = unitViews.get(i).getUnit();
            boolean seeRange =
                    myPreferencesBundle.testUnitTypePreference(myUnit.toString(), SEE_RANGE);
            if (seeRange) {
                Position pos = myUnit.getProperties().getPosition();
                generateRangesFromBounds(ranges,
                                         myUnit.getChildren().get(0).getProperties().getRange(),
                                         pos);
                generateRangesFromBounds(rangesToSubtract, myUnit.getProperties().getBounds(), pos);
            }
        }
        updateUnionRange(ranges, rangesToSubtract, root);       
    }
    public void updateUnionRange (List<Polygon> ranges, List<Polygon> rangesToSubtract, Pane root) {
        root.getChildren().remove(unionRange);
        if (ranges.size() > 0) {
            unionRange = PolygonUtility.generateUnion(ranges);
            unionRange = PolygonUtility.subtractBounds(unionRange, rangesToSubtract);
            unionRange.setFill(Color.BLACK);
            unionRange.toFront();
            unionRange.setOpacity(0.1);
            root.getChildren().add(unionRange);
        }
    }

    public void generateRangesFromBounds (List<Polygon> ranges, Bounds bounds, Position position) {
        List<Position> range = bounds.getUseableBounds(position);
        ranges.add(PolygonUtility.fillPolygonWithPoints(new Polygon(), range));
    }


}
