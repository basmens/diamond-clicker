package nl.benmens.world.terrain;

import nl.benmens.processing.mvc.Model;

public class Terrain extends Model {

  public int x;
  public int y;
  
  public Terrain(int x, int y) {
    this.x = x;
    this.y = y;
  }
}