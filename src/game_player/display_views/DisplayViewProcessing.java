package game_player.display_views;

import java.util.List;
import game_player.UnitViews.UnitImageView;
import javafx.scene.layout.Pane;

public interface DisplayViewProcessing {
    
    public void display(List<UnitImageView> unitViews, Pane root);
    
}
