package nl.basmens.diamondclicker.mvc;

import java.util.ArrayList;

public class Controller {
  protected Controller parentController = null;
  protected ArrayList<Controller> childControllers = new ArrayList<Controller>();

  public boolean toDestroy = false;

  public final String id;


  public Controller(Controller parentController, String id) {
    this.id = id;

    setParentController(parentController);
  }


  public View getView() {
    return null;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
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

  final public void addChildController(Controller child) {
    if(!childControllers.contains(child)) {
      childControllers.add(child);
    }
  }

  public Controller findControllerByID(String id) {
    if(this.id == id) {
      return this;
    }

    for(Controller child : childControllers) {
      Controller result = child.findControllerByID(id);
      if(result != null) {
        return result;
      }
    }

    return null;
  }

  
  // ########################################################################
  // Tick
  // ########################################################################
  public void update(int millisPassed) {
    for (Object childControllerObject : (ArrayList<?>) childControllers.clone()) {
      Controller c = (Controller) childControllerObject;
      c.update(millisPassed);
    }
    tick(millisPassed);
  }

  public void tick(int millisPassed) {
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public final void updateToDestroy() {
    if(toDestroy) {
      destroy();
    } else {
      for (Object childControllerObject : (ArrayList<?>) childControllers.clone()) {
        Controller c = (Controller) childControllerObject;
        c.updateToDestroy();
      }
    }
  }

  private final void destroy() {
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
