package nl.benmens.world.terrain.water;

import nl.benmens.processing.mvc.Controller;
import nl.benmens.processing.mvc.View;

public class WaterController extends Controller {

  Water grass;
  WaterView grassView;

  public WaterController(Controller parent, View parentView, Water grass) {
    super(parent);

    this.grass = grass;

    grassView = new WaterView(parentView);
    grassView.setFrameRect(grass.x * 10, grass.y * 10, 10, 10);
  }
  
}