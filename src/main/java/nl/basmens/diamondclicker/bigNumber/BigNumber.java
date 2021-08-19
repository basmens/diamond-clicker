package nl.basmens.diamondclicker.bigNumber;

public class BigNumber {
  //private ToStringStrategy toStringStrategy = new ScientificNotationStrategy();
  //private ToStringStrategy toStringStrategy = new AbbreviatedNotationStrategy();
  private ToStringStrategy toStringStrategy = new SpeechNotationStrategy();

  private double base;
  private int exponent;


  public BigNumber(double base, int exponent) {
    this.base = base;
    this.exponent = exponent;
    redoExponent();
  }

  public BigNumber(double n) {
    valueOf(n);
  }


  public BigNumber copy() {
    return new BigNumber(base, exponent);
  }

  public BigNumber valueOf(double n) {
    base = n;
    exponent = 0;
    redoExponent();
    return this;
  }

  private void redoExponent() {
    while(base >= 10) {
      base /= 10;
      exponent++;
    }
    while(base < 1) {
      base *= 10;
      exponent--;
    }
  }


  // ########################################################################
  // Math
  // ########################################################################  
  public BigNumber add(BigNumber n) {
    if(exponent > n.getExponent()) {
      base += n.getBase() * Math.pow(10, n.getExponent() - exponent);
    } else {
      base = base * Math.pow(10, exponent - n.getExponent()) + n.getBase();
    }

    redoExponent();

    return this;
  }

  public BigNumber sub(BigNumber n) {
    add(n.copy().inverseSign());
    return this;
  }

  public BigNumber mult(BigNumber n) {
    base *= n.getBase();
    exponent += n.getExponent();
    redoExponent();

    return this;
  }

  public BigNumber div(BigNumber n) {
    base /= n.getBase();
    exponent -= n.getExponent();
    redoExponent();

    return this;
  }

  public BigNumber abs() {
    base = Math.abs(base);
    return this;
  }

  public BigNumber inverseSign() {
    base *= -1;
    return this;
  }


  // ########################################################################
  // Getters and setters
  // ########################################################################
  public int getSign() {
    return (int)Math.signum(base);
  }

  public void setSign(int sign) {
    base = Math.abs(base) * sign;
  }

  public double getBase() {
    return base;
  }


  public void setBase(double base) {
    this.base = base;
  }

  public int getExponent() {
    return exponent;
  }

  public void setExponent(int exponent) {
    this.exponent = exponent;
  }

  public ToStringStrategy getToStringStrategy() {
    return toStringStrategy;
  }

  public void setToStringStrategy(ToStringStrategy toStringStrategy) {
    this.toStringStrategy = toStringStrategy;
  }

  // ########################################################################
  // ToString
  // ########################################################################
  public String toString() {
    return toStringStrategy.toString(base, exponent);
  }
}
