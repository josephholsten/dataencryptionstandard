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
  
  // XOR the elements of two binary int array, returning a
  // binary int array of results
  private static int[] xorBinArray(int[] p, int[] q) {
    int[] result = new int[p.length];
    for(int i=0; i<p.length; i++)
      result[i] = p[i] ^ q[i]; // p ^ q is the bitwise XOR
    return result;
  }
}
