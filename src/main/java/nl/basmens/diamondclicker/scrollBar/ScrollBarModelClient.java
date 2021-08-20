package nl.basmens.diamondclicker.scrollBar;

public interface ScrollBarModelClient {
  public void onScrollBarModelDestroy();

  public default void onVisibleHeightChange(float visibleHeight) {}
  public default void onMaxHeightChange(float maxHeight) {}
  public default void onHeightChange(float height) {}
}
