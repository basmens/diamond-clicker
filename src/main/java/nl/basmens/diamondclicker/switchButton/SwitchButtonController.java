package nl.basmens.diamondclicker.switchButton;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.mvc.*;

public class SwitchButtonController extends Controller implements SwitchButtonModelClient, SwitchButtonViewClient {
  public SwitchButton switchButton;
  public SwitchButtonView switchButtonView;


  public SwitchButtonController(Controller parentController, View parentView, SwitchButton switchButton, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.switchButton = switchButton;
    this.switchButton.registerClient(this);

    switchButtonView = new SwitchButtonView(parentView, switchButton, frameRect, id);
    switchButtonView.registerClient(this);
  }

  public View getView() {
    return switchButtonView;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    switchButton.unregisterClient(this);
    switchButtonView.unregisterClient(this);

    switchButton.destroy();
    switchButtonView.destroy();
  }

  public void onSwitchButtonModelDestroy(Model origin) {
    switchButton = null;
  }

  public void onSwitchButtonViewDestroy(View origin) {
    switchButtonView = null;
  }
}
