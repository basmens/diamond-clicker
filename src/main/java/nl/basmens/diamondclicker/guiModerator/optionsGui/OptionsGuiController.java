package nl.basmens.diamondclicker.guiModerator.optionsGui;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.buttons.exitButton.ExitButtonController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.mvc.*;
import nl.basmens.diamondclicker.switchButton.SwitchButton;
import nl.basmens.diamondclicker.switchButton.SwitchButtonController;
import nl.basmens.diamondclicker.switchButton.SwitchButtonModelClient;

public class OptionsGuiController extends Controller implements OptionsGuiModelClient, OptionsGuiViewClient, ButtonViewClient, SwitchButtonModelClient {
  public OptionsGui optionsGui;
  public OptionsGuiView optionsGuiView;

  ButtonView exitButton;
  SwitchButton switchButton;


  public OptionsGuiController(Controller parentController, View parentView, OptionsGui optionsGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.optionsGui = optionsGui;
    this.optionsGui.registerClient(this);
 
    optionsGuiView = new OptionsGuiView(parentView, optionsGui, frameRect, id);
    optionsGuiView.registerClient(this);

    Controller exitButtonController = new ExitButtonController(this, optionsGuiView, new Rectangle2D.Float(3200, 210, 80, 80), "exit button 0");
    exitButton = (ButtonView)exitButtonController.getView();
    exitButton.registerClient(this);

    switchButton = new SwitchButton(optionsGui, "switch button 0", "very low", "low", "medium", "high", "very high");
    new SwitchButtonController(this, optionsGuiView, switchButton, new Rectangle2D.Float(700, 550, 1200, 120), "switch button 0");
    switchButton.registerClient(this);
  }

  public View getView() {
    return optionsGuiView;
  }


  public void onSwitch(Model origin, int state) {
    if(origin == switchButton) {
      System.out.println(state);
    }
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

  public void onSwitchButtonModelDestroy(Model origin) {
    if(origin == switchButton) {
      switchButton = null;
    }
  }
}
