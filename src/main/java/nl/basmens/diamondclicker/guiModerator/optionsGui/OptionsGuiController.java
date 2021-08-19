package nl.basmens.diamondclicker.guiModerator.optionsGui;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.buttons.exitButton.ExitButtonController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.mvc.*;

public class OptionsGuiController extends Controller implements OptionsGuiModelClient, OptionsGuiViewClient, ButtonViewClient {
  public OptionsGui optionsGui;
  public OptionsGuiView optionsGuiView;

  ButtonView exitButton;


  public OptionsGuiController(Controller parentController, View parentView, OptionsGui optionsGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.optionsGui = optionsGui;
    this.optionsGui.registerClient(this);

    optionsGuiView = new OptionsGuiView(parentView, optionsGui, frameRect, id);
    optionsGuiView.registerClient(this);

    Controller exitButtonController = new ExitButtonController(this, optionsGuiView, new Rectangle2D.Float(3200, 210, 80, 80), "exit button 0");
    exitButton = (ButtonView)exitButtonController.getView();
    exitButton.registerClient(this);
  }

  public View getView() {
    return optionsGuiView;
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
    optionsGui.unregisterClient(this);
    optionsGuiView.unregisterClient(this);

    optionsGui.destroy();
    optionsGuiView.destroy();
  }

  public void onOptionsGuiModelDestroy() {
    optionsGui = null;
  }

  public void onOptionsGuiViewDestroy() {
    optionsGuiView = null;
  }

  public void onButtonViewDestroy(View origin) {
    exitButton = null;
  }
}
