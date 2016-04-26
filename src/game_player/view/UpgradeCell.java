package game_player.view;

import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class UpgradeCell extends ListCell<Affector> {

    public UpgradeCell () {
        super();
    }

    @Override
    protected void updateItem (Affector item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
        } else {
            setText(item.getClass().getSimpleName());
        }
    }
}
