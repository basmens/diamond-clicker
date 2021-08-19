package nl.basmens.diamondclicker.guiModerator.statsGui;

import nl.basmens.diamondclicker.mvc.Model;

import java.util.ArrayList;

public class StatsGui extends Model {
  ArrayList<StatsGuiModelClient> clients = new ArrayList<StatsGuiModelClient>();

  public StatsGui(Model parentModel, String id) {
    super(parentModel, id);
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(StatsGuiModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(StatsGuiModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (StatsGuiModelClient client : clients) {
      client.onStatsGuiModelDestroy();
    }
  }
}
