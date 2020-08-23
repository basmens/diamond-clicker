package nl.benmens.evolution.entity;

import nl.benmens.processing.mvc.Controller;
import nl.benmens.processing.mvc.View;

public class EntityController extends Controller {
  
  EntityInterface entity;
  EntityView entityView;

  public EntityController(Controller parentController, View parentView, EntityInterface entity) {
    super(parentController);

    this.entity = entity;
    this.entityView = new EntityView(parentView, entity);
  }

}