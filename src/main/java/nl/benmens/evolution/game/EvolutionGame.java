package nl.benmens.evolution.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import nl.benmens.evolution.factories.ControlerFactory;
import nl.benmens.processing.SharedPApplet;
import nl.benmens.processing.mvc.View;
import nl.benmens.evolution.world.World;
import nl.benmens.processing.PApplet;

public class EvolutionGame extends PApplet {
  static int MILLIS_PER_TICK = 10;

  protected static final Logger evolutionGameLogger = LogManager.getLogger();

  private Logger logger = evolutionGameLogger;
  private int nextTick = 0;

  View rootView;

  private World world = new World(80, 60);

  public void settings() {
    SharedPApplet.setSharedApplet(this);

    ControlerFactory controlerFactory = new ControlerFactory();

    rootView = new View(null);
    controlerFactory.createController(null, rootView, world);

    size(world.width * 10, world.height * 10, P2D);

    logger.debug("settings finished");
  }

  public void setup() {
    logger.debug("setup finished");
  }

  public void draw() {
    if (millis() > nextTick) {
      nextTick += MILLIS_PER_TICK;
      world.tick(MILLIS_PER_TICK);
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
      PApplet.main(new Object() { }.getClass().getEnclosingClass(), passedArgs);
    } else {
      PApplet.main(new Object() { }.getClass().getEnclosingClass());
    }
  }
}
