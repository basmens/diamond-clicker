package nl.basmens.diamondclicker.buttons.prestigeButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonController;
import nl.basmens.diamondclicker.mvc.*;

public class PrestigeButtonController extends ButtonController {
  public final String buttonID = "prestige";


  public PrestigeButtonController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, new PrestigeButtonView(parentView, frameRect, id), id);
  }
}
