package nl.basmens.diamondclicker.canvas;

import nl.basmens.diamondclicker.bigNumber.BigNumber;
import nl.basmens.diamondclicker.bigNumber.ToStringStrategy;
import nl.basmens.diamondclicker.mvc.Model;
import java.util.ArrayList;

public class Canvas extends Model {
  ArrayList<CanvasModelClient> clients = new ArrayList<CanvasModelClient>();

  private BigNumber diamondsInBank = new BigNumber(1045776);
  private BigNumber diamondsPerSecond = new BigNumber(44326, 86);
  private BigNumber diamondsCollectedAllTime = new BigNumber(0);
  private BigNumber diamondsCollectedThisPrestige = new BigNumber(0);

  
  public Canvas(Model parentModel, String id) {
    super(parentModel, id);
  }
  

  // ########################################################################
  // Diamonds
  // ########################################################################
  public BigNumber getDiamondsInBank() {
    return diamondsInBank.copy();
  }

  public void setDiamondsInBank(BigNumber diamondsInBank) {
    BigNumber newDiamonds = BigNumber.max(this.diamondsInBank, diamondsInBank).copy().sub(this.diamondsInBank);
    diamondsCollectedAllTime.add(newDiamonds);
    diamondsCollectedThisPrestige.add(newDiamonds);

    this.diamondsInBank = diamondsInBank.copy();
  }

  public BigNumber getDiamondsPerSecond() {
    return diamondsPerSecond.copy();
  }

  public void setDiamondsPerSecond(BigNumber diamondsPerSecond) {
    this.diamondsPerSecond = diamondsPerSecond;
  }

  public void setDiamondsToStringStrategy(ToStringStrategy strategy) {
    diamondsInBank.setToStringStrategy(strategy);
    diamondsPerSecond.setToStringStrategy(strategy);
    diamondsCollectedAllTime.setToStringStrategy(strategy);
    diamondsCollectedThisPrestige.setToStringStrategy(strategy);
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
