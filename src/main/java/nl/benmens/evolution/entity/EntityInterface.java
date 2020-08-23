package nl.benmens.evolution.entity;

public interface EntityInterface {
  void tick(int millisPerTick);

  <T> T getState(String stateKey, Class<T> type);

  void setState(String stateKey, Object value);

}