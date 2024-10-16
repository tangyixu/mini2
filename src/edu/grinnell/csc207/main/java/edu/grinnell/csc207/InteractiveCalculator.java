package edu.grinnell.csc207;

import edu.grinnell.csc207.util.BFCalculator;


public class InteractiveCalculator {
  public void main (String args[]){
     
    while(!args[0].equals("QUIT"))
    {
      for(int n = 1; n < args.length; n++)
      {
        if (args[n].equals("/"))
        {
          if(Character.isDigit(args[n-1].charAt(0))){

            BFCalculator.lastVal.add(args[n-1].toCharArray());

          }
          else
          {

          }

        }//if
        
      }
    }//while
  }
  
  
}
