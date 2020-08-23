package nl.benmens.world.terrain.grass;

import nl.benmens.processing.mvc.Controller;
import nl.benmens.processing.mvc.View;

public class GrassController extends Controller {

  Grass grass;
  GrassView grassView;

  public GrassController(Controller parent, View parentView, Grass grass) {
    super(parent);

    this.grass = grass;

    grassView = new GrassView(parentView);
    grassView.setFrameRect(grass.x * 10, grass.y * 10, 10, 10);
  }
  
}