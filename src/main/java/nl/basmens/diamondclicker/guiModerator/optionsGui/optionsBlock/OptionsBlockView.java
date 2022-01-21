package nl.basmens.diamondclicker.guiModerator.optionsGui.optionsBlock;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.guiModerator.optionsGui.OptionsGui;
import nl.basmens.diamondclicker.guiModerator.optionsGui.OptionsGuiModelClient;
import nl.basmens.diamondclicker.mvc.View;

import processing.core.PApplet;

public class OptionsBlockView extends View implements OptionsGuiModelClient{
  ArrayList<OptionsBlockViewClient> clients = new ArrayList<OptionsBlockViewClient>();

  public OptionsGui optionsGui;
  
  
  public OptionsBlockView(View parentView, OptionsGui optionsGui, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    this.optionsGui = optionsGui;
    optionsGui.registerClient(this);

    shouldClip = true;
  }
  

  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.fill(30);
    p.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    p.textSize(70);
    p.textAlign(PApplet.LEFT, PApplet.BOTTOM);
    p.text("Set the method of abbreviating big numbers", 850, 80);
    p.text("Enable or disable particle effects", 850, 180);
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(OptionsBlockViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(OptionsBlockViewClient client) {
      clients.remove(client);
  }

  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (OptionsBlockViewClient client : clients) {
      client.onOptionsBlockViewDestroy();
    }

    optionsGui = null;
  }

  public void onOptionsGuiModelDestroy() {
    optionsGui = null;
  }
}
