package nl.basmens.diamondclicker.buttons.optionsButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonController;
import nl.basmens.diamondclicker.mvc.*;

public class OptionsButtonController extends ButtonController {
  public final String buttonID = "options";


  public OptionsButtonController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, new OptionsButtonView(parentView, frameRect, id), id);
  }
}
