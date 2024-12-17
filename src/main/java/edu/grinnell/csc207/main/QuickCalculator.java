package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A class that computes based on the commands.
 *
 * @author Tiffany Tang
 *
 */

public class QuickCalculator {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator bc = new BFCalculator();
    BFRegisterSet set = new BFRegisterSet();
    Boolean hasHigherOp = false;
    String previousOperator = "";
    BigFraction hold = new BigFraction();
    BigFraction holdStored = new BigFraction();

    if (args[0].equals("")) {
      pen.println("FAILED [Invalid expression]");
      return;
    } // if
    if (args.length != 0) {
      for (int n = 0; n < args.length; n++) {
        String str = args[n];
        String[] spiltStr = str.split(" ");
        String start = str.substring(0, 1);
        String end = str.substring(str.length() - 1, str.length());
        hasHigherOp = bc.hasHigherOp(spiltStr);

        // Dealing with invalid currents and store commands
        if (bc.isOperator(start) || bc.isOperator(end)) {
          pen.println(str + "FAILED [Invalid expression]");
        } else if (str.startsWith("STORE")) {
          if (set.isValidReg(end)) {
            set.store(end.charAt(0), holdStored);
            pen.println(str + " -> " + "STORED");
          } // if
        } else if (!str.contains(" ")) {
          if (set.isValidReg(str)) {
            pen.println(str + " -> " + set.get(str.charAt(0)));
          } else {
            bc.add(new BigFraction(str));
            pen.println(str + " -> " + bc.get());
            holdStored = bc.get();
            bc.clear();
          } // if-else
        } else {
          for (int m = 0; m < spiltStr.length - 1; m++) {
            String current = spiltStr[m];
            String next = spiltStr[m + 1];
            BigFraction nextVal = new BigFraction();
            BigFraction currentVal = new BigFraction();

            // Make the current string to a BigFraction
            if (set.isValidReg(current)) {
              currentVal = set.get(current.charAt(0));
            } else if (!bc.isOperator(current)) {
              currentVal = new BigFraction(current);
            } // if-else

            // Process next to a BigFraction
            if (set.isValidReg(next)) {
              nextVal = set.get(next.charAt(0));
            } else if (!bc.isOperator(next)) {
              nextVal = new BigFraction(next);
            } // if-else

            // Dealing with different operations of values:
            if (current.equals("+")) {
              if (hasHigherOp && !previousOperator.equals("*") && !previousOperator.equals("/")) {
                previousOperator += "+";
                hold = nextVal;
                // continue;
              } else {
                bc.add(nextVal);
              } // if-else
            } else if (current.equals("-")) {
              if (hasHigherOp && !previousOperator.equals("*") || !previousOperator.equals("/")) {
                previousOperator += "-";
                hold = nextVal;
                // continue;
              } else {
                bc.subtract(nextVal);
              } // if-else
            } else if (current.equals("*")) {
              if (previousOperator.equals("+")) {
                bc.add(hold.multiply(nextVal));
              } // if
              if (previousOperator.equals("-")) {
                bc.subtract(hold.multiply(nextVal));
              } // if
              previousOperator += "*";
              bc.multiply(nextVal);
            } else if (current.equals("/")) {
              if (previousOperator.equals("+")) {
                bc.add(hold.divide(nextVal));
              } // if
              if (previousOperator.equals("-")) {
                bc.subtract(hold.divide(nextVal));
              } // if
              previousOperator += "/";
              bc.divide(nextVal);
            } else {
              if (n == 0) {
                bc.add(currentVal);
              } // if
            } // if-else
          } // for
          // Wrapping up and print the results
          previousOperator = "";
          hasHigherOp = false;
          pen.println(str + " -> " + bc.get());
        } // if-else
      } // for
    } // if
  } // main
} // QuickCalculator Class

