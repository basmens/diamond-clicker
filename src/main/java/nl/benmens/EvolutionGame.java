package nl.benmens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import processing.core.PApplet;

public class EvolutionGame extends PApplet {
  protected static final Logger evolutionGameLogger = LogManager.getLogger();

  private Logger logger = evolutionGameLogger;


  public void settings() {
    SharedPApplet.sharedApplet = this;

    size(800, 600, P2D);
    logger.debug("settings finished");
  }

  public void setup() {
    logger.debug("setup finished");
  }

  public void draw() {
    noStroke();
    fill(250, 90, 70);
    rect(0, 0, 100, 100);
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
