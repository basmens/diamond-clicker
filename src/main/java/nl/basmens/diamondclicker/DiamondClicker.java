package nl.basmens.diamondclicker;

import nl.benmens.processing.PApplet;

public class DiamondClicker extends PApplet {
  static int MILLIS_PER_TICK = 10;


  public void settings() {
    size(800, 600, P2D);

    getLogger().debug("settings finished");
  }

  public void setup() {
  }

  public void draw() {
  }

  static public void main(String[] passedArgs) {
    if (passedArgs != null) {
    	PApplet.main(new Object() { }.getClass().getEnclosingClass(), passedArgs);
    } else {
    	PApplet.main(new Object() { }.getClass().getEnclosingClass());
    }
  }
}
