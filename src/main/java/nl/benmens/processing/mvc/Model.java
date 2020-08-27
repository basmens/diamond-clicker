package nl.benmens.processing.mvc;

import nl.benmens.processing.observer.SubscriptionManager;

public class Model {

  public SubscriptionManager subscriptionManager = new SubscriptionManager();

  public Model() {
  }

  public void onDestroy() {
  }
}