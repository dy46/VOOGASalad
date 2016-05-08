package game_player.display_views;

//This class conducts display view processing, which means it allows for front-end elements that 
//aggregate the results of a lot of units i.e. ranges. Like the image view processing, separating it into
//multiple classes makes it more modular/extensible so more display view processes can be added.

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.game_elements.Unit;
import game_player.UnitViews.ImageViewPicker;
import game_player.interfaces.IGameView;
import javafx.scene.layout.Pane;

public class UnitDisplayView extends DisplayView {
        
    private List<DisplayViewProcessing> displayViewProcesses;
    
    public UnitDisplayView (IGameView gameView, Pane root) {
        super(gameView, root);
        initDisplayViewProcesses(gameView, root);
    }
    
    public void initDisplayViewProcesses (IGameView gameView, Pane root) {
        displayViewProcesses = new ArrayList<>();
        displayViewProcesses.add(new RangeDisplayView(gameView, root));
    }

    public void display(List<Unit> unitList, int timer) {
        List<Unit> list =
                unitList.stream().filter(u -> u.isVisible()).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            if (!hasImageView(list.get(i), getImageViews())) {
                ImageViewPicker picker = new ImageViewPicker(list.get(i), getRoot());
                picker.getImageView().setOnMouseClicked(e -> getGameView().updateHUD(picker));
                getImageViews().add(picker);
            }
        }
        for (int i = 0; i < getImageViews().size(); i++) {
            ImageViewPicker picker = (ImageViewPicker) getImageViews().get(i);
            if (getImageViews().get(i).getUnit().isVisible()) {
                picker.selectNextImageView(timer);
            }
            else {
                picker.removeFromRoot();
                getImageViews().remove(i);
            }
        }
        displayViewProcesses.stream().forEach(i -> i.display(getImageViews(), getRoot()));
    }
}
