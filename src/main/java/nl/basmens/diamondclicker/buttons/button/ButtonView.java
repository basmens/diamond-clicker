package nl.basmens.diamondclicker.buttons.button;

import nl.basmens.diamondclicker.mvc.View;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.event.MouseEvent;

public abstract class ButtonView extends View {
  protected ArrayList<ButtonViewClient> clients = new ArrayList<ButtonViewClient>();

  public boolean isHovered = false;
  public boolean isPressed = false;
  
  
  public ButtonView(View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentView, frameRect, id);
  }
  

  // ########################################################################
  // Mouse event
  // ########################################################################
  public boolean onMouseEvent(MouseEvent event) {
    if(event.getAction() == MouseEvent.ENTER) {
      isHovered = true;
    }
    if(event.getAction() == MouseEvent.EXIT) {
      isHovered = false;
      isPressed = false;
    }

    if(isPressed) {
      if(event.getAction() == MouseEvent.RELEASE) {
        isPressed = false;

        for (ButtonViewClient client : clients) {
          client.onButtonClick(this);
        }

        return true;
      }
    } else {
      if(isHovered && event.getAction() == MouseEvent.PRESS) {
        isPressed = true;
        return true;
      }
    }

    return false;
  }
  
  
  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(ButtonViewClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(ButtonViewClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (ButtonViewClient client : clients) {
      client.onButtonViewDestroy(this);
    }
  }
}
