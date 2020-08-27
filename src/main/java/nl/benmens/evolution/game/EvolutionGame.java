package nl.benmens.evolution.game;

import nl.benmens.evolution.factories.ControlerFactory;
import nl.benmens.processing.mvc.View;
import nl.benmens.evolution.world.World;
import nl.benmens.processing.PApplet;

public class EvolutionGame extends PApplet {
  static int MILLIS_PER_TICK = 10;

  private int nextTick = 0;

  View rootView;

  private World world = new World(80, 60);

  public void settings() {
    ControlerFactory controlerFactory = new ControlerFactory();

    rootView = new View(null);
    controlerFactory.createController(null, rootView, world);

    size(world.width * 10, world.height * 10, P2D);

    getLogger().debug("settings finished");
  }

  public void setup() {
    getLogger().debug("setup finished");
  }

  public void draw() {
    if (millis() > nextTick) {
      nextTick += MILLIS_PER_TICK;
      world.tick(MILLIS_PER_TICK);
    }

    clear();

    rootView.draw();
  }

  static public void main(String[] passedArgs) {
    if (passedArgs != null) {
      PApplet.main(new Object() { }.getClass().getEnclosingClass(), passedArgs);
    } else {
      PApplet.main(new Object() { }.getClass().getEnclosingClass());
    }
  }
}
