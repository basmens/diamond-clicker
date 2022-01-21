package nl.basmens.diamondclicker.switchButton;

import nl.basmens.diamondclicker.mvc.Model;
import nl.benmens.processing.SharedPApplet;

import java.util.ArrayList;

public class SwitchButton extends Model {
  ArrayList<SwitchButtonModelClient> clients = new ArrayList<SwitchButtonModelClient>();

  private String[] labels;
  private int stateCount = 0;
  private int state = 0;
  private float highestTextWidth; //at textsize = 100


  public SwitchButton(Model parentModel, String id, String... labels) {
    super(parentModel, id);

    this.stateCount = labels.length;
    this.labels = labels;

    calculateHighestTextWidth();
  }


  private void calculateHighestTextWidth() {
    SharedPApplet.textSize(100);
    for(int i = 0; i < labels.length; i++) {
      highestTextWidth = Math.max(highestTextWidth, SharedPApplet.textWidth(labels[i]));
    }
  }
  

  // ########################################################################
  // Getters and setters
  // ########################################################################
  public String[] getLabels() {
    return labels;
  }

  public void setLabels(String[] labels) {
    if(labels.length > 0) {
      this.labels = labels;
      stateCount = labels.length;

      calculateHighestTextWidth();
    }
  }

  public int getStateCount() {
    return stateCount;
  }

  public void setStateCount(int stateCount) {
    this.stateCount = Math.max(stateCount, 1);
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    if(this.state == state) {
      return;
    }

    this.state = Math.max(Math.min(state, stateCount - 1), 0);

    for (SwitchButtonModelClient client : clients) {
      client.onSwitch(this, state);
    }
  }

  public float getHighestTextWidth() {
    return highestTextWidth;
  }


  // ########################################################################
  // Registration
  // ########################################################################
  public void registerClient(SwitchButtonModelClient client) {
    if(!clients.contains(client)) {
      clients.add(client);
    }
  }

  public void unregisterClient(SwitchButtonModelClient client) {
      clients.remove(client);
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    for (SwitchButtonModelClient client : clients) {
      client.onSwitchButtonModelDestroy(this);
    }
  }
}
