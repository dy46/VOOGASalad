package game_player.image_processing;

import game_engine.game_elements.Unit;
import game_player.utilties.ResourceBundleSymbolProcessor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public abstract class ImageViewProcessing {
    
    public static final String RESOURCE_BUNDLE_LINK = "game_player/resources/UnitTypePreferences";
    private ResourceBundleSymbolProcessor myPreferencesBundle;
    private ImageView mainImageView;
    private Pane root;
    
    public ImageViewProcessing(ImageView mainImageView, Pane root) {
        this.root = root;
        this.myPreferencesBundle = new ResourceBundleSymbolProcessor();
        this.myPreferencesBundle.addPatterns(RESOURCE_BUNDLE_LINK);
        this.mainImageView = mainImageView;
    }
    
    public abstract void processImageView(Unit myUnit);
    
    public abstract void removeFromRoot();
    
    public ResourceBundleSymbolProcessor getResourceBundle() {
        return myPreferencesBundle;
    }
    
    public ImageView getMainImageView() {
        return mainImageView;
    } 
    
    public Pane getRoot() {
        return root;
    }
    
}
