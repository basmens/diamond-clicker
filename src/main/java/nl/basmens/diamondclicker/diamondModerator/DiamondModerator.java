package nl.basmens.diamondclicker.diamondModerator;

import nl.basmens.diamondclicker.ImageCache;
import nl.basmens.diamondclicker.mvc.Model;
import processing.core.PImage;

import java.util.ArrayList;

public class DiamondModerator extends Model {
  ArrayList<DiamondModeratorModelClient> clients = new ArrayList<DiamondModeratorModelClient>();

  public PImage diamondIcon;

  public float minDiamondSize = 180;
  public float maxDiamondSize = 220;
  public float diamondSpawnChance = 0.1f;
  public int diamondTier = 0;
  public float averageFallSpeed = 5; //In seconds / screen height

  
  public DiamondModerator(Model parentModel, String id) {
    super(parentModel, id);

    diamondIcon = ImageCache.getImage("diamond" + diamondTier + ".png");
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(DiamondModeratorModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(DiamondModeratorModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (DiamondModeratorModelClient client : clients) {
      client.onDiamondModeratorModelDestroy();
    }
  }
}
