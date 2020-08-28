package nl.benmens.evolution.world;

import nl.benmens.processing.KeyEventsHandler;
import nl.benmens.processing.SharedPApplet;
import nl.benmens.processing.mvc.View;
import processing.event.KeyEvent;

public class WorldView extends View {


  public WorldView(View parent, World world) {
    super(parent);

    this.hasBackground = true;
    this.backgroundColor = SharedPApplet.color(255);
    this.setFrameRect(10, 10, world.width * 10 - 20, world.height * 10 - 20);
    this.setBoundsRect(0, 0, world.width * 10, world.height * 10);
    this.shouldClip = true;

    SharedPApplet.keyEvents.subscribe(new KeyEventsHandler(){
      @Override
      public void keyPressed(KeyEvent event) {
        System.out.println("Key pressed");
      }

      @Override
        public void keyReleased(KeyEvent event) {
          System.out.println("Key released");
        }
    }, subscriptionManager);
    
  }

  @Override
  public void mouseMoved(float mouseX, float mouseY, float pmouseX, float pmouseY) {
    System.out.println("move " + String.valueOf(mouseX));
    super.mouseMoved(mouseX, mouseY, pmouseX, pmouseY);
  }
  
}