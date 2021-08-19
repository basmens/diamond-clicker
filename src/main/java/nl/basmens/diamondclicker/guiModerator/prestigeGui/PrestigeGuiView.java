package nl.basmens.diamondclicker.guiModerator.prestigeGui;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class PrestigeGuiView extends View implements PrestigeGuiModelClient{
  ArrayList<PrestigeGuiViewClient> clients = new ArrayList<PrestigeGuiViewClient>();

  public PrestigeGui prestigeGui;
  
  
  public PrestigeGuiView(View parentView, PrestigeGui prestigeGui, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.prestigeGui = prestigeGui;
    prestigeGui.registerClient(this);

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
    p.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    p.textSize(300);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text("Prestige system is \n in development", width / 2, height / 2);
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
  public void registerClient(PrestigeGuiViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(PrestigeGuiViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (PrestigeGuiViewClient client : clients) {
      client.onPrestigeGuiViewDestroy();
    }

    prestigeGui = null;
  }

  public void onPrestigeGuiModelDestroy() {
    prestigeGui = null;
  }
}
