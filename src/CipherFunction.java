package edu.okstate.cs.des;
/**
 * This class provides the core encryption
 * functions for the DES algorithm
 */
public class CipherFunction {
  // Take a plaintext binary int array and encrypt with the key 
  public static int[] desEncrypt(int[] plaintext, int[] key) {
    int[][] subkeys = KeySequence.getEncryptKeys(key);
    
    return des(plaintext, subkeys);
	}
	
	/**
	 * Take a ciphertext binary int array and decrypt with the key
	 */
  public static int[] desDecrypt(int[] ciphertext, int[] key) {
    int[][] subkeys = KeySequence.getDecryptKeys(key);
  
    return des(ciphertext, subkeys);
  }
  
  /**
   * Take standard block r as a binary int array and apply
   * one full round against it with the subkey
   */
	public static int[] roundCipher(int[] r, int[] subkey){
    int[] er = BitSelection.call(r);
    int[] bs = xorBinArray(subkey, er);
    int[] l = Sbox.allSboxes(bs);
    return Permutation.call(l);
	}
	
	
	/**
	 * Return a binary string representation of i with at
	 * least the width provided, padding with 0s if necessary
	 */
	public static String padBinary(int i, int width) {
	  String result = Integer.toBinaryString(i);
	  while (result.length() < width) {
	    result = "0".concat(result);
	  }
	  return result;
	}
	
	/**
	 * Take a text and subkey sequence and perform the generic
	 * DES algorithm against it. This works for both encryption
	 * and decryption by inverting the order of subkeys
	 */
  private static int[] des(int[] text, int[][] subkeys) {
  	  int[] ip = InitialPermutation.call(text);

      int l[] = new int[32];
      int r[] = new int[32];

      // split into standard blocks l and r
      for(int i=0;i<32;i++){
          l[i]=ip[i];
          r[i]=ip[i+32];
      } 

      for(int roundNumber = 0; roundNumber<16; roundNumber++) {
          int[] finalInts = roundCipher(r, subkeys[roundNumber]);
          //Now we swap the L and R
          int[] tempL = xorBinArray(l, finalInts);
          l = r;
          r = tempL;
      }

      int[] result = new int[64];
      for(int i=0;i<32;i++){
          result[i] = r[i];
          result[i+32] = l[i];
      }

      return FinalPermutation.call(result);
  }
  
  /**
   * Convert from a binary int array to a binary string
   */
  public static String intArrayToBinaryString(int[] input) {
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < input.length; i++)
      result.append(Integer.toString(input[i],2));
    return result.toString();
  }
  
  /**
   * Convert from a binary string to a binary int array
   */
  public static int[] stringToIntArray(String input) {
    int[] result = new int[input.length()];
    for(int i = 0; i < input.length(); i++) {
      result[i] = Integer.parseInt(input.substring(i,i+1));
    }
    return result;
  }
  
  /**
   * Convert the plaintext string into its equivalent binary string
   */
  public static String textToBinaryString(String plaintext) {
    //The binary representation of the plaintext 
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<plaintext.length(); i++)
      sb.append(CipherFunction.padBinary((int)plaintext.charAt(i), 8));
    return sb.toString();
  }
  
  /**
   * Convert a binary string into its equivalent plaintext
   */
  public static String binaryStringToText(String plaintext) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<plaintext.length(); i+=8)
      sb.append((char)Integer.parseInt(plaintext.substring(i,i+8),2));
    return sb.toString();
  }
  
  
  /**
   * Convert a binary string into its equivalent hex string
   */
  public static String binaryStringToHex(String plaintext) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<plaintext.length(); i+=4){
      int val = Integer.parseInt(plaintext.substring(i,i+4),2);
      sb.append(Integer.toHexString(val));
    }
    return sb.toString();
  }
  /**
   * Convert a hex string into its equivalent binary string
   */
  public static String hexToBinaryString(String key) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<key.length(); i++){
      // convert hex value to integer
      int asciiVal = Integer.parseInt(key.substring(i,i+1), 16);
      // append to string
      sb.append(CipherFunction.padBinary(asciiVal, 4));
    }
    return sb.toString();
  }
  
  // XOR the elements of two binary int array, returning a
  // binary int array of results
  private static int[] xorBinArray(int[] p, int[] q) {
    int[] result = new int[p.length];
    for(int i=0; i<p.length; i++)
      result[i] = p[i] ^ q[i]; // p ^ q is the bitwise XOR
    return result;
  }
}
