package game_player.view;

import exceptions.WompException;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Unit;
import game_player.interfaces.IGameView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class GameViewEventHandler {

    private GameEngineInterface playerEngineInterface;
    private IGameView gameView;
    private Pane root;
    private int DEFAULT_MOVE_SPEED = 1;
    private int moveSpeed;
    private Unit specificUnitIsSelected;
    private String unitToPlace;
    private boolean canPlaceUnit;

    public GameViewEventHandler (GameEngineInterface gameEngine, IGameView gameView, Pane root) {
        this.playerEngineInterface = gameEngine;
        this.gameView = gameView;
        this.moveSpeed = DEFAULT_MOVE_SPEED;
        this.root = root;
    }

    public void setEventHandlers (Scene myScene) {
        myScene.setOnKeyPressed(e -> setUpKeyPressed(e.getCode()));
        this.root.setOnMouseClicked(e -> setUpMouseClicked(e));
        this.root.setOnMouseMoved(e -> setUpMouseMoved(e));
    }

    public void setUpMouseClicked (MouseEvent e){
        if (unitToPlace != null) {
            try {
				playerEngineInterface.getUnitController().addTower(unitToPlace, e.getX(), e.getY());
			} catch (WompException e1) {
				e1.displayMessage();
			}
        }
        else if (canPlaceUnit) {
            gameView.hideHUD();
        }
        canPlaceUnit = true;
    }

    public void setUpMouseMoved (MouseEvent e) {
        playerEngineInterface.setCursorPosition(e.getX(), e.getY());
    }

    public void setUpKeyPressed (KeyCode code) {
        switch (code) {
            case SPACE: gameView.toggleGame(); break;
            case ESCAPE: unitToPlace = null; break;
            case W: moveSelectedUnit(0, -moveSpeed); break;
            case A: moveSelectedUnit(-moveSpeed, 0); break;
            case D: moveSelectedUnit(moveSpeed, 0); break;
            case S: moveSelectedUnit(0, moveSpeed); break;
            default: break;
        }
    }

    public void moveSelectedUnit (double offsetX, double offsetY) {
        if (specificUnitIsSelected != null) {
            playerEngineInterface.getUnitController()
                    .moveUnit(specificUnitIsSelected,
                              specificUnitIsSelected.getProperties().getPosition().getX() +
                                                      offsetX,
                              specificUnitIsSelected.getProperties().getPosition().getY() +
                                                                     offsetY);
        }
    }

    public void setSpecificUnitIsSelected (Unit unit) {
        this.specificUnitIsSelected = unit;
    }

    public void setCanPlaceUnit (boolean canPlaceUnit) {
        this.canPlaceUnit = canPlaceUnit;
    }

    public void setUnitToPlace (String name) {
        this.unitToPlace = name;
    }
}
