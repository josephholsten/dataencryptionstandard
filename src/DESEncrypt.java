package edu.okstate.cs.des;
/***********************************************
 * This is a test version of the encryption code
 * for plaintext of a set size of 64bits
 * @author Jason
 */
public class DESEncrypt {
  public static void main(String[]args){
    //The hex key that the key sheduler uses to create
    //the sub keys
    String key="1112131415161718";

    String plaintext = args[0];
    
    //Concatentate the binary forms of the key and plaintext
    //into a single text file
    String binFullText = CipherFunction.textToBinaryString(plaintext);
    String binFullKey = CipherFunction.hexToBinaryString(key);

    //Convert the strings into arrays where each
    //Entry represents an individual bit;
    int binArrText[] = CipherFunction.stringToIntArray(binFullText);
    int binArrKey[]  = CipherFunction.stringToIntArray(binFullKey);
    
    int[] cipherIntArr = CipherFunction.desEncrypt(binArrText, binArrKey);
    String cipherBinStr = CipherFunction.intArrayToBinaryString(cipherIntArr);
    
    System.out.println(CipherFunction.binaryStringToHex(cipherBinStr));
  }
}
