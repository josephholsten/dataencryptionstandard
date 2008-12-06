package edu.okstate.cs.des;

/**
 * This class generates encryption and decryption
 * key sequences for the DES algorithm
 */
public class KeySequence {
  static private int[] leftShift(int[] input) {
    int[] result = new int[input.length];
    // beware of fencepost errors, this should eval 
    // all but the last value of input 
    for(int i=0; i<(input.length-1); i++) {
      result[i] = input[i+1];
    }
    result[input.length-1] = input[0];
    return result;
  }
  
  /**
   * Generate the subkey sequence to allow
   * encryption with the DES algorithm
   */
  static public int[][] getEncryptKeys(int[] key) {
    int[] temp;
    int[][] result = new int[16][];
    temp = PermutedChoice1.call(key);
    int[] c = new int[temp.length/2];
    int[] d = new int[temp.length/2];
    
    for(int i=0; i<temp.length/2; i++) {
      c[i] = temp[i];
      d[i] = temp[i+temp.length/2];
    }
    
    int[][] cd;
    
    // This is the standard left-shift
    // sequence for the DES algorithm
    cd = leftShift(c,d);
    result[0] = generateKey(cd);
    cd = leftShift(cd);
    result[1] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[2] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[3] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[4] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[5] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[6] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[7] = generateKey(cd);
    cd = leftShift(cd);
    result[8] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[9] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[10] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[11] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[12] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[13] = generateKey(cd);
    cd = leftShift(cd);
    cd = leftShift(cd);
    result[14] = generateKey(cd);
    cd = leftShift(cd);
    result[15] = generateKey(cd);
    
    return result;
  }
  
  /**
   * Generate the subkey sequence to allow
   * decryption with the DES algorithm
   */
  static public int[][] getDecryptKeys(int[] key) {
    int[][] encryptSequence = getEncryptKeys(key);
    int len = encryptSequence.length;
    int[][] result = new int[len][];
    
    // the des algorithm will decrypt if the
    // keys are in the reverse order,
    // so we reverse them
    for(int i=0; i<len; i++)
      result[len-1-i] = encryptSequence[i];
      
    return result;
  }
  
  
  static private int[][] leftShift(int[] c, int[] d) {
    int[][] result = new int[2][];
    result[0] = leftShift(c);
    result[1] = leftShift(d);
    return result;
  }
  
  static private int[][] leftShift(int[][] input) {
    return leftShift(input[0], input[1]);
  }
  
  static private int[] generateKey(int[][] input) {
    return generateKey(input[0], input[1]);
  }
  static private int[] generateKey(int[] c, int[] d) {
    int[] result = new int[56];
    for(int i=0; i<result.length/2; i++) {
      result[i] = c[i];
      result[i+result.length/2] = d[i];
    }
    return PermutedChoice2.call(result);
  }
}