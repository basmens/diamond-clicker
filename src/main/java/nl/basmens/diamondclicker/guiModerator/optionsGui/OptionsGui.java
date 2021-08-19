package nl.basmens.diamondclicker.guiModerator.optionsGui;

import nl.basmens.diamondclicker.mvc.Model;

import java.util.ArrayList;

public class OptionsGui extends Model {
  ArrayList<OptionsGuiModelClient> clients = new ArrayList<OptionsGuiModelClient>();

  public OptionsGui(Model parentModel, String id) {
    super(parentModel, id);
  }
  

  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(OptionsGuiModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(OptionsGuiModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (OptionsGuiModelClient client : clients) {
      client.onOptionsGuiModelDestroy();
    }
  }
}
