package nl.basmens.diamondclicker.buttons.button;

import nl.basmens.diamondclicker.mvc.View;

public interface ButtonViewClient {
  public void onButtonViewDestroy(View origin);

  default public void onButtonClick(View origin) {};
}
