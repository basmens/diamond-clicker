package nl.basmens.diamondclicker.bigNumber;

public class ScientificNotationStrategy implements ToStringStrategy {
  public String toString(double base, int exponent, int decimals) {
    return String.valueOf(base).substring(0, decimals + 2) + "E" + String.valueOf(exponent);
  }
}