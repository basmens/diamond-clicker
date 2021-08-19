package nl.basmens.diamondclicker.diamondModerator;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.diamondModerator.diamond.DiamondController;
import nl.basmens.diamondclicker.mvc.*;
import nl.benmens.processing.SharedPApplet;
import processing.core.PVector;

public class DiamondModeratorController extends Controller implements DiamondModeratorModelClient, DiamondModeratorViewClient {
  public DiamondModerator diamondModerator;
  public DiamondModeratorView diamondModeratorView;

  public int diamondIdCounter = 0;


  public DiamondModeratorController(Controller parentController, View parentView, DiamondModerator diamondModerator, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.diamondModerator = diamondModerator;
    this.diamondModerator.registerClient(this);

    diamondModeratorView = new DiamondModeratorView(parentView, diamondModerator, frameRect, id);
    diamondModeratorView.registerClient(this);
  }


  public View getView() {
    return diamondModeratorView;
  }


  // ########################################################################
  // Tick
  // ########################################################################
  public void tick(int millisPassed) {
    float spawnChance = diamondModerator.diamondSpawnChance;
    for (; spawnChance > 1; spawnChance--) {
      spawnDiamond();
    }
    if (SharedPApplet.random(1) < spawnChance) {
      spawnDiamond();
    }
  }

  private void spawnDiamond() {
    float size = SharedPApplet.random(diamondModerator.minDiamondSize, diamondModerator.maxDiamondSize);
    PVector pos = new PVector(SharedPApplet.random(diamondModeratorView.boundsRect.width), -size / 2);
    
    new DiamondController(this, diamondModeratorView, diamondModerator, diamondModeratorView.getBoundsRect(), "diamond " + diamondIdCounter, pos, size);
    diamondIdCounter++;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    diamondModerator.unregisterClient(this);
    diamondModeratorView.unregisterClient(this);

    diamondModerator.destroy();
    diamondModeratorView.destroy();
  }

  public void onDiamondModeratorModelDestroy() {
    diamondModerator = null;
  }

  public void onDiamondModeratorViewDestroy() {
    diamondModeratorView = null;
  }
}
