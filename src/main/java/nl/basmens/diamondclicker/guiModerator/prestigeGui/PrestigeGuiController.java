package nl.basmens.diamondclicker.guiModerator.prestigeGui;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.buttons.exitButton.ExitButtonController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.mvc.*;

public class PrestigeGuiController extends Controller implements PrestigeGuiModelClient, PrestigeGuiViewClient, ButtonViewClient {
  public PrestigeGui prestigeGui;
  public PrestigeGuiView prestigeGuiView;

  ButtonView exitButton;


  public PrestigeGuiController(Controller parentController, View parentView, PrestigeGui prestigeGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.prestigeGui = prestigeGui;
    this.prestigeGui.registerClient(this);

    prestigeGuiView = new PrestigeGuiView(parentView, prestigeGui, frameRect, id);
    prestigeGuiView.registerClient(this);

    Controller exitButtonController = new ExitButtonController(this, prestigeGuiView, new Rectangle2D.Float(3200, 210, 80, 80), "exit button 3");
    exitButton = (ButtonView)exitButtonController.getView();
    exitButton.registerClient(this);
  }

  public View getView() {
    return prestigeGuiView;
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
    prestigeGui.unregisterClient(this);
    prestigeGuiView.unregisterClient(this);

    prestigeGui.destroy();
    prestigeGuiView.destroy();
  }

  public void onPrestigeGuiModelDestroy() {
    prestigeGui = null;
  }

  public void onPrestigeGuiViewDestroy() {
    prestigeGuiView = null;
  }

  public void onButtonViewDestroy(View origin) {
    exitButton = null;
  }
}
