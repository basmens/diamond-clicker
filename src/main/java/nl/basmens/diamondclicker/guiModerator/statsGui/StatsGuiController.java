package nl.basmens.diamondclicker.guiModerator.statsGui;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.buttons.exitButton.ExitButtonController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.mvc.*;

public class StatsGuiController extends Controller implements StatsGuiModelClient, StatsGuiViewClient, ButtonViewClient {
  public StatsGui statsGui;
  public StatsGuiView statsGuiView;

  ButtonView exitButton;


  public StatsGuiController(Controller parentController, View parentView, StatsGui statsGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.statsGui = statsGui;
    this.statsGui.registerClient(this);

    statsGuiView = new StatsGuiView(parentView, statsGui, frameRect, id);
    statsGuiView.registerClient(this);

    Controller exitButtonController = new ExitButtonController(this, statsGuiView, new Rectangle2D.Float(3200, 210, 80, 80), "exit button 2");
    exitButton = (ButtonView)exitButtonController.getView();
    exitButton.registerClient(this);
  }

  public View getView() {
    return statsGuiView;
  }


  public void onButtonClick(View origin) {
    if(origin == exitButton) {
      GuiModeratorController guiModeratorController = (GuiModeratorController)getParentController();
      guiModeratorController.closeGui(this);
    }
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    statsGui.unregisterClient(this);
    statsGuiView.unregisterClient(this);

    statsGui.destroy();
    statsGuiView.destroy();
  }

  public void onStatsGuiModelDestroy() {
    statsGui = null;
  }

  public void onStatsGuiViewDestroy() {
    statsGuiView = null;
  }

  public void onButtonViewDestroy(View origin) {
    exitButton = null;
  }
}
