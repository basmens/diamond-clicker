package nl.basmens.diamondclicker.diamondModerator;

import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;

public class DiamondModeratorView extends View implements DiamondModeratorModelClient{
  ArrayList<DiamondModeratorViewClient> clients = new ArrayList<DiamondModeratorViewClient>();

  public DiamondModerator diamondModerator;
  
  
  public DiamondModeratorView(View parentView, DiamondModerator diamondModerator, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.diamondModerator = diamondModerator;
    diamondModerator.registerClient(this);

    shouldClip = true;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.fill(200);
    p.noStroke();
    p.rect(0, 0, width, height);
  }
  
  public void afterDrawChildren(PApplet p, float width, float height) {
    p.stroke(0);
    p.strokeWeight(20);
    p.line(width, 0, width,  height);
  }

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(DiamondModeratorViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(DiamondModeratorViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (DiamondModeratorViewClient client : clients) {
      client.onDiamondModeratorViewDestroy();
    }

    diamondModerator = null;
  }

  public void onDiamondModeratorModelDestroy() {
    diamondModerator = null;
  }
}
