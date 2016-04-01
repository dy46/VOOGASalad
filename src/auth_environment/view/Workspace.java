package auth_environment.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class represents a single tab within our View.
 */

public interface Workspace {

    public void addPane(Pane pane, int x, int y);

    public Node getRoot();
}
