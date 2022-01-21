package nl.basmens.diamondclicker.guiModerator.optionsGui;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.buttons.exitButton.ExitButtonController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.guiModerator.optionsGui.optionsBlock.OptionsBlockController;
import nl.basmens.diamondclicker.guiModerator.optionsGui.optionsBlock.OptionsBlockView;
import nl.basmens.diamondclicker.mvc.*;
import nl.basmens.diamondclicker.scrollBar.ScrollBar;
import nl.basmens.diamondclicker.scrollBar.ScrollBarController;
import nl.basmens.diamondclicker.scrollBar.ScrollBarModelClient;

public class OptionsGuiController extends Controller implements OptionsGuiModelClient, OptionsGuiViewClient, ButtonViewClient, ScrollBarModelClient {
  public OptionsGui optionsGui;
  public OptionsGuiView optionsGuiView;

  ButtonView exitButton;
  ScrollBar scrollBar;

  OptionsBlockView optionsBlockView;


  public OptionsGuiController(Controller parentController, View parentView, OptionsGui optionsGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.optionsGui = optionsGui;
    this.optionsGui.registerClient(this);
 
    optionsGuiView = new OptionsGuiView(parentView, optionsGui, frameRect, id);
    optionsGuiView.registerClient(this);

    Controller exitButtonController = new ExitButtonController(this, optionsGuiView, new Rectangle2D.Float(3200, 210, 80, 80), "exit button 0");
    exitButton = (ButtonView)exitButtonController.getView();
    exitButton.registerClient(this);

    OptionsBlockController optionsBlockController = new OptionsBlockController(this, optionsGuiView, optionsGui, new Rectangle2D.Float(700, 550, 2440, 500), "options block 0");
    optionsBlockView = (OptionsBlockView)optionsBlockController.getView();

    scrollBar = new ScrollBar(optionsGui, "scroll bar 1", 1310, optionsBlockView.getBoundsRect().height);
    new ScrollBarController(this, optionsGuiView, scrollBar, new Rectangle2D.Float(600, 250, 2640, 1660), "scroll bar 1");
    scrollBar.registerClient(this);
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

  public void onHeightChange(float height) {
    Rectangle2D.Float boundsRect = optionsBlockView.getBoundsRect();
    optionsBlockView.setBoundsRect(boundsRect.x, height, boundsRect.width, boundsRect.height);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    optionsGui.unregisterClient(this);
    optionsGuiView.unregisterClient(this);
    exitButton.unregisterClient(this);
    scrollBar.unregisterClient(this);

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

  public void onScrollBarModelDestroy() {
    scrollBar = null;
  }
}
