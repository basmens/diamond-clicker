package nl.basmens.diamondclicker.bigNumber;

public class ScientificNotationStrategy implements ToStringStrategy {
  public String toString(double base, int exponent) {
    return String.valueOf(base).substring(0, 5) + "E" + String.valueOf(exponent);
  }
}