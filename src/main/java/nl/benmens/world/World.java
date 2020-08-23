package nl.benmens.world;

import nl.benmens.processing.mvc.Model;
import nl.benmens.world.terrain.Terrain;
import nl.benmens.world.terrain.grass.Grass;
import nl.benmens.world.terrain.water.Water;

public class World extends Model {
  public int width;
  public int height;
  public Terrain[][] terrain;

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
  }
}