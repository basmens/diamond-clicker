package nl.basmens.diamondclicker;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.canvas.*;

import nl.benmens.processing.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class DiamondClicker extends PApplet {
  Canvas canvas;
  CanvasController canvasController;
  CanvasView canvasView;
  Canvas canvas2;
  CanvasController canvasController2;
  CanvasView canvasView2;

  static public String dataFolderPath = "C:/Users/basme/development/VScode/diamond-clicker/src/main/resources/";

  int lastTickTimestamp = 0;
  int millisPerTick = 25;

  int pwidth, pheight;


  static public void main(String[] passedArgs) {
    if (passedArgs != null) {
    	PApplet.main(new Object() { }.getClass().getEnclosingClass(), passedArgs);
    } else {
    	PApplet.main(new Object() { }.getClass().getEnclosingClass());
    }
  }

  
  public void settings() {
    size(displayWidth / 2, displayHeight / 2, P2D);
    pwidth = width;
    pheight = height;

    registerMethod("mouseEvent", this);
    registerMethod("keyEvent", this);

    getLogger().debug("settings finished");
  }

  public void setup() {
    surface.setResizable(true);

    canvas = new Canvas(null, "canvas 0");
    canvasController = new CanvasController(null, null, canvas, new Rectangle2D.Float(0, 0, width, height), "canvas 0");
    canvasView = (CanvasView)canvasController.getView();

    //canvas2 = new Canvas(null, "canvas 0");
    //canvasController2 = new CanvasController(null, null, canvas2, new Rectangle2D.Float(width / 2, 0, width / 2, height), "canvas 0");
    //canvasView2 = (CanvasView)canvasController2.getView();

    getLogger().debug("setup finished");
  }

  public void draw() {
    if(pwidth != width || pheight != height) {
      pwidth = width;
      pheight = height;

      resizeWindowEvent();
    }

    mouseEvent(new MouseEvent(null, 0, 9, 0, mouseX, mouseY, mouseButton, 1));

    while (millis() - lastTickTimestamp >= millisPerTick) {
      canvasController.update(millisPerTick);
      //canvasController2.update(millisPerTick);
      lastTickTimestamp += millisPerTick;
    }

    canvasView.draw();
    //canvasView2.draw();
  }


  // ########################################################################
  // Events
  // ########################################################################
  public void mouseEvent(MouseEvent event) {
		canvasView.processMouseEvent(event);
		//canvasView2.processMouseEvent(event);
  }

  public void keyEvent(KeyEvent event) {
		canvasView.processKeyEvent(event);
		//canvasView2.processKeyEvent(event);

    if(key == ESC) {
      key = 0;
    }
  }

  public void resizeWindowEvent() {
    canvasView.resizeWindowEvent();
    //canvasView2.resizeWindowEvent();
  }
}
