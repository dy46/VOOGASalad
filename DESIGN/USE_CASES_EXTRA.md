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
  
8. Engine detects when a wave has finished, and pauses in order to let the user make purchases
  - EngineWorkspace: makes consecutive calls to `updateElements()`
  - EngineWorkspace: `updateElements()` makes checks to `myCurrentLevel.getCurrentWave().isFinished()` which checks if wave has finished
  - If wave has finished, EngineWorkspace calls 
    - `clearProjectiles();`
    - `pause = true;`
  - During next call to `update()`, EngineWorkspace understands game is paused, and blocks calculations until next level or wave is played 
  
9. An enemy traverses its path, and decrements health
  - EngineWorkspace: makes a call to `updateElements()`
  - EngineWorkspace: `updateElements()` makes a call to `updateLives()`
  - EngineWorkspace: `updateLives()` checks `isUnitAtLastPosition(myEnemys.get(i))`
  - if enemy is at the last position in the path, `myLives` is decremented.

10. A Projectile homes in on an enemy
  - 

1. Auth Environment can drag in a Tower

  > Use the DragDelegate.java class we've created for Player, set possible Towers as sources, and locations on the Map as targets (using the DragDelegate's .setSource and .setTarget methods)

2. Auth Environment can load in a previous Game 

  > Open FileChooser to locate corresponding XML file. Update local IGameData instance by deserializing the XML. Tower Pickers and other Game Element containers will be updated by setting their contents from GameData. 

3. Auth Environment can edit placed Tower

  > Take target locations on Map and add mouse listener that would return the location's Unit instance. Then the Unit editor window is opened and has its fields populated with the selected Unit instance using setter methods.

4. Auth Environment can create multiple paths

  > Click a 'Place Path' button to begin selecting Path positions on the Map. Each subsequent click adds another Position to a growing List of Positions. Clicking a 'Save Path' will add this List of Positions to the IGameData instance. 

5. Auth Environment can view old (already placed) paths

  > The Map will be updated by calling the IGameData's method that returns a Collection of previous Paths. These Paths will then be displayed on the Map in an alternate color scheme (to be distinct from the currently placed Path).

6. Auth Environment can delete old Paths

  > A side panel of active Paths will be updated each time a Path is saved. Each item in that panel will have an event handler that will remove the given Path from the IGameData with a removePath method call.

7. Auth Environment can move placed Units

  > Map tiles can be both sources and targets (as setup with the DragDelegate using .setSource() and .setTarget() ). 

8. Auth Environment can change Map size

  > A Map customization Menu takes in doubles for .setMapWidth and .setMapHeight (either in pixels or grid count). This is then used to create the Map size. 


