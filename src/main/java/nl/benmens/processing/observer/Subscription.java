package nl.benmens.processing.observer;

public class Subscription<T> {

  T observer;
  Subject<T> subject;

  public Subscription(T observer, Subject<T> subject) {
    this.observer = observer;
    this.subject = subject;
  }

  void unsubscribe() {
    if (subject != null) {
      subject.unsubscribe(observer);
      subject = null;
      observer = null;
    }
  }
  
}