package edu.okstate.cs.des;

import java.util.*;

public abstract class Sbox {
  /**
   * Calculate the substitution value from the binary
   * string using the lookup table sb. This works for all
   * sboxes by just replacing the lookup table
   */
  public static String sbox(String string, int[][] sb) {
    int x = i(string);
    int y = j(string);
    
    int lookup = sb[x][y];
    
    return CipherFunction.padBinary(lookup, 4);
  }
  
  /**
   * Apply all the substitution boxes 1-8 for a single round
   * of the DES algorithm, 
   */
  public static int[] allSboxes(int[] bs) {
    String string = CipherFunction.intArrayToBinaryString(bs);
    
    StringBuilder result = new StringBuilder();
    //The s functions
    result.append(Sbox1.call(string.substring( 0, 6)));
    result.append(Sbox2.call(string.substring( 6,12)));
    result.append(Sbox3.call(string.substring(12,18)));
    result.append(Sbox4.call(string.substring(18,24)));
    result.append(Sbox5.call(string.substring(24,30)));
    result.append(Sbox6.call(string.substring(30,36)));
    result.append(Sbox7.call(string.substring(36,42)));
    result.append(Sbox8.call(string.substring(42,48)));
    
    return CipherFunction.stringToIntArray(result.toString());
  }
  
  /**
   * Return the table value for the y axis
   * using the standard j function
   */
  public static int j(String b) {
    // inner bits    
    String temp = b.substring(1,5);
    return Integer.parseInt(temp,2);
  }
  
  /**
   * Return the table value for the x axis
   * using the standard i function 
   */
	public static int i(String b) {
    // The first and last bits
    String temp;
    temp = b.substring(0,1);
    temp = temp.concat(b.substring(5,6));
    return Integer.parseInt(temp,2);
	}
}