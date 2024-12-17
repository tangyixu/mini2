package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Based on user inputs, use BFCalculator to give out results.
 *
 * @author Tiffany Tang
 */

public class InteractiveCalculator {
  public static void main(String args[]) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    String command = eyes.nextLine();
    String[] spiltCom;
    BFCalculator bc = new BFCalculator();
    BFRegisterSet set = new BFRegisterSet();
    Boolean hasHigherOp = false;
    String previousOperator = "";
    BigFraction hold = new BigFraction();
    BigFraction holdStored = new BigFraction();

    while (!command.equals("QUIT")) {
      spiltCom = command.split(" ");
      String last = spiltCom[spiltCom.length - 1];
      String first = spiltCom[0];

      // Dealing with operators at the head or at the end
      if (bc.isOperator(last) || bc.isOperator(first)) {
        pen.println("*** ERROR [Invalid expression] ***");
        command = eyes.nextLine();
        bc.clear();
        continue;
      } // if

      // Deadling with a single value as the input and STORE command.
      if (first.equals("STORE")) {
        // if the next string is a valid r bc.add(set.get(first.charAt(0)));egister
        if (set.isValidReg(last)) {
          set.store(last.charAt(0), holdStored);
          pen.println("STORED");
        } else {
          // otherwise return error message
          pen.println("*** ERROR [STORE command received invalid register] ***");
          // bc.clear();
        } // if-else
        command = eyes.nextLine();
        holdStored = new BigFraction();
        continue;
      } // if

      if (last.equals(first)) {
        if (set.isValidReg(first)) {
          pen.println(set.get(first.charAt(0)));
        } else {
          bc.add(new BigFraction(first));
          pen.println(bc.get());
        } // if-else
        command = eyes.nextLine();
        holdStored = bc.get();
        bc.clear();
        continue;
      } // if


      for (int n = 0; n < spiltCom.length - 1; n++) {
        String input = spiltCom[n];
        String next = spiltCom[n + 1];
        BigFraction nextVal = new BigFraction();
        BigFraction inputVal = new BigFraction();
        hasHigherOp = bc.hasHigherOp(spiltCom);

        // Edge Cases
        if (Character.isUpperCase(input.charAt(0)) || Character.isUpperCase(next.charAt(0))) {
          pen.println("*** ERROR [Invalid expression] ***");
          bc.clear();
          command = eyes.nextLine();
          break;
        } // if

        // Process next to a BigFraction
        if (set.isValidReg(next)) {
          nextVal = set.get(next.charAt(0));
        } else if (!bc.isOperator(next)) {
          nextVal = new BigFraction(next);
        } // if-else

        // Process input to a BigFraction
        if (set.isValidReg(input)) {
          inputVal = set.get(input.charAt(0));
        } else if (!bc.isOperator(input)) {
          inputVal = new BigFraction(input);
        } // if-else

        // Dealing with different operations of values:
        if (input.equals("+")) {
          if (hasHigherOp && !previousOperator.equals("*") && !previousOperator.equals("/")) {
            previousOperator += "+";
            hold = nextVal;
            // continue;
          } else {
            bc.add(nextVal);
          } // if-else
        } else if (input.equals("-")) {
          if (hasHigherOp && !previousOperator.equals("*") || !previousOperator.equals("/")) {
            previousOperator += "-";
            hold = nextVal;
            // continue;
          } else {
            bc.subtract(nextVal);
          } // if-else
        } else if (input.equals("*")) {
          if (previousOperator.equals("+")) {
            bc.add(hold.multiply(nextVal));
          } // if
          if (previousOperator.equals("-")) {
            bc.subtract(hold.multiply(nextVal));
          } // if
          previousOperator += "*";
          bc.multiply(nextVal);
        } else if (input.equals("/")) {
          if (previousOperator.equals("+")) {
            bc.add(hold.divide(nextVal));
          } // if
          if (previousOperator.equals("-")) {
            bc.subtract(hold.divide(nextVal));
          } // if
          previousOperator += "/";
          bc.divide(nextVal);
        } else if (n == 0) {
          bc.add(inputVal);
          continue;
        } else {
          pen.println("*** ERROR [Invalid expression] ***");
          bc.clear();
          command = eyes.nextLine();
          break;
        } // if-else
      } // for

      // Wrapping up and print the results
      previousOperator = "";
      hasHigherOp = false;
      hold = new BigFraction();
      pen.println(bc.get());
      bc.clear();
      command = eyes.nextLine();
    } // while
  } // main
} // InteractiveCalculator
