package nl.basmens.diamondclicker.diamondModerator.diamond;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.diamondModerator.DiamondModerator;
import nl.basmens.diamondclicker.diamondModerator.DiamondModeratorModelClient;
import nl.basmens.diamondclicker.mvc.*;
import nl.benmens.processing.SharedPApplet;
import processing.core.PVector;

public class DiamondController extends Controller implements DiamondModeratorModelClient, DiamondViewClient {
  public DiamondModerator diamondModerator;
  public DiamondView diamondView;

  float fallSpeed;
  boolean toDie = false;


  public DiamondController(Controller parentController, View parentView, DiamondModerator diamondModerator, Rectangle2D.Float frameRect, String id, PVector pos, float size) {
    super(parentController, id);

    this.diamondModerator = diamondModerator;
    this.diamondModerator.registerClient(this);

    diamondView = new DiamondView(parentView, diamondModerator, frameRect, id, pos, size);
    diamondView.registerClient(this);

    setFallSpeed();
  }


  public View getView() {
    return diamondView;
  }

  public void setFallSpeed() {
    fallSpeed = SharedPApplet.random(0.9f, 1.1f) * diamondModerator.averageFallSpeed;
  }

  public void onDiamondClick() {
    toDestroy = true;
  }


  // ########################################################################
  // Tick
  // ########################################################################
  public void tick(int millisPassed) {
    diamondView.pos.add(0, diamondView.getBoundsRect().height / 1000 / fallSpeed * millisPassed);
    diamondView.age += millisPassed / 1000.f;

    if(diamondView.pos.y > diamondView.getBoundsRect().height + diamondView.size / 2) {
      toDestroy = true;
    }
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    diamondModerator.unregisterClient(this);
    diamondView.unregisterClient(this);

    diamondView.destroy();
  }

  public void onDiamondModeratorModelDestroy() {
    diamondModerator = null;
  }

  public void onDiamondViewDestroy() {
    diamondView = null;
  }
}
