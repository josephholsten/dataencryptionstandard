package edu.okstate.cs.des;

public class CipherFunction {
  public static int[] xorBinArray(int[] p, int[] q) {
    int[] result = new int[p.length];
    for(int i=0; i<p.length; i++)
      result[i] = p[i] ^ q[i]; // p ^ q is the bitwise XOR
    return result;
  }
  
  static public int[] stringToIntArray(String input) {
    int[] result = new int[input.length()];
    for(int i = 0; i < input.length(); i++) {
      result[i] = Integer.parseInt(input.substring(i,i+1));
    }
    return result;
  }
  
  static public String intArrayToBinaryString(int[] input) {
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < input.length; i++)
      result.append(Integer.toString(input[i],2));
    return result.toString();
  }
	
	public static int[] roundCipher(int[] r, int[] subkey){
    int[] er = BitSelection.call(r);
    int[] bs = CipherFunction.xorBinArray(subkey, er);
    int[] l = Sbox.allSboxes(bs);
    return Permutation.call(l);
	}
	public static String pretty(int[] input) {
	  StringBuilder sb = new StringBuilder();
	  String bin = CipherFunction.intArrayToBinaryString(input);
	  for(int i=0; i<bin.length()/4; i++) {
      int j = Integer.parseInt(bin.substring(i*4, (i*4)+4), 2);
      sb.append(String.format("%01x", j));
	  }
	  return sb.toString();
	}
	
	public static String pretty6(int[] input) {
	  StringBuilder sb = new StringBuilder();
	  String bin = CipherFunction.intArrayToBinaryString(input);
	  for(int i=0; i<bin.length()/6; i++) {
      int j = Integer.parseInt(bin.substring(i*6, (i+1)*6), 2);
      sb.append(String.format("%02x ", j));
	  }
	  return sb.toString();
	}
	
	
	
	public static int[] desEncrypt(int[] plaintext, int[] key) {
	  int[] ip = InitialPermutation.call(plaintext);

    int l[] = new int[32];
    int r[] = new int[32];
    
    // split into standard blocks l and r
    for(int i=0;i<32;i++){
        l[i]=ip[i];
        r[i]=ip[i+32];
    } 
    
    int[][] subkeys = KeySequence.generate(key);
    
    for(int roundNumber = 0; roundNumber<16; roundNumber++) {
        int[] finalInts = roundCipher(r, subkeys[roundNumber]);
        //Now we swap the L and R
        int[] tempL = CipherFunction.xorBinArray(l, finalInts);
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
}
