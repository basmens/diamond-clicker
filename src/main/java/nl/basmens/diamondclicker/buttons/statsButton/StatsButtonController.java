package nl.basmens.diamondclicker.buttons.statsButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonController;
import nl.basmens.diamondclicker.mvc.*;

public class StatsButtonController extends ButtonController {
  public final String buttonID = "stats";


  public StatsButtonController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, new StatsButtonView(parentView, frameRect, id), id);
  }
}
