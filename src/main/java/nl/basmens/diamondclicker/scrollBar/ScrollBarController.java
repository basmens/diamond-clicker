package nl.basmens.diamondclicker.scrollBar;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.mvc.*;

public class ScrollBarController extends Controller implements ScrollBarModelClient, ScrollBarViewClient {
  ScrollBar scrollBar;
  ScrollBarView scrollBarView;


  public ScrollBarController(Controller parentController, View parentView, ScrollBar scrollBar, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.scrollBar = scrollBar;
    scrollBar.registerClient(this);

    this.scrollBarView = new ScrollBarView(parentView, scrollBar, frameRect, id);
    scrollBarView.registerClient(this);
  }


  public View getView() {
    return scrollBarView;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    scrollBar.unregisterClient(this);
    scrollBarView.unregisterClient(this);

    scrollBar.destroy();
    scrollBarView.destroy();
  }

  public void onScrollBarModelDestroy() {
    scrollBar = null;
  }

  public void onScrollBarViewDestroy() {
    scrollBarView = null;
  }
}