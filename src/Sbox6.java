package edu.okstate.cs.des;

public class Sbox6 extends Sbox {
  private static  int sb[][]={
      {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
      {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
      {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
      {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13} 
  };
  
  public static String call(String string) {
    //Create a substring of 6bits
    String substring = string.substring(29,34);
    
    return sbox(string, sb);
  }
}