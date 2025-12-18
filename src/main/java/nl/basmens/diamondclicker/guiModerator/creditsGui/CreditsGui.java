package nl.basmens.diamondclicker.guiModerator.creditsGui;

import nl.basmens.diamondclicker.DiamondClicker;
import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.mvc.Model;
import nl.benmens.processing.PApplet;
import nl.benmens.processing.SharedPApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

public class CreditsGui extends Model {
  ArrayList<CreditsGuiModelClient> clients = new ArrayList<CreditsGuiModelClient>();

  PImage creditsTextImg;


  public CreditsGui(Model parentModel, String id) {
    super(parentModel, id);

    getCreditsTextImg();
  }


  void getCreditsTextImg() {
    String[] text = SharedPApplet.loadStrings("credits.txt");
    //String[] text = SharedPApplet.loadStrings(this.getClass().getResource("resources/credits.txt").);
    PGraphics pg = SharedPApplet.createGraphics(2500, text.length * 70);

    boolean isLink = false;
    float textPosX = 0;
    float textPosY = 0;

    pg.beginDraw();
    pg.fill(30);
    pg.noStroke();
    pg.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    pg.textSize(70);
    pg.textAlign(PApplet.LEFT, PApplet.TOP);
    for(int i = 0; i < text.length; i++) {
      int textStart = 0;
      for(int j = 0; j < text[i].length(); j++) {
        if(text[i].charAt(j) == '[' || text[i].charAt(j) == ']') {
          pg.text(text[i].substring(textStart, j), textPosX, textPosY);
          textPosX += pg.textWidth(text[i].substring(textStart, j));
          textStart = j + 1;

          isLink = !isLink;
          if(isLink) {
            pg.fill(10, 0, 130);
          } else {
            pg.fill(30);
          }
        }
      }

      pg.text(text[i].substring(textStart), textPosX, textPosY);
      textPosX = 0;
      textPosY += 70;
    }
    
    pg.endDraw();
    creditsTextImg = pg.get();
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(CreditsGuiModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(CreditsGuiModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (CreditsGuiModelClient client : clients) {
      client.onCreditsGuiModelDestroy();
    }
  }
}
