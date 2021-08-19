package nl.basmens.diamondclicker.guiModerator;

import nl.basmens.diamondclicker.canvas.Canvas;
import nl.basmens.diamondclicker.canvas.CanvasModelClient;
import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GuiModeratorView extends View implements CanvasModelClient{
  ArrayList<GuiModeratorViewClient> clients = new ArrayList<GuiModeratorViewClient>();

  public Canvas canvas;
  
  
  public GuiModeratorView(View parentView, Canvas canvas, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.canvas = canvas;
    canvas.registerClient(this);

    shouldClip = true;
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(GuiModeratorViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(GuiModeratorViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (GuiModeratorViewClient client : clients) {
      client.onGuiModeratorViewDestroy();
    }

    canvas = null;
  }

  public void onCanvasModelDestroy() {
    canvas = null;
  }
}
