package nl.benmens;

import processing.core.PApplet;

/**
 * Hello world!
 *
 */
public class EvolutionGame extends PApplet
{
    static public void main(String[] passedArgs) {
        if (passedArgs != null) {
          PApplet.main(EvolutionGame.class, passedArgs);
        } else {
          PApplet.main(EvolutionGame.class);
        }
      }
}
