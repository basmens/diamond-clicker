package nl.basmens.diamondclicker.buttons.exitButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonController;
import nl.basmens.diamondclicker.mvc.*;

public class ExitButtonController extends ButtonController {
  public ExitButtonController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, new ExitButtonView(parentView, frameRect, id), id);
  }
}
