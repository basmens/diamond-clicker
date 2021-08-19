package nl.basmens.diamondclicker.buttonBlock;

import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class ButtonBlockView extends View{
  ArrayList<ButtonBlockViewClient> clients = new ArrayList<ButtonBlockViewClient>();
  
  
  public ButtonBlockView(View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    shouldClip = true;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.fill(50);
    p.noStroke();
    p.rect(0, 0, width, height);

    p.stroke(0);
    p.strokeWeight(20);
    p.line(0, height, width, height);
  }

  
  // ########################################################################
  // Mouse event
  // ########################################################################
  public boolean onMouseEvent(MouseEvent event) {
    PVector mousePos = screenPosToViewPos(new PVector(event.getX(), event.getY()));

    return getBoundsRect().contains(mousePos.x, mousePos.y);
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(ButtonBlockViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(ButtonBlockViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (ButtonBlockViewClient client : clients) {
      client.onButtonBlockViewDestroy();
    }
  }
}
