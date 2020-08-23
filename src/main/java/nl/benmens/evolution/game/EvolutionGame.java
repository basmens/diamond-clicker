package nl.benmens.evolution.game;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.benmens.evolution.entity.EntityInterface;
import nl.benmens.evolution.entity.Entity;
import nl.benmens.evolution.entity.EntityController;
import nl.benmens.evolution.entity.EntityMover;
import nl.benmens.processing.SharedPApplet;
import nl.benmens.processing.mvc.View;
import nl.benmens.world.World;
import nl.benmens.world.WorldController;
import processing.core.PApplet;
import processing.core.PVector;

public class EvolutionGame extends PApplet {
  static int MILLIS_PER_TICK = 10;

  protected static final Logger evolutionGameLogger = LogManager.getLogger();

  private Logger logger = evolutionGameLogger;
  private int nextTick = 0;

  View rootView;

  private World world = new World(80, 60);
  private WorldController worldController;
  private ArrayList<EntityInterface> entities = new ArrayList<EntityInterface>();

  public void settings() {
    SharedPApplet.sharedApplet = this;

    rootView = new View(null);
    worldController = new WorldController(null, rootView, world);

    size(world.width * 10, world.height * 10, P2D);

    EntityInterface e = new Entity();

    e.setState("pos", new PVector(0, 0));
    e.setState("world", world);
    e = new EntityMover(e);

    entities.add(e);
    new EntityController(worldController, worldController.worldView, e);

    logger.debug("settings finished");
  }

  public void setup() {
    logger.debug("setup finished");
  }

  public void draw() {
    if (millis() > nextTick) {
      nextTick += MILLIS_PER_TICK;
      for (EntityInterface e: entities) {
        e.tick(MILLIS_PER_TICK);
      }
    }

    clear();

    rootView.draw();
  }

  protected Logger getLogger() {
    return logger;
  }

  protected void setLogger(Logger logger) {
      this.logger = logger;
  }

  static public void main(String[] passedArgs) {
    if (passedArgs != null) {
      PApplet.main(EvolutionGame.class, passedArgs);
    } else {
      PApplet.main(EvolutionGame.class);
    }
  }
}
