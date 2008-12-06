package edu.okstate.cs.des;
/**
 * Implements the cryptographic padding scheme
 * from PKCS#5 for 8-byte blocks
 */
public class PKCS5Padding {
  /**
   * Pad the input block according to PKCS#5
   * up to the DES block size 64 bits
   */
  public static int[] enpad(int[] input) {
    int[] result = new int[64];
    int pad = 8 - (input.length/8);
    for(int i=0; i<64; i++) {
      if (input.length/8 > i/8) {
        result[i] = input[i];
      } else {
        result[i] = (pad >> ((63 - i) % 8)) & 1;
      }
    }
    return result;
  } 
  
  /**
   * Remove the padding from the input block according to PKCS#5
   * from a DES block size 64 bits
   */
  public static int[] depad(int[] input) {
    int pad = 0;
    for(int i=0; i<4; i++) {
      pad |= input[63-i] << i;
    }
    int unpadded = 8-pad;
    
    int[] result = new int[64-(8*pad)];
    for(int i = 0; i<result.length; i++) {
      result[i] = input[i];
    }
    
    return result;
  }
}