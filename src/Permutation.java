package edu.okstate.cs.des;

public class Permutation extends LookupTable {
  public static int[] p =  {
    16,  7, 20, 21,
    29, 12, 28, 17,
     1, 15, 23, 26,
     5, 18, 31, 10,
     2,  8, 24, 14,
    32, 27,  3,  9,
    19, 13, 30,  6,
    22, 11,  4, 25
  };
  
  static public int[] call(int[] sall ) {
    return applyLookup(sall, p);
  }
}