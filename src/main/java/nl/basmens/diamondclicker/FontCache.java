package nl.basmens.diamondclicker;

import java.util.HashMap;

import nl.benmens.processing.SharedPApplet;
import processing.core.PFont;

public class FontCache {

  static HashMap<String, PFont> fontCache = new HashMap<String, PFont>();

  static public PFont getFont(String name) {
    PFont resultFont = fontCache.get(name);

    if (resultFont == null) {
      resultFont = SharedPApplet.createFont(DiamondClicker.dataFolderPath + "fonts/" + name, 100);
      fontCache.put(name, resultFont);
    }

    return resultFont;
  }
}