package nl.basmens.diamondclicker;

import processing.core.PImage;
import java.util.HashMap;

import nl.benmens.processing.SharedPApplet;

public class ImageCache {

  static HashMap<String, PImage> imageCache = new HashMap<String, PImage>();

  static public PImage getImage(String name) {
    PImage resultImage = imageCache.get(name);

    if (resultImage == null) {
      resultImage = SharedPApplet.loadImage(DiamondClicker.dataFolderPath + "images/" + name);
      imageCache.put(name, resultImage);
    }

    return resultImage;
  }
}