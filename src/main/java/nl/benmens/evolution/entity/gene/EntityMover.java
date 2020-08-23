package nl.benmens.evolution.entity.gene;

import nl.benmens.evolution.entity.EntityDecorator;
import nl.benmens.evolution.entity.EntityInterface;
import nl.benmens.evolution.world.World;
import processing.core.PVector;

public class EntityMover extends EntityDecorator {

  public EntityMover(EntityInterface parent) {
    super(parent);
  }
  
  @Override
  public void tick(int millisPerTick) {
    PVector destination = getState("destination", PVector.class);
    Float speed = getState("speed", Float.class);
    World world = getState("world", World.class);

    if (destination != null && speed != null) {
      PVector pos = getState("pos", PVector.class);

      pos.add(destination.copy().sub(pos).normalize().mult(speed));

      pos.x = (pos.x + world.width * 10) % (world.width * 10);
      pos.y = (pos.y + world.height * 10) % (world.height * 10);
  
      super.tick(millisPerTick);

      if (pos.copy().sub(destination).mag() < 10) {
        setState("destination", null);
        setState("speed", null);
      }

    } else {
      destination = new PVector((float)Math.random() * world.width * 10, (float)Math.random() *world.width * 10);
      speed = new Float(Math.random() * 2);
      setState("destination", destination);
      setState("speed", speed);
    }

  }
}