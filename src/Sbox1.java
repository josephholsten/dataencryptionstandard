package edu.okstate.cs.des;

public class Sbox1 extends Sbox {
  public static int sub(int b) {
    return sub_sb(b, sb);
  }
  
  static public int[][] sb = {
    {14,  4, 13, 1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9, 0,  7}, /* 0xxxx0 */
    { 0, 15,  7, 4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5, 3,  8}, /* 0xxxx1 */
    { 4,  1, 14, 8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10, 5,  0}, /* 1xxxx0 */
    {15, 12,  8, 2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0, 6, 13}  /* 1xxxx1 */
  };
}