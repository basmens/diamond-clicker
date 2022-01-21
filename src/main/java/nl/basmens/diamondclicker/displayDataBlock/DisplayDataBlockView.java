package nl.basmens.diamondclicker.displayDataBlock;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.canvas.Canvas;
import nl.basmens.diamondclicker.canvas.CanvasView;
import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class DisplayDataBlockView extends View {
  ArrayList<DisplayDataBlockViewClient> clients = new ArrayList<DisplayDataBlockViewClient>();

  Canvas canvas;
  
  
  public DisplayDataBlockView(View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    CanvasView canvasView = (CanvasView)findViewByID("canvas 0");
    canvas = (Canvas)canvasView.canvas;

    shouldClip = true;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void afterDrawChildren(PApplet p, float width, float height) {
    p.fill(160);
    p.noStroke();
    p.rect(0, 0, width, height);

    p.stroke(0);
    p.strokeWeight(20);
    p.line(0, height, width, height);
    p.line(width, 0, width, height);

    p.fill(15, 60, 180);
    p.noStroke();
    p.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    p.textSize(130);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text("Diamond Clicker", width * 0.5f, height / 2 - p.textDescent() / 2);

    String diamondsInBank = canvas.getDiamondsInBank().toString();
    String diamondsPerSecond = canvas.getDiamondsPerSecond().toString();

    p.fill(30, 105, 200);
    p.textFont(FontCache.getFont("DroidSansMono.ttf"));
    p.textSize(75);

    p.textAlign(PApplet.LEFT, PApplet.BOTTOM);
    p.text(diamondsInBank, 20, height / 2 + p.textDescent());
    p.textAlign(PApplet.RIGHT, PApplet.BOTTOM);
    p.text(diamondsPerSecond, width - 20, height / 2 + p.textDescent());

    p.textSize(55);
    p.textAlign(PApplet.LEFT, PApplet.TOP);
    p.text("Diamonds", 20, height / 2 - p.textDescent() + 10);
    p.textAlign(PApplet.RIGHT, PApplet.TOP);
    p.text("Diamonds / s", width - 20, height / 2 - p.textDescent() + 10);
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
  public void registerClient(DisplayDataBlockViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(DisplayDataBlockViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (DisplayDataBlockViewClient client : clients) {
      client.onDisplayDataBlockViewDestroy();
    }
  }
}
