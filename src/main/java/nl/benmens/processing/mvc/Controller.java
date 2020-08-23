package nl.benmens.processing.mvc;

import java.util.ArrayList;

public class Controller {
  private Controller parentController = null;
  protected ArrayList<Controller> childControllers = new ArrayList<Controller>();

  public Controller(Controller parentController) {
    if (parentController != null) {
      setParentController(parentController);
    }
  }

  public final Controller getParentController() {
    return parentController;
  }

  public final void setParentController(Controller newParentController) {
    if (parentController != newParentController) {

      if (parentController != null) {
        parentController.childControllers.remove(this);
      }

      parentController = newParentController;

      if (parentController != null) {

        if (!parentController.childControllers.contains(this)) {
          parentController.childControllers.add(this);
        }
      }
    }
  }

  public final ArrayList<Controller> getChildControllers() {
    return childControllers;
  }

  public final void updateLayout() {
    beforeLayoutChildren();

    for (Controller child : childControllers) {
      child.updateLayout();
    }

    afterLayoutChildren();
  }

  public void beforeLayoutChildren() {
  }

  public void afterLayoutChildren() {
  }

  // ########################################################################
  // Destruction
  // ########################################################################
  public final void destroy() {
    this.setParentController(null);

    for (Object childControllerObject : (ArrayList<?>) childControllers.clone()) {
      Controller childController = (Controller) childControllerObject;
      childController.destroy();
    }

    onDestroy();
  }

  public void onDestroy() {
  }
}
