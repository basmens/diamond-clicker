package nl.benmens.processing.mvc;

public interface ModelDestroyEventHandler {
  default public void modelDestroyed(Model model) {}
}