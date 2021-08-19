package nl.basmens.diamondclicker.buttons.button;

import nl.basmens.diamondclicker.mvc.*;

public abstract class ButtonController extends Controller implements ButtonViewClient {
  public ButtonView buttonView;


  public ButtonController(Controller parentController, ButtonView buttonView, String id) {
    super(parentController, id);

    this.buttonView = buttonView;
    buttonView.registerClient(this);
  }

  public View getView() {
    return buttonView;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    buttonView.unregisterClient(this);

    buttonView.destroy();
  }

  public void onButtonViewDestroy(View origin) {
    buttonView = null;
  }
}
