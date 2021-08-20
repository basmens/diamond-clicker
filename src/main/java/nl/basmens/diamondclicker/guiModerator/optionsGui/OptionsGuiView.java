package nl.basmens.diamondclicker.guiModerator.optionsGui;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class OptionsGuiView extends View implements OptionsGuiModelClient{
  ArrayList<OptionsGuiViewClient> clients = new ArrayList<OptionsGuiViewClient>();

  public OptionsGui optionsGui;
  
  
  public OptionsGuiView(View parentView, OptionsGui optionsGui, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.optionsGui = optionsGui;
    optionsGui.registerClient(this);

    shouldClip = true;
    isVisible = false;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.fill(50, 150);
    p.noStroke();
    p.rect(0, 0, width, height);

    p.fill(150);
    p.stroke(0);
    p.strokeWeight(15);
    p.rect(600, 250, 2640, 1660);

    p.fill(30);
    p.noStroke();
    p.textFont(FontCache.getFont("ProcessingSansPro-Semibold.ttf"));
    p.textSize(200);
    p.textAlign(PApplet.LEFT, PApplet.TOP);
    p.text("Options", 700, 300);
  }

  
  // ########################################################################
  // Mouse event
  // ########################################################################
  public boolean onMouseEvent(MouseEvent event) {
    PVector mousePos = screenPosToViewPos(new PVector(event.getX(), event.getY()));

    if(!new Rectangle2D.Float(600, 250, 2640, 1660).contains(mousePos.x, mousePos.y)) {
      return false;
    }

    return true;
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(OptionsGuiViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(OptionsGuiViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (OptionsGuiViewClient client : clients) {
      client.onOptionsGuiViewDestroy();
    }

    optionsGui = null;
  }

  public void onOptionsGuiModelDestroy() {
    optionsGui = null;
  }
}
