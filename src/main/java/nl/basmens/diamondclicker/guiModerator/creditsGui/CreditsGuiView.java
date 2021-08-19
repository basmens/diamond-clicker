package nl.basmens.diamondclicker.guiModerator.creditsGui;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.mvc.View;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class CreditsGuiView extends View implements CreditsGuiModelClient{
  ArrayList<CreditsGuiViewClient> clients = new ArrayList<CreditsGuiViewClient>();

  public CreditsGui creditsGui;

  float scrollHeight = 0;
  
  
  public CreditsGuiView(View parentView, CreditsGui creditsGui, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.creditsGui = creditsGui;
    creditsGui.registerClient(this);

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
    p.text("Credits", 700, 300);

    PVector clipPos = viewPosToScreenPos(new PVector(700, 550));
    PVector clipSize = viewSizeToScreenSize(new PVector(2440, 1310));
    p.clip(clipPos.x, clipPos.y, clipSize.x, clipSize.y);
    p.image(creditsGui.creditsTextImg, 700, 550 - scrollHeight);
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
  public void registerClient(CreditsGuiViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(CreditsGuiViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (CreditsGuiViewClient client : clients) {
      client.onCreditsGuiViewDestroy();
    }

    creditsGui = null;
  }

  public void onCreditsGuiModelDestroy() {
    creditsGui = null;
  }
}
