package edu.okstate.cs.des;

public class Sbox {
  public static int sub_sb(int b, int[][] sb) {
    return sb[i(b)][j(b)];
  }
  
  public static int j(int b) {
    return ((b & 0x1e)>>>1);
  }
  
	public static int i(int b) {
	  return ((b & 0x20) >>> 4) | (b & 1);
	}
}