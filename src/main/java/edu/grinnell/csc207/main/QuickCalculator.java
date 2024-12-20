package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

/**
 * A class that computes based on the commands.
 *
 * @author Tiffany Tang
 *
 */

public class QuickCalculator {
  /**
   * The main method to use the model of calculator in quick computations.
   *
   * @param args
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator bc = new BFCalculator();
    BFRegisterSet set = new BFRegisterSet();

    for (int n = 0; n < args.length; n++) {
      InteractiveCalculator.calculator(args[n], pen, bc, set);
    } // for
  } // main
} // QuickCalculator Class
