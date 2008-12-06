package edu.okstate.cs.des;

/**
 * Implements the standard Initial Permutation (IP) primitive
 * function for initializing a DES algorithm
 */
public class InitialPermutation extends LookupTable {
  public static int[] ip = {
    58, 50, 42, 34, 26, 18, 10, 2,
    60, 52, 44, 36, 28, 20, 12, 4,
    62, 54, 46, 38, 30, 22, 14, 6,
    64, 56, 48, 40, 32, 24, 16, 8,
    57, 49, 41, 33, 25, 17,  9, 1,
    59, 51, 43, 35, 27, 19, 11, 3,
    61, 53, 45 ,37, 29, 21, 13, 5,
    63, 55, 47, 39, 31, 23, 15, 7
  };
  
  /**
   * Return the IP function's mapping of the
   * binary int array for standard input block
   */
  public static int[] call(int[] input){
    return applyLookup(input, ip);
  }
} 