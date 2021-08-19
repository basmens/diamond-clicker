package nl.basmens.diamondclicker.guiModerator.creditsGui;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.buttons.exitButton.ExitButtonController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.mvc.*;
import nl.basmens.diamondclicker.scrollBar.ScrollBar;
import nl.basmens.diamondclicker.scrollBar.ScrollBarController;
import nl.basmens.diamondclicker.scrollBar.ScrollBarModelClient;


public class CreditsGuiController extends Controller implements CreditsGuiModelClient, CreditsGuiViewClient, ButtonViewClient, ScrollBarModelClient {
  public CreditsGui creditsGui;
  public CreditsGuiView creditsGuiView;

  ButtonView exitButton;
  ScrollBar scrollBar;


  public CreditsGuiController(Controller parentController, View parentView, CreditsGui creditsGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.creditsGui = creditsGui;
    this.creditsGui.registerClient(this);

    creditsGuiView = new CreditsGuiView(parentView, creditsGui, frameRect, id);
    creditsGuiView.registerClient(this);

    Controller exitButtonController = new ExitButtonController(this, creditsGuiView, new Rectangle2D.Float(3200, 210, 80, 80), "exit button 1");
    exitButton = (ButtonView)exitButtonController.getView();
    exitButton.registerClient(this);

    scrollBar = new ScrollBar(creditsGui, "scroll bar 1", 1310, creditsGui.creditsTextImg.height);
    new ScrollBarController(this, creditsGuiView, scrollBar, new Rectangle2D.Float(600, 250, 2640, 1660), "scroll bar 1");
    scrollBar.registerClient(this);
  }

  public View getView() {
    return creditsGuiView;
  }


  public void onButtonClick(View origin) {
    if(origin == exitButton) {
      GuiModeratorController guiModeratorController = (GuiModeratorController)getParentController();
      guiModeratorController.closeGui(this);
    }
  }


  public void onHeightChange(float height) {
    creditsGuiView.scrollHeight = height;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    creditsGui.unregisterClient(this);
    creditsGuiView.unregisterClient(this);

    creditsGui.destroy();
    creditsGuiView.destroy();

    exitButton.unregisterClient(this);
  }

  public void onCreditsGuiModelDestroy() {
    creditsGui = null;
  }

  public void onCreditsGuiViewDestroy() {
    creditsGuiView = null;
  }

  public void onButtonViewDestroy(View origin) {
    exitButton = null;
  }

  public void onScrollBarModelDestroy() {
    scrollBar = null;
  }
}
