package nl.benmens.world;

import nl.benmens.processing.mvc.Controller;
import nl.benmens.processing.mvc.View;
import nl.benmens.world.terrain.Terrain;
import nl.benmens.world.terrain.grass.Grass;
import nl.benmens.world.terrain.grass.GrassController;
import nl.benmens.world.terrain.water.Water;
import nl.benmens.world.terrain.water.WaterController;

public class WorldController extends Controller {

  public WorldView worldView;
  public Controller terrainControllers[][];

  public WorldController(Controller parent, View parentView, World world) {
    super(parent);

    worldView = new WorldView(parentView, world);

    terrainControllers = new Controller[world.width][world.height];
    for (Terrain[] terrainColomn: world.terrain) {
      for (Terrain terrain: terrainColomn) {
        if (terrain != null) {
          if (terrain instanceof Grass) {
            Grass grass = (Grass)terrain;

            GrassController grassController = new GrassController(this, worldView, grass);
            terrainControllers[grass.x][grass.y] = grassController;
          } else if (terrain instanceof Water) {
            Water water = (Water)terrain;

            WaterController waterController = new WaterController(this, worldView, water);
            terrainControllers[water.x][water.y] = waterController;
          }
        }
      }
    }
  }
  
}