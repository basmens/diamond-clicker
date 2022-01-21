package nl.basmens.diamondclicker.scrollBar;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import nl.basmens.diamondclicker.mvc.View;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class ScrollBarView extends View implements ScrollBarModelClient{
  protected ArrayList<ScrollBarViewClient> clients = new ArrayList<ScrollBarViewClient>();

  ScrollBar scrollBar;

  Rectangle2D.Float bar;

  public boolean isHovered = false;
  public boolean isPressed = false;
  private float barGrabHeight;

  
  public ScrollBarView(View parentView, ScrollBar scrollBar, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.scrollBar = scrollBar;
    scrollBar.registerClient(this);

    bar = new Rectangle2D.Float(getBoundsRect().width - 60, 25, 20, getBoundsRect().height - 50);

    shouldClip = true;
    isVisible = scrollBar.getVisibleHeight() < scrollBar.getMaxHeight();
  }


  public void onVisibleHeightChange(float visibleHeight) {
    isVisible = visibleHeight < scrollBar.getMaxHeight();
  }

  public void onMaxHeightChange(float maxHeight) {
    isVisible = scrollBar.getVisibleHeight() < maxHeight;
  }


  // ########################################################################
  // Mouse event
  // ########################################################################
  public boolean onMouseEvent(MouseEvent event) {
    PVector mousePos = screenPosToViewPos(new PVector(event.getX(), event.getY()));
    float barPos = PApplet.map(scrollBar.getHeight(), 0, scrollBar.getMaxHeight(), 0, bar.height);
    float barSize = PApplet.map(scrollBar.getVisibleHeight(), 0, scrollBar.getMaxHeight(), 0, bar.height);

    if(event.getAction() == MouseEvent.EXIT) {
      isHovered = false;
      isPressed = false;
    }

    if(event.getAction() == MouseEvent.MOVE || event.getAction() == MouseEvent.DRAG) {
      if(mousePos.x > bar.x && mousePos.x < bar.x + bar.width && mousePos.y > bar.y + barPos && mousePos.y < bar.y + barPos + barSize) {
        isHovered = true;
      } else {
        isHovered = false;
      }
    }

    if(isPressed) {
      if(event.getAction() == MouseEvent.RELEASE) {
        isPressed = false;
        return true;
      } else if(event.getAction() == MouseEvent.DRAG) {
        float newHeight = PApplet.map(mousePos.y - bar.y - barGrabHeight, 0, bar.height, 0, scrollBar.getMaxHeight());
        scrollBar.setHeight(newHeight);
      }
    } else {
      if(isHovered && event.getAction() == MouseEvent.PRESS) {
        isPressed = true;
        barGrabHeight = mousePos.y - bar.y - barPos;
        return true;
      }
    }

    if(event.getAction() == MouseEvent.WHEEL) {
      scrollBar.setHeight(scrollBar.getHeight() + event.getCount() * 70);
    }

    return false;
  }
   
    
  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.strokeWeight(bar.width);
    p.strokeCap(PApplet.ROUND);

    p.stroke(180);
    p.line(
      bar.x + bar.width / 2, 
      bar.y + bar.width / 2, 
      bar.x + bar.width / 2, 
      bar.y - bar.width / 2 + bar.height);

    if(isPressed) {
      p.stroke(100);
    } else if(isHovered) {
      p.stroke(80);
    } else {
      p.stroke(60);
    }

    float barPos = PApplet.map(scrollBar.getHeight(), 0, scrollBar.getMaxHeight(), 0, bar.height - bar.width);
    float barSize = PApplet.map(scrollBar.getVisibleHeight(), 0, scrollBar.getMaxHeight(), 0, bar.height - bar.width);
    p.line(
      bar.x + bar.width / 2, 
      bar.y + bar.width / 2 + barPos, 
      bar.x + bar.width / 2, 
      bar.y + bar.width / 2 + barPos + barSize);
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(ScrollBarViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(ScrollBarViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (ScrollBarViewClient client : clients) {
      client.onScrollBarViewDestroy();
    }

    scrollBar = null;
  }

  public void onScrollBarModelDestroy() {
    scrollBar = null;
  }
}
