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
