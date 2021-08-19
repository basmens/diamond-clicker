package nl.basmens.diamondclicker.bigNumber;

public class AbbreviatedNotationStrategy implements ToStringStrategy {
  private String[] abbreviations = {"", "k", "m", "b", "t", "?"};


  public String toString(double base, int exponent) {
    int thousands = exponent / 3;
    base *= Math.pow(10, exponent % 3);

    return String.valueOf(base).substring(0, 5) + abbreviations[Math.min(thousands, abbreviations.length - 1)];
  }
}
