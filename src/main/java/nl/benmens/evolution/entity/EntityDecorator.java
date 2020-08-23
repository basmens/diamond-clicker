package nl.benmens.evolution.entity;

public class EntityDecorator implements EntityInterface {

  EntityInterface parent;

  public EntityDecorator(EntityInterface parent) {
    this.parent = parent;
  }

  public void tick(int millisPerTick) {
    parent.tick(millisPerTick);
  }

  public <T> T getState(String stateKey, Class<T> type) {
    return parent.getState(stateKey, type);
  }

  public void setState(String stateKey, Object value) {
    parent.setState(stateKey, value);
  }
  
}