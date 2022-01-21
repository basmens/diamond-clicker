package nl.basmens.diamondclicker.switchButton;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.mvc.Model;
import nl.basmens.diamondclicker.mvc.View;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SwitchButtonView extends View implements SwitchButtonModelClient{
  ArrayList<SwitchButtonViewClient> clients = new ArrayList<SwitchButtonViewClient>();

  public SwitchButton switchButton;
  
  public int isHovered = -1;
  public boolean isPressed = false;

  
  public SwitchButtonView(View parentView, SwitchButton switchButton, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.switchButton = switchButton;
    switchButton.registerClient(this);

    shouldClip = true;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    String[] labels = switchButton.getLabels();
    int stateCount = switchButton.getStateCount();
    int state = switchButton.getState();

    p.fill(80);
    p.noStroke();
    p.rect(0, 0, width, height);

    if(isHovered != -1) {
      p.fill(90);
      p.noStroke();
      p.rect(width * isHovered / stateCount, 0, width / stateCount, height);
    }

    p.fill(110);
    p.noStroke();
    p.rect(width * state / stateCount, 0, width / stateCount, height);

    p.noFill();
    p.stroke(120);
    p.strokeWeight(16);
    p.rect(width * state / stateCount + 8, 8, width / stateCount - 16, height - 16);

    p.fill(30);
    p.stroke(30);
    p.strokeWeight(10);
    p.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.textSize(Math.min(width * 100 / stateCount / switchButton.getHighestTextWidth(), height * .9f));

    float textY = (height - p.textDescent()) / 2;
    for(int i = 0; i < stateCount; i++) {
      p.text(labels[i], width * (i + .5f) / stateCount, textY);

      p.line(width * i / stateCount, 0, width * i / stateCount, height);
    }
    
    p.noFill();
    p.stroke(30);
    p.strokeWeight(15);
    p.rect(0, 0, width, height);
  }


  // ########################################################################
  // Mouse event
  // ########################################################################
  public boolean onMouseEvent(MouseEvent event) {
    PVector mousePos = screenPosToViewPos(new PVector(event.getX(), event.getY()));
    Rectangle2D.Float boundsRect = getBoundsRect();

    if(event.getAction() == MouseEvent.ENTER || event.getAction() == MouseEvent.MOVE || event.getAction() == MouseEvent.DRAG) {
      isHovered = (int)Math.floor(mousePos.x * switchButton.getStateCount() / boundsRect.width);
    }
    if(event.getAction() == MouseEvent.EXIT) {
      isHovered = -1;
      isPressed = false;
    }

    if(isPressed) {
      if(event.getAction() == MouseEvent.RELEASE) {
        switchButton.setState((int)Math.floor(mousePos.x * switchButton.getStateCount() / boundsRect.width));
        isPressed = false;

        return true;
      }
    } else {
      if(isHovered != -1 && event.getAction() == MouseEvent.PRESS) {
        isPressed = true;
        return true;
      }
    }

    return false;
  }
  
  
  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(SwitchButtonViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(SwitchButtonViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (SwitchButtonViewClient client : clients) {
      client.onSwitchButtonViewDestroy(this);
    }

    switchButton = null;
  }

  public void onSwitchButtonModelDestroy(Model origin) {
    switchButton = null;
  }
}
