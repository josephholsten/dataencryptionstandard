package edu.okstate.cs.des;

/**
 * Implements the standard Permutation (P) primitive
 * function for completing a single DES round
 */
public class Permutation extends LookupTable {
  private static int[] p =  {
    16,  7, 20, 21,
    29, 12, 28, 17,
     1, 15, 23, 26,
     5, 18, 31, 10,
     2,  8, 24, 14,
    32, 27,  3,  9,
    19, 13, 30,  6,
    22, 11,  4, 25
  };
  
  /**
   * Return the P function's mapping of the
   * binary int array for the resultant
   * blocks from the standard sbox functions
   */
  static public int[] call(int[] sall ) {
    return applyLookup(sall, p);
  }
}