package game_player.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class TowerCell extends ListCell<String> {
	
	private HBox hbox;
	private Label label;
	private Pane pane;
	private ImageView image;
	
	public TowerCell() {
        super();
        label = new Label();
        pane = new Pane();
        image = new ImageView();
        hbox.getChildren().addAll(label, pane);
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            label.setText(item);
            setGraphic(hbox);
        }
    }
}
