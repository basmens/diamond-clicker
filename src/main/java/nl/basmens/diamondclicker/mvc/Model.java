package nl.basmens.diamondclicker.mvc;

import java.util.ArrayList;

public class Model {
  protected Model parentModel = null;
  protected ArrayList<Model> childModels = new ArrayList<Model>();

  public final String id;


  public Model(Model parentModel, String id) {
    this.id = id;

    setParentModel(parentModel);
  }


  // ########################################################################
  // Parenting
  // ########################################################################
  public final void setParentModel(Model newParentModel) {
    if (parentModel != newParentModel) {

      if (parentModel != null) {
        parentModel.childModels.remove(this);
      }

      parentModel = newParentModel;

      if (parentModel != null) {

        if (!parentModel.childModels.contains(this)) {
          parentModel.childModels.add(this);
        }
      }
    }
  }


  public Model findModelByID(String id) {
    if(this.id == id) {
      return this;
    }

    for(Model child : childModels) {
      Model result = child.findModelByID(id);
      if(result != null) {
        return result;
      }
    }

    return null;
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public final void destroy() {
    this.setParentModel(null);

    for (Object childModelObject : (ArrayList<?>) childModels.clone()) {
      Model childModel = (Model) childModelObject;
      childModel.destroy();
    }

    onDestroy();
  }

  public void onDestroy() {
  }
}