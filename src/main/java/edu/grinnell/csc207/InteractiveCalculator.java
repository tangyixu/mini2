package edu.grinnell.csc207;


public class InteractiveCalculator {
  public void main (String args[]){
     
    while(!args[0].equals(null))
    {
      for(int n = 0; n < args.length; n++)
      {
        if (args[n].equals("/"))
        {
          if(Character.isDigit(args[n-1].charAt(0))){

            args[n-1].toCharArray();

          }
          


        }//if
        
      }
    }//while
  }
  
  
}
