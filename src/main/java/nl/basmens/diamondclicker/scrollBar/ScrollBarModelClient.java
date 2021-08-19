package nl.basmens.diamondclicker.scrollBar;

public interface ScrollBarModelClient {
  public void onScrollBarModelDestroy();

  public default void onHeightChange(float height) {}
}
