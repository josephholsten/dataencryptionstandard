package edu.okstate.cs.des;

public class Sbox {
  
  // calculate the sbox value of the string from the lookup table sb
  public static String sbox(String string, int[][] sb) {
    int x = i(string);
    int y = j(string);
    
    int lookup = sb[x][y];
    
    return padBinary(lookup, 4);
  }
  
  public static String allSboxes(String string) {
    //The final 32 bit string from all s functions
    StringBuilder result = new StringBuilder();

    //The s functions
    result.append(Sbox1.call(string));
    result.append(Sbox2.call(string));
    result.append(Sbox3.call(string));
    result.append(Sbox4.call(string));
    result.append(Sbox5.call(string));
    result.append(Sbox6.call(string));
    result.append(Sbox7.call(string));
    result.append(Sbox8.call(string));
    
    return result.toString();
  }
  
  // Return the table value for the y axis
  public static int j(String b) {
    // inner bits    
    String temp = b.substring(1,5);
    return Integer.parseInt(temp,2);
  }
	public static int i(String b) {
    // The first and last bits
    String temp;
    temp = b.substring(0,1);
    temp = temp.concat(b.substring(5,6));
    return Integer.parseInt(temp,2);
	}
	public static String padBinary(int i, int width) {
	  String result = Integer.toBinaryString(i);
	  while (result.length() < width) {
	    result = "0".concat(result);
	  }
	  return result;
	}
}