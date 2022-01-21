package nl.basmens.diamondclicker.canvas;

import nl.basmens.diamondclicker.mvc.View;
import nl.benmens.processing.SharedPApplet;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class CanvasView extends View implements CanvasModelClient{
  ArrayList<CanvasViewClient> clients = new ArrayList<CanvasViewClient>();

  Rectangle2D.Float originalFrameRect;

  public Canvas canvas;
  
  
  public CanvasView(View parentView, Canvas canvas, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.canvas = canvas;
    canvas.registerClient(this);

    originalFrameRect = frameRect;

    setBoundsRect(0, 0, 3840, 2160);
    setFrameRect(
      getFrameRect().x + getFrameRect().width / 160, 
      getFrameRect().y + getFrameRect().width / 160, 
      getFrameRect().width - getFrameRect().width / 80, 
      getFrameRect().height - getFrameRect().width / 80);
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.fill(50);
    p.noStroke();
    p.rect(0, 0, width, height);
  }

  public void afterDrawChildren(PApplet p, float width, float height) {
    PVector borderPos = screenPosToViewPos(new PVector(
      PApplet.lerp(originalFrameRect.x, getFrameRect().x, 0.5f),
      PApplet.lerp(originalFrameRect.y, getFrameRect().y, 0.5f)));
    PVector borderSize = screenSizeToViewSize(new PVector(
      PApplet.lerp(originalFrameRect.width, getFrameRect().width, 0.5f),
      PApplet.lerp(originalFrameRect.height, getFrameRect().height, 0.5f)));
    PVector borderWidth = screenSizeToViewSize(new PVector(getFrameRect().x - originalFrameRect.x, 0));

    p.strokeCap(PApplet.PROJECT);
    p.noFill();
    p.stroke(0);
    p.strokeWeight(borderWidth.x);
    p.line(borderPos.x + 0 * borderSize.x, borderPos.y + 0 * borderSize.y, borderPos.x + 1 * borderSize.x, borderPos.y + 0 * borderSize.y);
    p.line(borderPos.x + 0 * borderSize.x, borderPos.y + 1 * borderSize.y, borderPos.x + 1 * borderSize.x, borderPos.y + 1 * borderSize.y);
    p.line(borderPos.x + 0 * borderSize.x, borderPos.y + 0 * borderSize.y, borderPos.x + 0 * borderSize.x, borderPos.y + 1 * borderSize.y);
    p.line(borderPos.x + 1 * borderSize.x, borderPos.y + 0 * borderSize.y, borderPos.x + 1 * borderSize.x, borderPos.y + 1 * borderSize.y);
  }
              

  // ########################################################################
  // Resize window event
  // ########################################################################
  public void onResizeWindowEvent(Rectangle2D.Float newSize) {
    originalFrameRect = newSize;
    setFrameRect(
      newSize.x + newSize.width / 160, 
      newSize.y + newSize.width / 160, 
      newSize.width - newSize.width / 80, 
      newSize.height - newSize.width / 80);
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(CanvasViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(CanvasViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (CanvasViewClient client : clients) {
      client.onCanvasViewDestroy();
    }

    canvas = null;
  }

  public void onCanvasModelDestroy() {
    canvas = null;
  }
}
