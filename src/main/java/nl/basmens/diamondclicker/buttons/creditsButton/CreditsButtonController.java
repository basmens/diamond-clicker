package nl.basmens.diamondclicker.buttons.creditsButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonController;
import nl.basmens.diamondclicker.mvc.*;

public class CreditsButtonController extends ButtonController {
  public final String buttonID = "credits";


  public CreditsButtonController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, new CreditsButtonView(parentView, frameRect, id), id);
  }
}
