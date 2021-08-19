package nl.basmens.diamondclicker.canvas;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttonBlock.ButtonBlockController;
import nl.basmens.diamondclicker.diamondModerator.DiamondModerator;
import nl.basmens.diamondclicker.diamondModerator.DiamondModeratorController;
import nl.basmens.diamondclicker.displayDataBlock.DisplayDataBlockController;
import nl.basmens.diamondclicker.guiModerator.GuiModeratorController;
import nl.basmens.diamondclicker.mvc.*;


public class CanvasController extends Controller implements CanvasModelClient, CanvasViewClient {
  public Canvas canvas;
  public CanvasView canvasView;

  public CanvasController(Controller parentController, View parentView, Canvas canvas, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.canvas = canvas;
    this.canvas.registerClient(this);

    canvasView = new CanvasView(parentView, canvas, frameRect, id);
    canvasView.registerClient(this);

    new DiamondModeratorController(this, canvasView, new DiamondModerator(canvas, "diamond moderator 0"), new Rectangle2D.Float(0, 0, 2800, 2160), "diamond moderator 0");
    new DisplayDataBlockController(this, canvasView, new Rectangle2D.Float(0, 0, 2800, 180), "display data block 0");
    new ButtonBlockController(this, canvasView, new Rectangle2D.Float(2800, 0, 1040, 600), "button block 0");
    new GuiModeratorController(this, canvasView, canvas, canvasView.getBoundsRect(), "gui moderator 0");
  }


  public View getView() {
    return canvasView;
  }


  // ########################################################################
  // Tick
  // ########################################################################
  public void tick(int millisPassed) {
    updateToDestroy();
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    canvas.unregisterClient(this);
    canvasView.unregisterClient(this);

    canvas.destroy();
    canvasView.destroy();
  }

  public void onCanvasModelDestroy() {
    canvas = null;
  }

  public void onCanvasViewDestroy() {
    canvasView = null;
  }
}
