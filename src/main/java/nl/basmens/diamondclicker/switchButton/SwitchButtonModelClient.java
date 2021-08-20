package nl.basmens.diamondclicker.switchButton;

import nl.basmens.diamondclicker.mvc.Model;

public interface SwitchButtonModelClient {
  public void onSwitchButtonModelDestroy(Model origin);

  default public void onSwitch(Model origin, int state) {}
}
