package nl.basmens.diamondclicker.canvas;

import nl.basmens.diamondclicker.bigNumber.BigNumber;
import nl.basmens.diamondclicker.mvc.Model;
import java.util.ArrayList;

public class Canvas extends Model {
  ArrayList<CanvasModelClient> clients = new ArrayList<CanvasModelClient>();

  private BigNumber diamondsInBank = new BigNumber(1045776);
  private BigNumber diamondsPerSecond = new BigNumber(44326, 86);

  
  public Canvas(Model parentModel, String id) {
    super(parentModel, id);
  }
  

  // ########################################################################
  // Getters and setters
  // ########################################################################
  public BigNumber getDiamondsInBank() {
    return diamondsInBank;
  }

  public void setDiamondsInBank(BigNumber diamondsInBank) {
    this.diamondsInBank = diamondsInBank;
  }

  public BigNumber getDiamondsPerSecond() {
    return diamondsPerSecond;
  }

  public void setDiamondsPerSecond(BigNumber diamondsPerSecond) {
    this.diamondsPerSecond = diamondsPerSecond;
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(CanvasModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(CanvasModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (CanvasModelClient client : clients) {
      client.onCanvasModelDestroy();
    }
  }
}
