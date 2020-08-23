package nl.benmens.evolution.entity;

import nl.benmens.processing.SharedPApplet;
import nl.benmens.processing.mvc.View;
import processing.core.PVector;

public class EntityView extends View {
  
  EntityInterface entity;

  public EntityView(View parentView, EntityInterface entity) {
    super(parentView);

    this.entity = entity;
  }

  @Override
  public void beforeDrawChildren() {
    PVector pos = entity.getState("pos", PVector.class);

    SharedPApplet.noStroke();
    SharedPApplet.fill(250, 90, 70);
    SharedPApplet.rect(pos.x, pos.y, 10, 10);
    
    super.beforeDrawChildren();
  }

}