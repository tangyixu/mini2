package edu.grinnell.csc207.util;

import java.math.BigInteger;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A class designed for storing and retrieving values stored during computations.
 *
 * @author Tiffany Tang
 *
 */

public class BFRegisterSet {

  /** An array for storing 26 registers and their BigFraction values. */
  BigFraction[] storage;

  /**
   * Create a new BFRegisterSet with an null array with size of 26..
   */
  public BFRegisterSet() {
    this.storage = new BigFraction[26];
  } // BFCalculator()

  /**
   * Stores the given value in the specified register.
   *
   * @param register
   * @param val
   */
  public void store(char register, BigFraction val) {
    this.storage[((int) register) - 97] = val;
  } // store(char, BigFraction);

  /**
   * Retrieves the value from the given register.
   *
   * @param register the register to store value.
   * @return the BigFraction value stored at the register.
   */
  public BigFraction get(char register) {
    return this.storage[(int) register - 97];
  }// get(register)

  /**
   * Determine if the incoming char is a valid register.
   * 
   * @param ch the char to be determined.
   * @return true if it exists in the set, return false otherwise.
   */
  public boolean isValidReg(String str) {
    int pos = (int) str.charAt(0);
    if (pos >= 97 && pos <= 122) {
      return true;
    } // if
    return false;
  } // isValidReg(String)

} // BFRegisterSet
