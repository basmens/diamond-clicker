package nl.basmens.diamondclicker.diamondModerator.diamond;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import nl.basmens.diamondclicker.diamondModerator.DiamondModerator;
import nl.basmens.diamondclicker.diamondModerator.DiamondModeratorModelClient;
import nl.basmens.diamondclicker.mvc.View;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class DiamondView extends View implements DiamondModeratorModelClient{
  ArrayList<DiamondViewClient> clients = new ArrayList<DiamondViewClient>();

  public DiamondModerator diamondModerator;

  protected PVector pos;
  protected float size;
  protected float age = 0; //in seconds
  
  
  public DiamondView(View parentView, DiamondModerator diamondModerator, Rectangle2D.Float frameRect, String id, PVector pos, float size) {
    super(parentView, frameRect, id);

    this.diamondModerator = diamondModerator;
    diamondModerator.registerClient(this);

    this.pos = pos;
    this.size = size;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawSiblings(PApplet p, float width, float height) {
    p.imageMode(PApplet.CENTER);
    p.tint(255, 120);

    p.push();
    p.translate(pos.x, pos.y - size * 0.08f);
    p.rotate(age);
    p.scale(p.noise(age / 2) * 0.3f + 0.7f);
    p.image(diamondModerator.diamondShine, 0, 0, size * 1.5f, size * 1.65f);
    p.pop();

    p.push();
    p.translate(pos.x, pos.y - size * 0.08f);
    p.rotate(-age * 1.4f);
    p.scale(p.noise(age / 2 + 10) * 0.3f + 0.7f);
    p.image(diamondModerator.diamondShine, 0, 0, size * 0.9f, size * 1.5f);
    p.pop();
  }

  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.noTint();
    p.imageMode(PApplet.CENTER);
    p.image(diamondModerator.diamondIcon, pos.x, pos.y, size, size);
  }


  // ########################################################################
  // Mouse event
  // ########################################################################
  public boolean onMouseEvent(MouseEvent event) {
    if(event.getAction() == MouseEvent.DRAG || event.getAction() == MouseEvent.CLICK || (event.getAction() == 9 && event.getButton() == PApplet.LEFT)) {
      PVector mousePos = screenPosToViewPos(new PVector(event.getX(), event.getY()));

      if(mousePos.x > pos.x - size / 2 && mousePos.x < pos.x + size / 2 && mousePos.y > pos.y - size / 2 && mousePos.y < pos.y + size / 2) {
        for (DiamondViewClient client : clients) {
          client.onDiamondClick();
        }
      }
    }

    return false;
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(DiamondViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(DiamondViewClient client) {
      clients.remove(client);
  }

  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (DiamondViewClient client : clients) {
      client.onDiamondViewDestroy();
    }

    diamondModerator = null;
  }

  public void onDiamondModeratorModelDestroy() {
    diamondModerator = null;
  }
}
