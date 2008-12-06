package edu.okstate.cs.des;


// Implements the standard Bit Selection (E) primitive
// function for initializing a DES round
public class BitSelection extends LookupTable {
  //The E function lookup table
  private static int[] e = {
    32, 1,   2,   3,  4,  5,
     4, 5,   6,   7,  8,  9,
     8, 9,  10,  11, 12, 13,
    12, 13, 14,  15, 16, 17,
    16, 17, 18,  19, 20, 21,
    20, 21, 22,  23, 24, 25,
    24, 25, 26,  27, 28, 29,
    28, 29, 30,  31, 32,  1
  };
  
  // Map the standard r block onto eight
  // six-bit values using the E function 
  public static int[] call(int[] r) {
    return applyLookup(r, e);
  }
}