package nl.basmens.diamondclicker.guiModerator.statsGui;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class StatsGuiView extends View implements StatsGuiModelClient{
  ArrayList<StatsGuiViewClient> clients = new ArrayList<StatsGuiViewClient>();

  public StatsGui statsGui;
  
  
  public StatsGuiView(View parentView, StatsGui statsGui, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.statsGui = statsGui;
    statsGui.registerClient(this);

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

    p.fill(200);
    p.noStroke();
    p.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    p.textSize(100);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text("Stats", width / 2, height / 2);
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
  public void registerClient(StatsGuiViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(StatsGuiViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (StatsGuiViewClient client : clients) {
      client.onStatsGuiViewDestroy();
    }

    statsGui = null;
  }

  public void onStatsGuiModelDestroy() {
    statsGui = null;
  }
}
