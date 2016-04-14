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
  
6. 


