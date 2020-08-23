package nl.benmens.evolution.entity;

import java.util.HashMap;

import nl.benmens.processing.mvc.Model;

public class Entity extends Model implements EntityInterface {

  private HashMap<String, Object> state = new HashMap<String, Object>();

  @Override
  public void tick(int millisPerTick) {    
  }

  @Override
  public <T> T getState(String stateKey, Class<T> type) {
    try {
      return type.cast(state.get(stateKey));
    } catch (ClassCastException e) {
      return null;
    }
  }

  @Override
  public void setState(String stateKey, Object value) {
    state.put(stateKey, value);
  }
}
