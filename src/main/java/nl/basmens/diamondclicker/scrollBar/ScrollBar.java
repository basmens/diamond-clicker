package nl.basmens.diamondclicker.scrollBar;

import nl.basmens.diamondclicker.mvc.Model;

import java.util.ArrayList;

public class ScrollBar extends Model {
  ArrayList<ScrollBarModelClient> clients = new ArrayList<ScrollBarModelClient>();

  private float visibleHeight;
  private float maxHeight;
  private float currentHeight;


  public ScrollBar(Model parentModel, String id, float visibleHeight, float maxHeight) {
    super(parentModel, id);

    this.visibleHeight = visibleHeight;
    this.maxHeight = maxHeight;
    this.currentHeight = 0;
  }
  

  // ########################################################################
  // Getters and setters
  // ########################################################################
  public float getVisibleHeight() {
    return visibleHeight;
  }

  public void setVisibleHeight(float visibleHeight) {
    this.visibleHeight = visibleHeight;

    if(currentHeight > maxHeight - visibleHeight) {
      currentHeight = maxHeight - visibleHeight;

      for(ScrollBarModelClient client : clients) {
        client.onHeightChange(currentHeight);
      }
    }
  }

  public float getMaxHeight() {
    return maxHeight;
  }

  public void setMaxHeight(float maxHeight) {
    this.maxHeight = maxHeight;

    if(currentHeight > maxHeight - visibleHeight) {
      currentHeight = maxHeight - visibleHeight;

      for(ScrollBarModelClient client : clients) {
        client.onHeightChange(currentHeight);
      }
    }
  }

  public float getHeight() {
    return currentHeight;
  }

  public void setHeight(float height) {
    height = Math.min(Math.max(height, 0), maxHeight - visibleHeight);

    if(currentHeight != height) {
      currentHeight = height;

      for(ScrollBarModelClient client : clients) {
        client.onHeightChange(currentHeight);
      }
    }
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(ScrollBarModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(ScrollBarModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (ScrollBarModelClient client : clients) {
      client.onScrollBarModelDestroy();
    }
  }
}
