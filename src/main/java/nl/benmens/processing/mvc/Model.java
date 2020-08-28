package nl.benmens.processing.mvc;

import nl.benmens.processing.observer.Subject;
import nl.benmens.processing.observer.SubscriptionManager;

public class Model {

  public SubscriptionManager subscriptionManager = new SubscriptionManager();
  public Subject<ModelDestroyEventHandler> destroyEvents = new Subject<ModelDestroyEventHandler>(this);

  public Model() {
  }

  public final void destroy() {
    onDestroy();

    for (ModelDestroyEventHandler s: destroyEvents.getSubscribers()) {
      s.modelDestroyed(this);
    } 
  }

  public void onDestroy() {
  }
}