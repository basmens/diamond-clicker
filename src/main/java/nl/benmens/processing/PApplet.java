package nl.benmens.processing;

import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class PApplet extends processing.core.PApplet {

  @Override
  public void keyPressed(KeyEvent event) {
    for (KeyEventsHandler s: SharedPApplet.keyEvents.getSubscribers()) {
      s.keyPressed(event);
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {
    for (KeyEventsHandler s: SharedPApplet.keyEvents.getSubscribers()) {
      s.keyReleased(event);
    } 
  }

  @Override
  public void mousePressed(MouseEvent event) {
    for (MouseEventsHandler s: SharedPApplet.mouseEvents.getSubscribers()) {
      s.mousePressed(mouseX, mouseY, pmouseX, pmouseY);
    } 
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    for (MouseEventsHandler s: SharedPApplet.mouseEvents.getSubscribers()) {
      s.mouseReleased(mouseX, mouseY, pmouseX, pmouseY);
    } 
  }

  @Override
  public void mouseMoved(MouseEvent event) {
    for (MouseEventsHandler s: SharedPApplet.mouseEvents.getSubscribers()) {
      s.mouseMoved(mouseX, mouseY, pmouseX, pmouseY);
    } 
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    for (MouseEventsHandler s: SharedPApplet.mouseEvents.getSubscribers()) {
      s.mouseDragged(mouseX, mouseY, pmouseX, pmouseY);
    } 
  }

  @Override
  public void mouseWheel(MouseEvent event) {
    for (MouseEventsHandler s: SharedPApplet.mouseEvents.getSubscribers()) {
      s.mouseWheel(mouseX, mouseY, event.getCount());
    } 
  }
}