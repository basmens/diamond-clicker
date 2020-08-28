package nl.benmens.evolution.game;

import nl.benmens.processing.PApplet;
import nl.benmens.processing.mvc.View;
import nl.benmens.evolution.factories.ControlerFactory;
import nl.benmens.evolution.world.World;

public class EvolutionGame extends PApplet {
  static int MILLIS_PER_TICK = 10;

  private int nextTick = 0;

  private View rootView = new View(null);;

  private World world = new World(80, 60);

  public void settings() {
    size(800, 600, P2D);

    ControlerFactory controlerFactory = new ControlerFactory();

    controlerFactory.createController(null, rootView, world);

    getLogger().debug("settings finished");
  }

  public void setup() {
  }

  public void draw() {
    if (millis() > nextTick) {
      nextTick += MILLIS_PER_TICK;
      world.tick(MILLIS_PER_TICK);
    }

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
