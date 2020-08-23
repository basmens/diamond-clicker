package nl.benmens.world.terrain.grass;

import nl.benmens.processing.SharedPApplet;
import nl.benmens.processing.mvc.View;

public class GrassView extends View {
  
  public GrassView(View parent) {
    super(parent);

    setBoundsRect(0, 0, 100, 100);

    shouldClip = true;
    backgroundColor = SharedPApplet.color(0, 200, 0);
    hasBackground = true;
  }

  @Override
  public void beforeDrawChildren() {
    super.beforeDrawChildren();
  }

}