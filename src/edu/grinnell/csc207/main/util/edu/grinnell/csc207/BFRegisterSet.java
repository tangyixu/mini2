package edu.grinnell.csc207;

import edu.grinnell.csc207.util.BigFraction;

public class BFRegisterSet {

  BigFracion[] storage = new BigFraction[26];
  

  public void store(char register, BigFraction val){

    if(((int) register) < 97 || ((int) register) > 122)
    {
      
      System.err.println("*** ERROR [STORE command received invalid register] ***\n");

    }//if
    else
    {

      storage[((int) register)- 97] = val;

    }//else

  } //store(register, val)this.storage[];

  public BigFraction get(char register){

    int index = ((int) register) - 97;
    
    if(this.storage[index].equals(null))
    {
      System.err.println("*** ERROR [Invalid expression due to null registers] ***\n");
    }
    else
    {
      return this.storage[index];
    }
  
  }//get(register)

}