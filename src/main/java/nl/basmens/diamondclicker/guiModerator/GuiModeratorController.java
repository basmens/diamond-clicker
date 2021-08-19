package nl.basmens.diamondclicker.guiModerator;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.canvas.Canvas;
import nl.basmens.diamondclicker.canvas.CanvasModelClient;
import nl.basmens.diamondclicker.guiModerator.creditsGui.CreditsGui;
import nl.basmens.diamondclicker.guiModerator.creditsGui.CreditsGuiController;
import nl.basmens.diamondclicker.guiModerator.optionsGui.OptionsGui;
import nl.basmens.diamondclicker.guiModerator.optionsGui.OptionsGuiController;
import nl.basmens.diamondclicker.guiModerator.prestigeGui.PrestigeGui;
import nl.basmens.diamondclicker.guiModerator.prestigeGui.PrestigeGuiController;
import nl.basmens.diamondclicker.guiModerator.statsGui.StatsGui;
import nl.basmens.diamondclicker.guiModerator.statsGui.StatsGuiController;
import nl.basmens.diamondclicker.mvc.*;
import processing.core.PApplet;

public class GuiModeratorController extends Controller implements CanvasModelClient, GuiModeratorViewClient, ButtonViewClient {
  public Canvas canvas;
  public GuiModeratorView guiModeratorView;

  Controller[] openGuis = new Controller[0];

  Controller optionsGuiController;
  Controller creditsGuiController;
  Controller statsGuiController;
  Controller prestigeGuiController;

  ButtonView optionsButtonView;
  ButtonView creditsButtonView;
  ButtonView statsButtonView;
  ButtonView prestigeButtonView;


  public GuiModeratorController(Controller parentController, View parentView, Canvas canvas, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.canvas = canvas;
    this.canvas.registerClient(this);

    guiModeratorView = new GuiModeratorView(parentView, canvas, frameRect, id);
    guiModeratorView.registerClient(this);

    optionsGuiController = new OptionsGuiController(this, guiModeratorView, new OptionsGui(canvas, "options gui 0"), guiModeratorView.getBoundsRect(), "options gui 0");
    creditsGuiController = new CreditsGuiController(this, guiModeratorView, new CreditsGui(canvas, "credits gui 0"), guiModeratorView.getBoundsRect(), "credits gui 0");
    statsGuiController = new StatsGuiController(this, guiModeratorView, new StatsGui(canvas, "stats gui 0"), guiModeratorView.getBoundsRect(), "stats gui 0");
    prestigeGuiController = new PrestigeGuiController(this, guiModeratorView, new PrestigeGui(canvas, "prestige gui 0"), guiModeratorView.getBoundsRect(), "prestige gui 0");


    optionsButtonView = (ButtonView)parentView.findViewByID("options button 0");
    optionsButtonView.registerClient(this);

    creditsButtonView = (ButtonView)parentView.findViewByID("credits button 0");
    creditsButtonView.registerClient(this);

    statsButtonView = (ButtonView)parentView.findViewByID("stats button 0");
    statsButtonView.registerClient(this);

    prestigeButtonView = (ButtonView)parentView.findViewByID("prestige button 0");
    prestigeButtonView.registerClient(this);
  }

  public View getView() {
    return guiModeratorView;
  }


  private void hideGuis(Controller... allowedGuis) {
    OUTER_LOOP : for(int i = openGuis.length - 1; i >= 0; i--) {
      Controller openGui = openGuis[i];

      for(int j = 0; j < allowedGuis.length; j++) {
        if(openGui == allowedGuis[j]) {
          continue OUTER_LOOP;
        }
      }

      openGuis[i].getView().isVisible = false;
      openGuis = (Controller[])PApplet.concat(PApplet.subset(openGuis, 0, i), PApplet.subset(openGuis, i + 1));
    }
  }

  private void showGui(Controller gui) {
    boolean isVisible = !gui.getView().isVisible;

    if(isVisible) {
      openGuis = (Controller[])PApplet.append(openGuis, gui);
    } else {
      for(int i = 0; i < openGuis.length; i++) {
        if(gui == openGuis[i]) {
          openGuis = (Controller[])PApplet.concat(PApplet.subset(openGuis, 0, i), PApplet.subset(openGuis, i + 1));
        }
      }
    }

    gui.getView().isVisible = isVisible;
  }

  public void closeGui(Controller gui) {
    int i = 0;
    while(gui != openGuis[i]) {
      i++;
    }
    for(int j = i; j < openGuis.length; j++) {
      openGuis[j].getView().isVisible = false;
    }
    openGuis = (Controller[])PApplet.subset(openGuis, 0, i);
  }


  public void onButtonClick(View origin) {
    if(origin == optionsButtonView) {
      hideGuis(optionsGuiController);
      showGui(optionsGuiController);
    }
    if(origin == creditsButtonView) {
      hideGuis(creditsGuiController);
      showGui(creditsGuiController);
    }
    if(origin == statsButtonView) {
      hideGuis(statsGuiController);
      showGui(statsGuiController);
    }
    if(origin == prestigeButtonView) {
      hideGuis(prestigeGuiController);
      showGui(prestigeGuiController);
    }
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    canvas.unregisterClient(this);
    guiModeratorView.unregisterClient(this);

    canvas.destroy();
    guiModeratorView.destroy();
  }

  public void onCanvasModelDestroy() {
    canvas = null;
  }

  public void onGuiModeratorViewDestroy() {
    guiModeratorView = null;
  }

  public void onButtonViewDestroy(View origin) {
    if(origin == optionsButtonView) {
      optionsButtonView = null;
    }
    if(origin == creditsButtonView) {
      creditsButtonView = null;
    }
    if(origin == statsButtonView) {
      statsButtonView = null;
    }
    if(origin == prestigeButtonView) {
      prestigeButtonView = null;
    }
  }
}
