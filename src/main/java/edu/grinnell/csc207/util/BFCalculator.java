package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A class with computational methods for calculating BigFractions.
 *
 * @author Tiffany Tang
 *
 */

public class BFCalculator {

  /** the last computed value. */
  BigFraction lastVal;

  /**
   * Create a new BFCalculator with a default lastVal.
   */
  public BFCalculator() {
    this.lastVal = new BigFraction(0, 1);
  } // BFCalculator()

  /**
   * Return the last computed value.
   *
   * @return the last computed value.
   */
  public BigFraction get() {
    return this.lastVal;
  } // get()

  /**
   * Add the val to the last computed value, and update the last val.
   *
   * @param val
   */
  public void add(BigFraction val) {
    this.lastVal = this.lastVal.add(val);
  } // add()

  /**
   * Substract the val from the last computed value and update it.
   *
   * @param val
   */
  public void subtract(BigFraction val) {
    this.lastVal = this.lastVal.subtract(val);
  } // subtract(val)

  /**
   * Substract the val from the last computed value and update it.
   *
   * @param val
   */
  public void multiply(BigFraction val) {
    this.lastVal = this.lastVal.multiply(val);
  } // multiply(val)

  /**
   * Divide the last compute value by val and update it.
   *
   * @param val
   */
  public void divide(BigFraction val) {
    this.lastVal = this.lastVal.divide(val);
  } // divide(val)

  /**
   * Set the last computed value to 0.
   */
  public void clear() {
    this.lastVal = new BigFraction(BigInteger.valueOf(0), BigInteger.valueOf(1));
  } // clear

  /**
   * Determine if the char is an operator.
   *
   * @param ch
   * @return true if it is an operator, otherwise return false.
   */
  public boolean isOperator(String str) {
    char[] arr = str.toCharArray();
    if (arr.length == 1) {
      char op = str.charAt(0);
      if (op == '+' || op == '-' || op == '*' || op == '/') {
        return true;
      } // if
    } // if
    return false;
  } // isOperator (char)

  /**
   * Determine if the string array has * or / operator.
   *
   * @param str the string array we search for the operators
   * @return true if it is an operator, otherwise return false.
   */
  public boolean hasHigherOp(String[] str) {
    for (String x : str) {
      if (x.equals("*") || x.equals("/")) {
        return true;
      } // if
    } // for
    return false;
  } // isOperator (char)

} // BFCalculator Class
