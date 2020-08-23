package nl.benmens.evolution.world.terrain.water;

import nl.benmens.processing.SharedPApplet;
import nl.benmens.processing.mvc.View;

public class WaterView extends View {
  
  public WaterView(View parent) {
    super(parent);

    setBoundsRect(0, 0, 100, 100);

    shouldClip = true;
    backgroundColor = SharedPApplet.color(0, 0, 200);
    hasBackground = true;
  }

  @Override
  public void beforeDrawChildren() {
    super.beforeDrawChildren();
  }

}