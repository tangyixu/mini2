package edu.grinnell.csc207;


import java.math.BigInteger;
import edu.grinnell.csc207.BigFraction;



public class BFCalculator {
  
  BigFraction lastVal = new BigFraction();

  public BigFraction get(){
    
    return this.lastVal;

  } //get()

  public void add(BigFraction val){
  
    this.lastVal = this.lastVal.add(val);

  } //add()

  public void subtract(BigFraction val){
  
    this.lastVal = this.lastVal.subtract(val);

  } //subtract(val)

  public void multiply(BigFraction val){

    this.lastVal = this.lastVal.multiplies(val);

  } //multiply(val)

  public void divide(BigFraction val){

    this.lastVal = this.lastVal.divide(val);

  } //divide(val)

  public void clear(){

    this.lastVal = new BigFraction();

  } //clear

}
