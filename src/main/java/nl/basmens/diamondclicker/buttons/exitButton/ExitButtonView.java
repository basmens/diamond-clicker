package nl.basmens.diamondclicker.buttons.exitButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.buttons.button.ButtonView;
import nl.basmens.diamondclicker.buttons.button.ButtonViewClient;
import nl.basmens.diamondclicker.mvc.View;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class ExitButtonView extends ButtonView {
  
  
  public ExitButtonView(View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);

    shouldClip = true;
  }


  // ########################################################################
  // Key event
  // ########################################################################
  public final boolean onKeyEvent(KeyEvent event) {
    if(event.getKeyCode() == PApplet.ESC && event.getAction() == KeyEvent.RELEASE) {
      for (ButtonViewClient client : clients) {
        client.onButtonClick(this);
      }
      return true;
    }
    return false;
  }
   
    
  // ########################################################################
  // Drawing
  // ########################################################################
  public void beforeDrawChildren(PApplet p, float width, float height) {
    p.stroke(0);
    p.strokeWeight(15);
    p.strokeCap(PApplet.ROUND);

    if(isPressed) {
      p.fill(100);
      p.rect(0, 0, width, height);

      p.stroke(240, 10, 10);
      p.strokeWeight(10);
      p.line(20, 20, width - 20, height - 20);
      p.line(width - 20, 20, 20, height - 20);
    } else if(isHovered) {
      p.fill(70);
      p.rect(0, 0, width, height);

      p.stroke(185, 10, 10);
      p.strokeWeight(10);
      p.line(20, 20, width - 20, height - 20);
      p.line(width - 20, 20, 20, height - 20);
    } else {
      p.fill(50);
      p.rect(0, 0, width, height);

      p.stroke(185, 10, 10);
      p.strokeWeight(14);
      p.line(20, 20, width - 20, height - 20);
      p.line(width - 20, 20, 20, height - 20);
    }
  }
}
