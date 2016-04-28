package game_player.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_player.UnitViews.UnitImageView;
import game_player.display_views.RangeDisplayView;
import game_player.interfaces.IGameView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GameView implements IGameView {

    public static final int DEFAULT_UPDATE_SPEED = 1;
    private Scene myScene;
    private Pane root;
    private GameViewEventHandler eventHandler;
    private GameEngineInterface playerEngineInterface;
    private RangeDisplayView rangeDisplayView;
    private List<ImageView> paths;
    private PlayerMainTab myTab;
    private GameHUD myHUD;
    private int timer;
    private double myUpdateSpeed;
    private double currentSpeed;
    private AnimationTimer AT;
    private boolean timerStatus;
    private boolean isPlaying;

    public GameView (GameEngineInterface engine,
                     GameCanvas canvas,
                     GameHUD hud,
                     Scene scene,
                     PlayerMainTab tab) {
    	this.myScene = scene;
        this.root = canvas.getRoot();
        this.playerEngineInterface = engine;
        this.rangeDisplayView = new RangeDisplayView(this, root);
        this.paths = new ArrayList<>();
        this.myTab = tab;
        this.myHUD = hud;
        setUpEventHandlers(scene);
        setUpSpeed();
        setUpHUD();
    }

    private void setUpEventHandlers (Scene scene) {
        this.eventHandler = new GameViewEventHandler(playerEngineInterface, this, root);
        this.eventHandler.setEventHandlers(scene);
    }

    private void setUpSpeed () {
        this.timer = 0;
        this.isPlaying = true;
        this.myUpdateSpeed = DEFAULT_UPDATE_SPEED;
        this.currentSpeed = DEFAULT_UPDATE_SPEED;
    }

    private void setUpHUD () {
        myHUD.setGameView(this);
        myHUD.setEngine(playerEngineInterface);
    }

    public void playGame (int gameIndex) {
        AT = new AnimationTimer() {
            public void handle (long currentNanoTime) {
                if (isPlaying) {
                    timer++;
                    updateEngine();
                    placePath();
//                    System.out.println("BEFORE RANGE DISPLAY");
//                    System.out.println(playerEngineInterface.getUnitController().getPlacedUnits().stream().filter(u -> u.isVisible()).collect(Collectors.toList()));
                    rangeDisplayView.display(playerEngineInterface.getUnitController().getPlacedUnits(), timer);
                }
            }
        };
        AT.start();
        timerStatus = true;
    }

    private void updateEngine () {
        for (int i = 1; i <= myUpdateSpeed; i++) {
            playerEngineInterface.update();
        }
        myTab.updateGameElements();
    }

    // TODO: Refactor when Adam is done with branches
    public void placePath () {
        List<Branch> currBranches = new ArrayList<>();
        currBranches.addAll(playerEngineInterface.getBranches());
        List<Position> allPositions = new ArrayList<>();
        currBranches.stream().forEach(cb -> allPositions.addAll(cb.getPositions()));
        for (int i = paths.size(); i < allPositions.size(); i++) {
            Image img = new Image("DirtNew.png");
            ImageView imgView = new ImageView(img);
            imgView.setX(allPositions.get(i).getX() - imgView.getImage().getWidth() / 2);
            imgView.setY(allPositions.get(i).getY() - imgView.getImage().getHeight() / 2);
            root.getChildren().add(imgView);
            imgView.toFront();
            paths.add(imgView);
        }
    }

    public void toggleGame () {
        if (timerStatus) {
            myUpdateSpeed = 0;
            timerStatus = false;
        }
        else {
            myUpdateSpeed = currentSpeed;
            timerStatus = true;
        }
    }

    public void changeGameSpeed (double gameSpeed) {
        this.myUpdateSpeed = gameSpeed;
        this.currentSpeed = gameSpeed;
    }

    public GameEngineInterface getGameEngine () {
        return playerEngineInterface;
    }

    public void setSpecificUnitIsSelected (Unit unit) {
        eventHandler.setSpecificUnitIsSelected(unit);
    }

    public void setUnitToPlace (String name) {
        eventHandler.setUnitToPlace(name);
    }

    public void setCanPlaceUnit (boolean canPlaceUnit) {
        eventHandler.setCanPlaceUnit(canPlaceUnit);
    }

    public void updateHUD (UnitImageView view) {
        myHUD.whenTowerSelected(view.getUnit());
        setCanPlaceUnit(false);
    }

    public void hideHUD () {
        myHUD.whenNothingSelected();
    }
    
    public Scene getScene() {
    	return this.myScene;
    }

    public void changeColorScheme (int colorIndex) {
        // TODO Auto-generated method stub
    }

    public void restartGame () {
        // TODO Auto-generated method stub
    }
}
