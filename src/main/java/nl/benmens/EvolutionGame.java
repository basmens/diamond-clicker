package nl.benmens;

import processing.core.PApplet;


public class EvolutionGame extends PApplet
{

  public void settings() {
    size(800, 600, P2D);
  }
  

  static public void main(String[] passedArgs) {
        if (passedArgs != null) {
          PApplet.main(EvolutionGame.class, passedArgs);
        } else {
          PApplet.main(EvolutionGame.class);
        }
      }
}
