package nl.basmens.diamondclicker.diamondModerator.diamond;

public interface DiamondViewClient {
  public void onDiamondViewDestroy();

  default public void onDiamondClick() {}
}
