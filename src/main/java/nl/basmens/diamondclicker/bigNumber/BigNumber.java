package nl.basmens.diamondclicker.bigNumber;

public class BigNumber {
  private ToStringStrategy toStringStrategy = new ScientificNotationStrategy();

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
    BigNumber copy = new BigNumber(base, exponent);
    copy.setToStringStrategy(toStringStrategy);
    return copy;
  }

  public BigNumber valueOf(double n) {
    base = n;
    exponent = 0;
    redoExponent();
    return this;
  }

  private void redoExponent() {
    if(base == 0) {
      exponent = 0;
      return;
    }
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

  public static BigNumber min(BigNumber a, BigNumber b) {
    if(a.getExponent() * a.getSign() == b.getExponent() * b.getSign()) {
      return (a.getBase() < b.getBase()) ? a : b;
    } else {
      return (a.getExponent() * a.getSign() < b.getExponent() * b.getSign()) ? a : b;
    }
  }

  public static BigNumber max(BigNumber a, BigNumber b) {
    if(a.getExponent() * a.getSign() == b.getExponent() * b.getSign()) {
      return (a.getBase() > b.getBase()) ? a : b;
    } else {
      return (a.getExponent() * a.getSign() > b.getExponent() * b.getSign()) ? a : b;
    }
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
    return toStringStrategy.toString(base, exponent, 3);
  }
}
