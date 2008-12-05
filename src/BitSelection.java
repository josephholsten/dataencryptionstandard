package edu.okstate.cs.des;

public class BitSelection {
  //The E function
  private static int[] E = {
    32, 1,   2,  3,  4,  5,
     4, 5,   6,  7,  8,  9,
     8, 9,  10, 11, 12, 13,
    12, 13, 14, 15, 16, 17,
    16, 17, 18, 19, 20, 21,
    20, 21, 22, 23, 24, 25,
    24, 25, 26, 27, 28, 29,
    28, 29, 30, 31, 32,  1
  };
  
  /* select eight six bit values */
  public static int[] call(int r) {
    int[] table = E; 
    int[] result  = {0, 0, 0, 0, 0, 0, 0, 0};
    
    int rows = result.length;
    int rcols = table.length / result.length;
    
    int input   = r;
System.out.println();
    for(int i = 0; i<table.length; i++) {
      int bit = table[i]-1;
      int is_set = (input & (1<<bit)) >>> bit;
      int out = i / rcols;
      int shift = (rcols-1) + ((out * rcols) - i);
      int val = is_set << shift;
      
System.out.printf("[%d] %3d:%3d %3d:%8x\n", out, bit, shift, is_set, val);
      result[out] |= val;
    }

    return result;
  }
}