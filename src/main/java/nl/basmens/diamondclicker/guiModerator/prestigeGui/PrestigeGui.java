package nl.basmens.diamondclicker.guiModerator.prestigeGui;

import nl.basmens.diamondclicker.mvc.Model;

import java.util.ArrayList;

public class PrestigeGui extends Model {
  ArrayList<PrestigeGuiModelClient> clients = new ArrayList<PrestigeGuiModelClient>();

  public PrestigeGui(Model parentModel, String id) {
    super(parentModel, id);
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(PrestigeGuiModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(PrestigeGuiModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (PrestigeGuiModelClient client : clients) {
      client.onPrestigeGuiModelDestroy();
    }
  }
}
