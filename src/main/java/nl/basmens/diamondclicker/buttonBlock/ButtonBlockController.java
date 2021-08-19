package nl.basmens.diamondclicker.buttonBlock;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.creditsButton.CreditsButtonController;
import nl.basmens.diamondclicker.buttons.optionsButton.OptionsButtonController;
import nl.basmens.diamondclicker.buttons.prestigeButton.PrestigeButtonController;
import nl.basmens.diamondclicker.buttons.statsButton.StatsButtonController;
import nl.basmens.diamondclicker.mvc.*;

public class ButtonBlockController extends Controller implements ButtonBlockViewClient {
  public ButtonBlockView buttonBlockView;


  public ButtonBlockController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    buttonBlockView = new ButtonBlockView(parentView, frameRect, id);
    buttonBlockView.registerClient(this);
    
    new OptionsButtonController(this, buttonBlockView, new Rectangle2D.Float(50, 40, 440, 230), "options button 0");
    new CreditsButtonController(this, buttonBlockView, new Rectangle2D.Float(550, 40, 440, 230), "credits button 0");
    new StatsButtonController(this, buttonBlockView, new Rectangle2D.Float(50, 320, 440, 230), "stats button 0");
    new PrestigeButtonController(this, buttonBlockView, new Rectangle2D.Float(550, 320, 440, 230), "prestige button 0");
  }

  public View getView() {
    return buttonBlockView;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    buttonBlockView.unregisterClient(this);

    buttonBlockView.destroy();
  }

  public void onButtonBlockViewDestroy() {
    buttonBlockView = null;
  }
}
