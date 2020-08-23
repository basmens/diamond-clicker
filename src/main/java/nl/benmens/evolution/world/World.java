package nl.benmens.evolution.world;

import nl.benmens.processing.mvc.Model;
import processing.core.PVector;
import nl.benmens.evolution.entity.Entity;
import nl.benmens.evolution.entity.EntityInterface;
import nl.benmens.evolution.entity.gene.EntityMover;
import nl.benmens.evolution.world.terrain.Terrain;
import nl.benmens.evolution.world.terrain.grass.Grass;
import nl.benmens.evolution.world.terrain.water.Water;
import java.util.ArrayList;

public class World extends Model {
  public int width;
  public int height;
  public Terrain[][] terrain;
  public ArrayList<EntityInterface> entities = new ArrayList<EntityInterface>();


  public World(int width, int height) {
    this.width = width;
    this.height = height;

    terrain = new Terrain[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (Math.random() > 0.5) {
          terrain[x][y] = new Grass(x, y);
        } else {
          terrain[x][y] = new Water(x, y);
        }
      }  
    }

    for (int i = 0 ; i < 100; i++) {
      EntityInterface entity = new Entity();

      entity.setState("pos", new PVector(0, 0));
      entity.setState("world", this);
      entity = new EntityMover(entity);
  
      entities.add(entity);  
    }
  }

  public void tick(int millisPerTick) {
    for (EntityInterface e: entities) {
      e.tick(millisPerTick);
    }
  }
  
}