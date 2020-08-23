package nl.benmens.evolution.entity;

import nl.benmens.world.World;
import processing.core.PVector;

public class EntityMover extends EntityDecorator {

  public EntityMover(EntityInterface parent) {
    super(parent);
  }
  
  @Override
  public void tick(int millisPerTick) {
    PVector pos = getState("pos", PVector.class);
    World world = getState("world", World.class);

    pos.x = (pos.x - 2 + world.width * 10) % (world.width * 10);
    pos.y = (pos.y - 1 + world.height * 10) % (world.height * 10);

    super.tick(millisPerTick);
  }
}