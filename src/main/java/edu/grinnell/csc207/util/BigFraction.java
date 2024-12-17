package edu.grinnell.csc207.util;


import java.math.BigInteger;


/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * 
 * @author Tang Yulin, Tang Yixuan.
 * 
 */

public class BigFraction {

  // +------------------+---------------------------------------------

  // | Design Decisions |

  // +------------------+


  /*
   * 
   * (1) Denominators are always positive. Therefore, negative fractions
   * 
   * are represented with a negative numerator. Similarly, if a fraction
   * 
   * has a negative numerator, it is negative.
   *
   * 
   * 
   * (2) Fractions are not necessarily stored in simplified form. To
   * 
   * obtain a fraction in simplified form, one must call the `simplify`
   * 
   * method.
   * 
   */


  // +-----------+---------------------------------------------------

  // | Constants |

  // +-----------+


  /** The default numerator when creating fractions. */

  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(0);


  /** The default denominator when creating fractions. */

  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(1);


  // +--------+-------------------------------------------------------

  // | Fields |

  // +--------+


  /** The numerator of the fraction. Can be positive, zero or negative. */

  private BigInteger num;


  /** The denominator of the fraction. Must be non-negative. */

  private BigInteger denom;


  // +--------------+-------------------------------------------------

  // | Constructors |

  // +--------------+


  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param numerator The numerator of the fraction.
   * @param denominator The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new BigFraction using default values.
   */
  public BigFraction() {
    this.num = DEFAULT_NUMERATOR;
    this.denom = DEFAULT_DENOMINATOR;
  } // BigFraction()

  /**
   *
   * Build a new fraction by parsing a string.
   *
   * @param str The fraction in string form.
   *
   */
  public BigFraction(String str) {
    // BigInteger negatNum = BigInteger.valueOf(1);
    // BigInteger negatDenom = BigInteger.valueOf(1);
    if (!str.contains("/")) {
      this.num = BigInteger.valueOf(Integer.parseInt(str));
      this.denom = BigInteger.valueOf(1);
      // if (str.contains("-")) {
      // negatNum = BigInteger.valueOf(-1);
      // } // if
    } else {
      // if (str.contains("-")) {
      // if (str.indexOf('/') > str.indexOf('-')) {
      // negatNum = negatNum.negate();
      // } else {
      // negatDenom = negatDenom.negate();
      // } // if-else
      // } // if
      this.num = BigInteger.valueOf(Integer.parseInt(str.substring(0, str.indexOf('/'))));
      this.denom =
          BigInteger.valueOf(Integer.parseInt(str.substring(str.indexOf('/') + 1, str.length())));
    } // if-else
    // this.num = this.num.multiply(negatNum);
    // this.denom = this.denom.multiply(negatDenom);
    this.simplify();
  } // BigFraction (str)

  /**
   * Build a new fraction based on two integers.
   *
   * @param numerator the integer value for this.num.
   * @param denominator the integer value for this.denom.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
    this.simplify();
  } // BigFraction (int, int)

  // +---------+------------------------------------------------------

  // | Methods |

  // +---------+

  /**
   *
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   *
   */

  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()


  /**
   * Add another faction to this fraction.
   *
   * @param addend The fraction to add.
   * @return the result of the addition.
   *
   */

  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    if (addend.numerator().doubleValue() < 0) {
      return this.subtract(new BigFraction(addend.numerator().negate(), addend.denominator()));
    } else {
      resultDenominator = this.denom.multiply(addend.denom);
      resultNumerator = (this.num.multiply(addend.denom)).add(this.denom.multiply(addend.num));
      // Return the computed value
      return new BigFraction(resultNumerator, resultDenominator).simplify();
    } // if-else
  } // add(BigFraction)


  /**
   *
   * Get the denominator of this fraction.
   *
   * @return the denominator
   *
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   *
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   *
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   *
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if

    if (this.denom.intValue() == 1) {
      return this.num.toString();
    } // if

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  /**
   * Return the result of one fraction multiply the current one.
   *
   * @param fraction
   * @return a BigFraction variable as the result of multiplication.
   */
  public BigFraction multiply(BigFraction fraction) {
    BigFraction result =
        new BigFraction(this.num.multiply(fraction.num), this.denom.multiply(fraction.denom));
    return result.simplify();
  }// multiply

  /**
   * Return the fractional value (smaller than 1) of this fraction.
   *
   * @return a BigFraction type representing the fractional value.
   */
  public BigFraction fractional() {
    BigFraction result = new BigFraction(this.num.mod(this.denom), this.denom);
    return result;
  } // fractional ()

  /**
   * Substract one fraction from the other fraction.
   *
   * @return a Bigfraction as a result of the object's value minus the input value of val
   */
  public BigFraction subtract(BigFraction subtractVal) {
    BigInteger resultNum;
    BigInteger resultDenom;
    resultDenom = this.denom.multiply(subtractVal.denom);
    resultNum =
        (this.num.multiply(subtractVal.denom)).subtract(this.denom.multiply(subtractVal.num));
    return new BigFraction(resultNum, resultDenom).simplify();
  } // subtract(val)

  /**
   * Divide the current fraction by the other.
   *
   * @param DivideVal the value to divide.
   * @return a big fraction as the the result of division.
   */
  public BigFraction divide(BigFraction DivideVal) {
    BigFraction reciprocal = new BigFraction(DivideVal.denominator(), DivideVal.numerator());
    return this.multiply(reciprocal).simplify();
  }// divide()

  /**
   * simplify a BigFraction to the simplest form.
   *
   * @return a BigFraction in its simplest form
   */
  public BigFraction simplify() {
    BigInteger negat = BigInteger.valueOf(1);
    BigInteger numerator = this.num.abs();
    if (this.num.doubleValue() < 0) {
      negat = BigInteger.valueOf(-1);
    } // if
    BigInteger gcd = numerator.gcd(this.denom);
    BigInteger resultnumerator = numerator.divide(gcd);
    BigInteger resultdenominator = this.denom.divide(gcd);
    this.num = resultnumerator.multiply(negat);
    this.denom = resultdenominator;
    return new BigFraction(this.num, resultdenominator);
  } // simplify()

} // class BigFraction
