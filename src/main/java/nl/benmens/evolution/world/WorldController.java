package nl.benmens.evolution.world;

import nl.benmens.processing.mvc.Controller;
import nl.benmens.processing.mvc.View;
import nl.benmens.evolution.entity.EntityInterface;
import nl.benmens.evolution.factories.ControlerFactory;
import nl.benmens.evolution.world.terrain.Terrain;

public class WorldController extends Controller {

  public WorldView worldView;
  public Controller terrainControllers[][];

  public WorldController(Controller parent, View parentView, World world) {
    super(parent);

    worldView = new WorldView(parentView, world);

    ControlerFactory controlerFactory = new ControlerFactory();
    
    terrainControllers = new Controller[world.width][world.height];
    for (Terrain[] terrainColomn: world.terrain) {
      for (Terrain terrain: terrainColomn) {
        if (terrain != null) {
          terrainControllers[terrain.x][terrain.y] = controlerFactory.createController(this, worldView, terrain);
        }
      }
    }

    for (EntityInterface entity: world.entities) {
      controlerFactory.createController(this, worldView, entity);
    }

  }
  
}