package nl.benmens;

import processing.core.PApplet;

public class SharedPApplet {
  public static PApplet sharedApplet;

  static public void rect(float a, float b, float c, float d) {
    sharedApplet.rect(a, b, c, d);
  }
}