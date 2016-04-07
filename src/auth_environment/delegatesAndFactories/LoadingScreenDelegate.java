package auth_environment.delegatesAndFactories;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

/**
 * Created by BrianLin on 4/6/16.
 *
 * Team member responsible: Brian
 *
 * What it does: Creates a loading (splash) screen featuring a loading bar.
 */
public class LoadingScreenDelegate {

    private static final String NAMES_PACKAGE = "auth_environment/properties/names";
    private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

    private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
    private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

    private Pane loadingPane;
    private ProgressBar loadProgress;
    private Label progressText;

    public LoadingScreenDelegate() {

    }


}
