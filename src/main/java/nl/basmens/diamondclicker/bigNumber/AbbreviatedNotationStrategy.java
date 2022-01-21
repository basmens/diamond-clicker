package nl.basmens.diamondclicker.bigNumber;

public class AbbreviatedNotationStrategy implements ToStringStrategy {
  private String[] abbreviations = {"", "k", "m", "b", "t", "?"};


  public String toString(double base, int exponent, int decimals) {
    int thousands = exponent / 3;
    base *= Math.pow(10, exponent % 3);

    return String.valueOf(base).substring(0, decimals + 2) + abbreviations[Math.min(thousands, abbreviations.length - 1)];
  }
}
