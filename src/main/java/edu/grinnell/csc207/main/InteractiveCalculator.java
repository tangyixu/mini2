
package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Based on user inputs, use BFCalculator to give out results.
 *
 * @author Tiffany Tang
 */

public class InteractiveCalculator {
  /**
   * The main class in which we active interactive calculator.
   *
   * @param args
   */
  public static void main(String[] args) {
    PrintWriter penIC = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    String command = eyes.nextLine();
    BFCalculator bc = new BFCalculator();
    BFRegisterSet set = new BFRegisterSet();
    while (!command.equals("QUIT")) {
      InteractiveCalculator.calculator(command, penIC, bc, set);
      command = eyes.nextLine();
    } // while
  } // main

  /**
   * The model of interactive and quick calculators.
   *
   * @param userInput
   * @param pen
   * @param bc
   * @param set
   */
  public static void calculator(String userInput, PrintWriter pen, BFCalculator bc,
      BFRegisterSet set) {
    String[] spiltCom = userInput.split(" ");
    String last = spiltCom[spiltCom.length - 1];
    String first = spiltCom[0];

    // Dealing with operators at the head or at the end
    if (bc.isOperator(last) || bc.isOperator(first)) {
      pen.println("*** ERROR [Invalid expression] ***");
      return;
    } // if

    if (first.equals("")) {
      pen.println("FAILED [Invalid expression]");
      return;
    } // if

    // Deadling with a single value as the input and STORE userInput.
    if (first.equals("STORE")) {
      // if the next string is a valid register
      if (set.isValidReg(last)) {
        set.store(last.charAt(0), bc.get());
        pen.println("STORED");
      } else {
        // otherwise return error message
        pen.println("*** ERROR [STORE command received invalid register] ***");
      } // if-else
      bc.clear();
      return;
    } else {
      for (String x : spiltCom) {
        if (Character.isUpperCase(x.charAt(0))) {
          pen.println("*** ERROR [Invalid expression] ***");
          return;
        } // if
      } // for
    } // else

    // When user only give one input as a whole
    if (last.equals(first)) {
      BigFraction value = BFCalculator.processVal(bc, set, first);
      pen.println(value);
      if (!set.isValidReg(first)) {
        bc.add(value);
      } // if
      return;
    } // if-else

    for (int n = 0; n < spiltCom.length - 1; n++) {
      String input = spiltCom[n];
      String next = spiltCom[n + 1];
      BigFraction nextVal = new BigFraction();
      BigFraction inputVal = new BigFraction();

      // Process valid next and input to a BigFraction
      inputVal = BFCalculator.processVal(bc, set, input);
      nextVal = BFCalculator.processVal(bc, set, next);

      // invalid Registers during computations
      if (Character.isUpperCase(input.charAt(0)) || Character.isUpperCase(next.charAt(0))) {
        pen.println("*** ERROR [Invalid expression] ***");
        continue;
      } else if (input.equals("+")) {
        bc.add(nextVal);
      } else if (input.equals("-")) {
        bc.subtract(nextVal);
      } else if (input.equals("*")) {
        bc.multiply(nextVal);
      } else if (input.equals("/")) {
        bc.divide(nextVal);
      } else {
        if (n == 0) {
          bc.add(inputVal);
        } // if
      } // if-else
    } // for
      // Wrapping up and print the results
    pen.println(bc.get());
    bc.clear();
  } // Calculator(String, PrintWriter)
} // InteractiveCalculator
