1. Detecting Collisions and applying affectors from projectiles to enemies
  - EngineWorkspace: calls to `resolveEnemyCollisions(myProjectiles, myTerrains);` in the `CollisionDetector.java` class.
  - CollisionDetector: loops through each of the enemies and calls from the `CollisionDetector.java` class.
    - `updateEnemies(t, myProjectiles)`
    - `terrainHandling(t, myTerrains)`
  - CollisionDetector: Performs collision detection, and:
    - for each collided enemy calls:
      - `unit.addAffectors(myProjectiles.get(i).getAffectorsToApply());`
    - for each collided projectile calls:
      - `setHasCollided(true);`
      - `setElapsedTimeToDeath();`
  - During the next tick, the affector is applied ot the enemy because it is in the list of affectors that get applied.
  - During the next tick, the projectiles are removed from the workspace because they have already collided.

2. Player clicks on a position, and the EngineWorkspace adds a specific type of tower to that position
  - GameView: detects a click event, makes a call to `playerEngineInterface.addTower(e.getX(), e.getY(), 0);`
  - EngineWorkspace: `addTower(double x, double y, int towerTypeIndex)` references the tower types, and creates a new tower in the list of towers for the level.
  - During the next tick, GameView makes a call to `placeUnits(List<Unit> list, List<ImageViewPicker> imageViews)`, which reads all active units in the workspace, and plots them on screen, which includes the new tower.

3. Player pauses the game
  - GameView: calls `myScene.setOnKeyPressed(e -> setUpKeyPressed(e.getCode()));`
  - GameView: `setUpKeyPressed(KeyCode code)` checks to see if `code` is the space key, which then calls `toggleGame();`
  - GameView: `toggleGame()` stops the timer for the animation, which pauses the game.

4. A Path is created for enemy units to follow
  - Path constructor is called: `Path p = new Path(String name);`
  - Points are added to the path: `p.addPosition(Position append)`
  - Enemy sets path: `enemy.setPath(p);`

5. An enemy follows a path 
  - Enemy: makes a call to `update();` which calls `myAffectors.forEach(a -> a.apply(this));` thus applying a movement affector
  - Affector obtains the path for the Enemy by calling `Path myPath = u.getProperties().getPath();`
  - The next position of the enemy is set to `Position next = myPath.getNextPosition(curr);`, which moves it along the path
  - `myPath.getNextPosition(curr);` obtains the next pixel-level position that the unit would move to in a single game tick
  
6. A wave of enemies is created on the back-end (Game Engine).
  - EnemyFactory: multiple calls are made to `createSpecifiedEnemy(String name, String behavior, String property) `
  - Wave: a new Wave object is constructed using `Wave(String name, int time)`
  - Wave: enemies are added to the wave by using ` addEnemy(Enemy e, int spawnTime)` repeatedly
  
7. A level is composed from several different waves
  - Level: constructor is used to generate a level object `Level (String name, Wave first, int myLives)`
  - Level: waves are added by making repeated calls to `addWave (Wave newWave) `
  

### Game Player Use Cases

1. Integrate Save
  * OptionsMenu: call saveGame() when the event handler is triggered.
  * We will use XStream to save the current game state of the game engine as an XML file.
    * This will be done by calling EngineWorkspace.saveGame().

2. Integrate Load
  * OptionsMenu: call loadGame() when the event handler is triggered.
  * We will use XStream to parse the selected XML file.
  * Once the information is parsed, the information will be sent to the EngineWorkspace class and an instance of the selected game will be created.
  * The GameView.playGame() method will then be called and the game will begin.

3. Integrate Restart
  * OptionsMenu: call restartGame() when the event handler is triggered.
  * We need to add a method within in EngineWorkspace called restartGame() that restarts the currently loaded game.
  * With this new method, we can call EngineWorkspace.restartGame() in the OptionsMenu.restartGame() method.

4. Display High Score
  * HighScoreDisplay: update the display with the updateNode() method with the current high score.
  * We need to create the backend for the game player that stores the game information. 
  * This information would be stored within the GameDataSource class.

5. Heads Up Display
  * Create new IGUIObject HeadsUpDisplay.
    * HeadsUpDisplay will contain a Label that is constantly updated every time the EngineWorkspace is updated.
  * We can potentially work with other groups who are working on the Heads Up Display game utility.
  * The information required for the HeadsUpDisplay will be stored within GameDataSource.

6. Set Preferences For Different Games
  * Using Reflection, we can add different components to the Configuration Panel by specifying the required components in a property file.
  * The preferences that the player is able to change is set by the game designer.

7. Integrate Cheat Codes
  * The cheat codes will be set up by the user when designing the game, and will be added to the game enginer from the game data.
  * The game player will have a text field of some kind that allows the player to enter a string to activate the cheat codes.
  * Add Class to game player: CheatCodeTextField that implements IGUIObject.
    * Additionally, contains method checkCheatCodes() that compares inputted string with backend codes.

8. Integrate Social Center
  * We would work with other groups on the Social Center utility.
  * Once the Social Center utility is created, we will use the Social Center API to integrate it into the game player.
  * We would need to create a new IGUIObject class called SocialCenter that uses the Social Center API.
  * The social center could be used to rate games created by users, such as the system in Super Mario Maker.

9. Add CSS to the player
  * We already started on adding CSS to the player to make the game more appealing, but we have not spent much time on this case.
  * The individual UI components will need to add Styles from CSS files.

10. Drag and Drop for Towers 
  * We need to create a TowerPalette class that implements IGUIObject.
  * When the user clicks on a tower within the TowerPalette, an EventHandler is triggered and the user will be able to drag the tower onto the Pane root of the player.



