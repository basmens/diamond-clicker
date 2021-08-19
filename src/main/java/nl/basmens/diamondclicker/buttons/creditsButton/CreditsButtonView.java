package nl.basmens.diamondclicker.buttons.creditsButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.FontCache;
import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.mvc.View;
import processing.core.PApplet;

public class CreditsButtonView extends ButtonView {
  
  
  public CreditsButtonView(View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    shouldClip = true;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    if(isPressed) {
      p.fill(85,105, 115);
      p.stroke(65, 75, 80);
      p.strokeWeight(30);
    } else if(isHovered) {
      p.fill(100, 125, 140);
      p.stroke(75, 90, 95);
      p.strokeWeight(30);
    } else {
      p.fill(100, 125, 140);
      p.noStroke();
    }

    p.rect(0, 0, width, height);

    p.fill(0);
    p.noStroke();
    p.textFont(FontCache.getFont("ProcessingSansPro-Regular.ttf"));
    p.textSize(height / 2);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text("Credits", width / 2, height / 2);
  }
}
