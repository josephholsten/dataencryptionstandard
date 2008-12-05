package edu.okstate.cs.des;

public class Sbox1 extends Sbox {
  private static  int sb[][]={
    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
    {4, 1, 14, 8, 13, 6 ,2, 11, 15, 12, 9, 7 ,3, 10, 5, 0},
    {15, 12, 8 ,2 ,4 ,9 ,1, 7 ,5 ,11 ,3, 14, 10, 0 ,6, 13}
  };
  
  public static String call(String string) {
    //Create a substring of 6bits
    String substring = string.substring(0,5);
    
    return sbox(string, sb);
  }
}