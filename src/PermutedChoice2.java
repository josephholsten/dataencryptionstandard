package edu.okstate.cs.des;

public class PermutedChoice2 extends LookupTable {
  static public int[] pc2 = {
    14, 17, 11, 24, 1, 5,
     3, 28, 15,  6, 21, 10,
    23, 19, 12,  4, 26, 8,
    16,  7, 27, 20, 13, 2,
    41, 52, 31, 37, 47, 55,
    30, 40, 51, 45, 33, 48,
    44, 49, 39, 56, 34, 53,
    46, 42, 50, 36, 29, 32
  };
  
  public static int[] call(int[] r) {
    return applyLookup(r, pc2);
  }
}