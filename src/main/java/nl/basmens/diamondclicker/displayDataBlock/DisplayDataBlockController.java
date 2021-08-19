package nl.basmens.diamondclicker.displayDataBlock;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.mvc.*;

public class DisplayDataBlockController extends Controller implements DisplayDataBlockViewClient {
  public DisplayDataBlockView displayDataBlockView;


  public DisplayDataBlockController(Controller parentController, View parentView, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    displayDataBlockView = new DisplayDataBlockView(parentView, frameRect, id);
    displayDataBlockView.registerClient(this);
  }

  public View getView() {
    return displayDataBlockView;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    displayDataBlockView.unregisterClient(this);
    
    displayDataBlockView.destroy();
  }

  public void onDisplayDataBlockViewDestroy() {
    displayDataBlockView = null;
  }
}
