package nl.benmens;

import processing.core.PApplet;

public class EvolutionGame extends PApplet {
  public static EvolutionGame sharedEvolutionGame;

  public void settings() {
    sharedEvolutionGame = this;

    size(800, 600, P2D);
  }

  public void setup() {
  }

  public void draw() {
    noStroke();
    fill(250, 90, 70);
    rect(0, 0, 100, 100);
  }

  static public void main(String[] passedArgs) {
    if (passedArgs != null) {
      PApplet.main(EvolutionGame.class, passedArgs);
    } else {
      PApplet.main(EvolutionGame.class);
    }
  }
}
