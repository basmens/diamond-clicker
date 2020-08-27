package nl.benmens.processing.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

public class Subject<T> {

  private ArrayList<T> subscribers = new ArrayList<T>();
  private HashMap<T, Subscription<T>> subscriptions = new HashMap<T, Subscription<T>>(); 
  protected Object source;

  public Subject(Object source) {
    this.source = source;
  }

  public ArrayList<T> getSubscribers() {
    return subscribers;
  }

  public Subscription<?> subscribe(T observer) {
    if (!subscribers.contains(observer)) {
      subscribers.add(observer);

      Subscription<T> subscription =  new Subscription<T>(observer, this);
      subscriptions.put(observer, subscription);

      return subscription;
    } else {
      return this.subscriptions.get(observer);
    }
  }

  public Subscription<?> subscribe(T observer, SubscriptionManager subscriptionManager) {
    return (Subscription<?>)subscriptionManager.add(this.subscribe(observer));
  }

  protected void unsubscribe(Object observer) {
      subscribers.remove(observer);
      subscriptions.remove(observer);
  }

  public void unsubscribeAll() {
    Collection<Subscription<T>> clonedList = subscriptions.values();

    for (Subscription<T> s: clonedList) {
      s.unsubscribe();
    }
  }
  
}