package nl.benmens.processing.mvc;

import java.util.ArrayList;

public class Model {

  private ArrayList<Object> clients = new ArrayList<Object>();

  public Model() {
  }

  // ########################################################################
  // Subscriber handling
  // ########################################################################
  protected <T> ArrayList<T> getClientsImplementing(Class<T> interfaceClass) {
    ArrayList<T> result = new ArrayList<T>();

    for (Object client : clients) {
      if (interfaceClass.isInstance(client)) {
        result.add(interfaceClass.cast(client));
      }
    }

    return result;
  }

  public void registerClient(Object client) {
    if (!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(Object client) {
    clients.remove(client);
  }

}